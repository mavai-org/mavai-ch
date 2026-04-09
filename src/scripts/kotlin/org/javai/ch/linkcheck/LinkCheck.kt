package org.javai.ch.linkcheck

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Semaphore
import kotlin.io.path.absolutePathString
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.io.path.readLines
import kotlin.system.exitProcess

/*
 * Link checker for javai.ch.
 *
 * Walks every file in the project that contributes to published content,
 * extracts external URLs, and runs a pipeline of checks against each URL.
 *
 * Today the only shipped check is reachability. The pipeline is structured
 * so future checks slot in without touching extraction or reporting. For
 * example, a future `ContextMatchCheck` could send the fetched resource
 * plus the surrounding anchor text to an LLM and decide whether the link
 * target matches the description that refers to it.
 *
 * Run locally:
 *   ./gradlew checkLinks                 # check reachability
 *   ./gradlew checkLinks -Pstrict        # also fail on warnings
 *   ./gradlew checkLinks -PlistOnly      # list URLs, don't contact them
 *
 * In CI: see .github/workflows/check-links.yml
 */

// ─── Data model ──────────────────────────────────────────────────────────────

/** A URL discovered in a source file, together with the context it appeared in. */
data class FoundLink(
    val url: String,
    val file: Path,
    val line: Int,
    /** Short snippet of surrounding text — the full line the URL appeared on. */
    val context: String,
)

/** Outcome of running a single rule against a single link. */
sealed interface CheckOutcome {
    val rule: String

    data class Ok(override val rule: String, val detail: String = "") : CheckOutcome
    data class Warning(override val rule: String, val detail: String) : CheckOutcome
    data class Failure(override val rule: String, val detail: String) : CheckOutcome
}

/**
 * A pluggable check. Extension point for future checks.
 *
 * Implementations return a [CompletableFuture] so the pipeline can fan out
 * many checks concurrently. A rule that does not need to block (e.g. a
 * purely syntactic check) should return `CompletableFuture.completedFuture`.
 */
interface LinkCheckRule {
    val name: String
    fun evaluate(link: FoundLink): CompletableFuture<CheckOutcome>
}

// ─── Source discovery ────────────────────────────────────────────────────────

/**
 * Roots that contribute to published content. Documentation-only files
 * (CLAUDE.md, README.md, drafts/) are intentionally excluded.
 */
private val SCAN_ROOTS: List<Pair<String, List<String>>> = listOf(
    "content"        to listOf("md", "html"),
    "layouts"        to listOf("html"),
    "i18n"           to listOf("toml"),
    "data"           to listOf("json", "yml", "yaml"),
    "newsroom/data"  to listOf("yml", "yaml"),
    "newsroom/config" to listOf("yml", "yaml"),
)

private val SINGLE_FILES: List<String> = listOf("hugo.toml")

// ─── Extraction ──────────────────────────────────────────────────────────────

/**
 * Matches http/https URLs. Intentionally permissive; we clean up trailing
 * punctuation in [cleanUrl] after matching.
 */
private val URL_REGEX = Regex("""https?://[^\s)"'<>\]}]+""")

/**
 * Strip trailing punctuation that the regex greedily captured and drop the
 * fragment — we check whether the resource is reachable, not the anchor.
 */
private fun cleanUrl(raw: String): String {
    var u = raw
    val trailing = setOf('.', ',', ';', ':', '!', '?', ')', ']', '}', '"', '\'', '*', '>', '`')
    while (u.isNotEmpty() && u.last() in trailing) u = u.dropLast(1)
    val hash = u.indexOf('#')
    if (hash >= 0) u = u.substring(0, hash)
    return u
}

/**
 * Extract every URL occurrence from a single file.
 *
 * feed.yml gets special treatment: the file holds both published and
 * unpublished items, and only items marked `accepted: y` end up on the
 * public site. Blocks whose `accepted:` is `n` are skipped.
 */
private fun extractFromFile(file: Path): List<FoundLink> {
    val lines = try {
        Files.readAllLines(file)
    } catch (_: Exception) {
        return emptyList()
    }

    if (file.name == "feed.yml") {
        return extractFromFeedYaml(file, lines)
    }

    val out = mutableListOf<FoundLink>()
    lines.forEachIndexed { idx, line ->
        URL_REGEX.findAll(line).forEach { match ->
            val url = cleanUrl(match.value)
            if (url.isNotEmpty()) {
                out += FoundLink(url = url, file = file, line = idx + 1, context = line.trim())
            }
        }
    }
    return out
}

/**
 * Parse feed.yml block-by-block. Each item starts with a line beginning
 * `- ` and runs until the next such line or end of file. A block is
 * "published" if any of its lines is `accepted: y`.
 */
private fun extractFromFeedYaml(file: Path, lines: List<String>): List<FoundLink> {
    val starts = mutableListOf<Int>()
    lines.forEachIndexed { idx, line ->
        if (line.startsWith("- ")) starts += idx
    }
    if (starts.isEmpty()) return emptyList()
    starts += lines.size

    val acceptedRegex = Regex("""^\s*accepted:\s*y\b""")
    val out = mutableListOf<FoundLink>()
    for (i in 0 until starts.size - 1) {
        val from = starts[i]
        val to = starts[i + 1]
        val block = lines.subList(from, to)
        val accepted = block.any { acceptedRegex.containsMatchIn(it) }
        if (!accepted) continue
        block.forEachIndexed { offset, line ->
            URL_REGEX.findAll(line).forEach { match ->
                val url = cleanUrl(match.value)
                if (url.isNotEmpty()) {
                    out += FoundLink(url = url, file = file, line = from + offset + 1, context = line.trim())
                }
            }
        }
    }
    return out
}

/** Walk every configured root and accumulate the URLs found. */
private fun scanProject(root: Path): List<FoundLink> {
    val all = mutableListOf<FoundLink>()
    for ((dir, exts) in SCAN_ROOTS) {
        val start = root.resolve(dir)
        if (!start.isDirectory()) continue
        Files.walk(start).use { stream ->
            stream
                .filter { it.isRegularFile() }
                .filter { p -> exts.any { e -> p.extension.equals(e, ignoreCase = true) } }
                .forEach { all += extractFromFile(it) }
        }
    }
    for (name in SINGLE_FILES) {
        val p = root.resolve(name)
        if (p.isRegularFile()) all += extractFromFile(p)
    }
    return all
}

// ─── Filtering ───────────────────────────────────────────────────────────────

/**
 * Hosts we never attempt to reach — either because they cannot be resolved
 * during a check run (e.g. javai.ch is served from this very repo and only
 * exists after deploy) or because they are reserved/examples.
 */
private val DEFAULT_SKIP_HOSTS: Set<String> = setOf(
    "localhost",
    "127.0.0.1",
    "example.com",
    "example.org",
    "javai.ch",
)

/** True if the URL targets a real external host that should be checked. */
private fun isExternal(url: String): Boolean = try {
    val host = URI.create(url).host ?: return false
    host.isNotBlank() && host.lowercase() !in DEFAULT_SKIP_HOSTS
} catch (_: Exception) {
    false
}

/**
 * Read an optional `.linkcheck-ignore` file: one URL substring per line,
 * blank lines and `#`-comments allowed (both whole-line and trailing).
 * Matches are done with `contains`.
 */
private fun loadIgnorePatterns(root: Path): List<String> {
    val p = root.resolve(".linkcheck-ignore")
    if (!p.isRegularFile()) return emptyList()
    return p.readLines()
        .map { line ->
            // Strip trailing `# comment`, then trim whitespace.
            val hash = line.indexOf('#')
            (if (hash >= 0) line.substring(0, hash) else line).trim()
        }
        .filter { it.isNotEmpty() }
}

// ─── Reachability check ──────────────────────────────────────────────────────

/**
 * Reachability rule: send a HEAD first, fall back to GET if the server
 * rejects HEAD. Classifies responses into Ok / Warning / Failure.
 *
 * Redirects are followed automatically via `HttpClient.Redirect.NORMAL`.
 */
class ReachabilityCheck(
    private val client: HttpClient,
    private val timeout: Duration = Duration.ofSeconds(20),
    /**
     * User-Agent string. Defaults to a current Firefox on macOS because a
     * large number of government and regulatory hosts (FINMA, NIST, CoE,
     * Congress, Defense Department CDNs) return 4xx/999 to unfamiliar
     * User-Agent strings. Using a real browser UA drops most of those
     * false positives. Hosts that still block (LinkedIn, CoE) need to
     * go in `.linkcheck-ignore`.
     */
    private val userAgent: String =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 14.6; rv:128.0) " +
            "Gecko/20100101 Firefox/128.0",
) : LinkCheckRule {

    override val name: String = "reachability"

    override fun evaluate(link: FoundLink): CompletableFuture<CheckOutcome> {
        val uri = try {
            URI.create(link.url)
        } catch (e: Exception) {
            return CompletableFuture.completedFuture(
                CheckOutcome.Failure(name, "malformed URI: ${e.message}")
            )
        }

        val head = buildRequest(uri, "HEAD")
        return client.sendAsync(head, HttpResponse.BodyHandlers.discarding())
            .handle<CheckOutcome?> { resp, err ->
                when {
                    err != null -> null  // fall through to GET
                    resp.statusCode() in retryWithGet -> null
                    else -> classify(resp.statusCode(), null)
                }
            }
            .thenCompose { early ->
                if (early != null) {
                    CompletableFuture.completedFuture(early)
                } else {
                    val get = buildRequest(uri, "GET")
                    client.sendAsync(get, HttpResponse.BodyHandlers.discarding())
                        .handle { resp, err -> classify(resp?.statusCode() ?: -1, err) }
                }
            }
    }

    private fun buildRequest(uri: URI, method: String): HttpRequest =
        HttpRequest.newBuilder(uri)
            .timeout(timeout)
            .header("User-Agent", userAgent)
            // Accept header mirrors what a real browser sends on navigation —
            // some WAFs reject `*/*` outright.
            .header(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif," +
                    "image/webp,*/*;q=0.8"
            )
            .header("Accept-Language", "en-US,en;q=0.9,de;q=0.8,fr;q=0.7,it;q=0.6")
            .method(method, HttpRequest.BodyPublishers.noBody())
            .build()

    /**
     * Classify an HTTP response.
     *
     * Only 404 and 410 are treated as hard failures — these are the statuses
     * that unambiguously mean "the resource is gone". Other 4xx responses
     * generally mean "the server refused our request" (bot detection, missing
     * auth, legal block) rather than "the page does not exist", so they are
     * downgraded to warnings. The definitive judgement for those requires
     * human follow-up or an ignore-list entry.
     */
    private fun classify(status: Int, err: Throwable?): CheckOutcome = when {
        err != null -> CheckOutcome.Failure(
            name, "request error: ${err.message ?: err::class.simpleName}"
        )
        status in 200..299 -> CheckOutcome.Ok(name, "HTTP $status")
        status in 300..399 -> CheckOutcome.Ok(name, "HTTP $status (redirect)")
        status == 404 -> CheckOutcome.Failure(name, "HTTP 404 (not found)")
        status == 410 -> CheckOutcome.Failure(name, "HTTP 410 (gone)")
        status == 429 ->
            CheckOutcome.Warning(name, "HTTP 429 (rate limited)")
        status in 400..499 ->
            CheckOutcome.Warning(name, "HTTP $status (server refused request — bot block or auth)")
        status in 500..599 ->
            CheckOutcome.Warning(name, "HTTP $status (server error, may be transient)")
        status == -1 -> CheckOutcome.Failure(name, "no response")
        status >= 900 ->
            CheckOutcome.Warning(name, "HTTP $status (non-standard, likely bot block)")
        else -> CheckOutcome.Failure(name, "HTTP $status")
    }

    companion object {
        /** Servers that return these for HEAD often work fine for GET. */
        private val retryWithGet = setOf(403, 405, 501)
    }
}

// ─── Pipeline ────────────────────────────────────────────────────────────────

/** A URL, every place it was found, and the outcomes of every rule run on it. */
data class UrlResult(
    val url: String,
    val locations: List<FoundLink>,
    val outcomes: List<CheckOutcome>,
)

/**
 * Run every rule against every unique URL, bounded by [maxConcurrency].
 * Rules for a single URL are launched simultaneously; the semaphore limits
 * the number of URLs in flight at once.
 */
private fun runChecks(
    links: List<FoundLink>,
    rules: List<LinkCheckRule>,
    maxConcurrency: Int,
): List<UrlResult> {
    val grouped: Map<String, List<FoundLink>> = links.groupBy { it.url }
    val sem = Semaphore(maxConcurrency)

    val futures: List<CompletableFuture<UrlResult>> = grouped.entries.map { (url, locs) ->
        sem.acquire()
        val representative = locs.first()
        val perRule: List<CompletableFuture<CheckOutcome>> =
            rules.map { it.evaluate(representative) }
        val all = CompletableFuture.allOf(*perRule.toTypedArray())
        all.whenComplete { _, _ -> sem.release() }
        all.thenApply { UrlResult(url, locs, perRule.map { it.join() }) }
    }
    return futures.map { it.join() }
}

// ─── Reporting ───────────────────────────────────────────────────────────────

private fun report(results: List<UrlResult>, root: Path) {
    val failures = results.filter { r -> r.outcomes.any { it is CheckOutcome.Failure } }
    val warnings = results.filter { r ->
        r.outcomes.none { it is CheckOutcome.Failure } &&
            r.outcomes.any { it is CheckOutcome.Warning }
    }
    val oks = results.size - failures.size - warnings.size

    println()
    println("=== Link check report ===")
    println("Unique URLs checked: ${results.size}")
    println("  ok:       $oks")
    println("  warnings: ${warnings.size}")
    println("  failures: ${failures.size}")
    println()

    if (failures.isNotEmpty()) {
        println("-- Failures --")
        for (r in failures) {
            println("[FAIL] ${r.url}")
            r.outcomes.filterIsInstance<CheckOutcome.Failure>().forEach {
                println("       [${it.rule}] ${it.detail}")
            }
            r.locations.forEach { loc ->
                println("       at ${root.relativize(loc.file)}:${loc.line}")
            }
            println()
        }
    }

    if (warnings.isNotEmpty()) {
        println("-- Warnings --")
        for (r in warnings) {
            println("[WARN] ${r.url}")
            r.outcomes.filterIsInstance<CheckOutcome.Warning>().forEach {
                println("       [${it.rule}] ${it.detail}")
            }
            r.locations.forEach { loc ->
                println("       at ${root.relativize(loc.file)}:${loc.line}")
            }
            println()
        }
    }
}

/**
 * Write a GitHub-flavoured markdown summary to [outputPath].
 *
 * Called when `LINKCHECK_REPORT_MD` env var or `-Dlinkcheck.report.md` system
 * property is set. The CI workflow uses this file as both the contents of
 * `$GITHUB_STEP_SUMMARY` and the body of the "link check" tracking issue.
 */
private fun writeMarkdownReport(
    results: List<UrlResult>,
    root: Path,
    ignorePatternCount: Int,
    totalOccurrences: Int,
    outputPath: Path,
) {
    val failures = results.filter { r -> r.outcomes.any { it is CheckOutcome.Failure } }
    val warnings = results.filter { r ->
        r.outcomes.none { it is CheckOutcome.Failure } &&
            r.outcomes.any { it is CheckOutcome.Warning }
    }
    val oks = results.size - failures.size - warnings.size

    val sb = StringBuilder()
    sb.appendLine("## Link check report")
    sb.appendLine()
    sb.appendLine("Scanned **$totalOccurrences** URL occurrences — **${results.size}** unique external URLs checked.")
    sb.appendLine()
    sb.appendLine("| Status | Count |")
    sb.appendLine("|---|---:|")
    sb.appendLine("| OK       | $oks |")
    sb.appendLine("| Warnings | ${warnings.size} |")
    sb.appendLine("| Failures | ${failures.size} |")
    sb.appendLine()
    if (ignorePatternCount > 0) {
        sb.appendLine("_$ignorePatternCount ignore pattern(s) applied from `.linkcheck-ignore`._")
        sb.appendLine()
    }

    if (failures.isEmpty() && warnings.isEmpty()) {
        sb.appendLine("All external URLs reachable.")
        sb.appendLine()
    }

    if (failures.isNotEmpty()) {
        sb.appendLine("### Failures")
        sb.appendLine()
        for (r in failures) {
            sb.appendLine("#### ${r.url}")
            sb.appendLine()
            r.outcomes.filterIsInstance<CheckOutcome.Failure>().forEach {
                sb.appendLine("- **${it.rule}**: ${it.detail}")
            }
            sb.appendLine()
            sb.appendLine("Found in:")
            r.locations.forEach { loc ->
                sb.appendLine("- `${root.relativize(loc.file)}:${loc.line}`")
            }
            sb.appendLine()
        }
    }

    if (warnings.isNotEmpty()) {
        sb.appendLine("<details>")
        sb.appendLine("<summary>Warnings (${warnings.size}) — not failing the build</summary>")
        sb.appendLine()
        for (r in warnings) {
            sb.appendLine("**${r.url}**")
            sb.appendLine()
            r.outcomes.filterIsInstance<CheckOutcome.Warning>().forEach {
                sb.appendLine("- ${it.rule}: ${it.detail}")
            }
            r.locations.forEach { loc ->
                sb.appendLine("- at `${root.relativize(loc.file)}:${loc.line}`")
            }
            sb.appendLine()
        }
        sb.appendLine("</details>")
        sb.appendLine()
    }

    Files.writeString(outputPath, sb.toString())
}

// ─── Entry point ─────────────────────────────────────────────────────────────

fun main(args: Array<String>) {
    val rootStr = System.getProperty("linkcheck.root")
        ?: System.getenv("LINKCHECK_ROOT")
        ?: "."
    val root = Path.of(rootStr).toAbsolutePath().normalize()
    val concurrency = System.getProperty("linkcheck.concurrency")?.toIntOrNull() ?: 16
    val timeoutSec = System.getProperty("linkcheck.timeout")?.toLongOrNull() ?: 20L
    val strict = "--strict" in args
    val listOnly = "--list" in args

    println("javai.ch link checker")
    println("  root:        ${root.absolutePathString()}")
    println("  concurrency: $concurrency")
    println("  timeout:     ${timeoutSec}s")
    if (strict) println("  strict:      warnings will fail the build")
    println()

    val ignore = loadIgnorePatterns(root)
    if (ignore.isNotEmpty()) {
        println("Ignore patterns from .linkcheck-ignore: ${ignore.size}")
    }

    val scanned = scanProject(root)
    val external = scanned.filter { isExternal(it.url) }
    val filtered = external.filter { link -> ignore.none { link.url.contains(it) } }
    val uniqueUrls = filtered.distinctBy { it.url }.size

    println("Discovered ${scanned.size} URL occurrences")
    println("  external:   ${external.size}")
    println("  after ignore: ${filtered.size}")
    println("  unique:     $uniqueUrls")
    println()

    if (listOnly) {
        println("-- URLs (list-only mode, no requests issued) --")
        filtered.distinctBy { it.url }.sortedBy { it.url }.forEach {
            println("  ${it.url}")
            println("    at ${root.relativize(it.file)}:${it.line}")
        }
        exitProcess(0)
    }

    val client = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(timeoutSec))
        .build()

    // Rule registry — append new rules here. Example future rules:
    //   ContextMatchCheck(anthropicClient),   // LLM: does the target match the anchor?
    //   LastModifiedCheck(),                  // warn if server reports a very old page
    //   CertificateCheck(),                   // warn on soon-to-expire TLS certs
    val rules: List<LinkCheckRule> = listOf(
        ReachabilityCheck(client, Duration.ofSeconds(timeoutSec)),
    )

    val results = runChecks(filtered, rules, concurrency)
    report(results, root)

    // Optional markdown report — picked up by the CI workflow for the GitHub
    // step summary and the tracking issue body. Empty string means "not set",
    // so that Gradle's `environment("FOO", System.getenv("FOO") ?: "")`
    // forwarding idiom works without the script tripping on it.
    val markdownPath = (System.getProperty("linkcheck.report.md")
        ?: System.getenv("LINKCHECK_REPORT_MD"))
        ?.takeIf { it.isNotBlank() }
    if (markdownPath != null) {
        writeMarkdownReport(
            results = results,
            root = root,
            ignorePatternCount = ignore.size,
            totalOccurrences = scanned.size,
            outputPath = Path.of(markdownPath),
        )
        println("Markdown report written to: $markdownPath")
    }

    val hasFailure = results.any { r -> r.outcomes.any { it is CheckOutcome.Failure } }
    val hasWarning = results.any { r -> r.outcomes.any { it is CheckOutcome.Warning } }
    val exit = when {
        hasFailure -> 1
        strict && hasWarning -> 1
        else -> 0
    }
    exitProcess(exit)
}

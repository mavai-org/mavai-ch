plugins {
    java
    application
    kotlin("jvm") version "2.1.20"
}

group = "org.mavai"
version = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

repositories {
    mavenCentral()
}

// ── Source set for standalone scripts (link checker, etc.) ──────────────
// Kept separate from `main` so script code does not leak into the app jar.
sourceSets {
    create("scripts") {
        kotlin.srcDir("src/scripts/kotlin")
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

// Align the scripts Java compile target with the Kotlin target to avoid the
// JVM-target consistency check (Kotlin 2.1.x caps out below JVM 25).
tasks.named<JavaCompile>("compileScriptsJava") {
    options.release.set(21)
}

dependencies {
    implementation("org.mavai:mavai-newsroom:0.1.0-SNAPSHOT")
    testImplementation(testFixtures("org.mavai:mavai-newsroom:0.1.0-SNAPSHOT"))
    testImplementation("org.javai:punit-junit5:0.4.0")
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    "scriptsImplementation"(kotlin("stdlib"))
}

application {
    mainClass = "org.mavai.ch.Main"
}

tasks.test {
    useJUnitPlatform {
        excludeTags("punit-experiment")
    }
    filter {
        excludeTestsMatching("*RelevanceClassificationTest*")
        isFailOnNoMatchingTests = false
    }
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

// Probabilistic tests — sample-level failures are expected; the aggregate
// statistical verdict determines pass/fail, not individual samples.
tasks.register<Test>("probabilisticTest") {
    description = "Runs probabilistic tests (sample failures do not fail the build)"
    group = "verification"
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform()
    filter {
        includeTestsMatching("*RelevanceClassificationTest*")
    }
    ignoreFailures = true
    environment("ANTHROPIC_API_KEY", System.getenv("ANTHROPIC_API_KEY") ?: "")
    systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// Feed generation — consolidated + per-sector
// ═══════════════════════════════════════════════════════════════════════════

val feedFile = rootProject.file("newsroom/data/feed.yml").absolutePath
val configDir = rootProject.file("newsroom/config").absolutePath
val siteDir = layout.buildDirectory.dir("site").get().asFile.absolutePath
val siteUrl = "https://mavai.ch/"

// Sector definitions — add new sectors here
data class Sector(val id: String, val title: String, val description: String, val tags: List<String>)

val sectors = listOf(
    Sector(
        "banking",
        "mavai.ch — Banking & Finance",
        "AI regulation news for Swiss banking and financial services",
        listOf("financial")
    ),
    Sector(
        "pharma",
        "mavai.ch — Pharma & Healthcare",
        "AI regulation news for pharmaceutical, biotech, and healthcare sectors",
        listOf("pharma", "biotech", "medicine", "health")
    ),
    Sector(
        "federal-government",
        "mavai.ch — Federal Government",
        "AI regulation news from Swiss federal authorities",
        listOf("swiss", "policy", "federal")
    ),
)

// ── Tag alignment check ─────────────────────────────────────────────────
// Ensures every tag in data/sectors.json exists in at least one source
// in newsroom/config/sources.yml.

tasks.register("validateTags") {
    description = "Check that all sector tags exist in sources.yml"
    group = "newsroom"
    doLast {
        val sectorsFile = rootProject.file("data/sectors.json")
        val sourcesFile = rootProject.file("newsroom/config/sources.yml")

        // Collect all tags from sources.yml (simple regex — no YAML parser needed)
        val tagPattern = Regex("""tags:\s*\[([^\]]+)]""")
        val sourceTags = sourcesFile.readText().let { text ->
            tagPattern.findAll(text).flatMap { match ->
                match.groupValues[1].split(",").map { it.trim().trim('"') }
            }.toSet()
        }

        val errors = mutableListOf<String>()

        // Check sectors.json tags
        val sectorTagPattern = Regex(""""tags"\s*:\s*\[([^\]]+)]""")
        val sectorEntries = sectorTagPattern.findAll(sectorsFile.readText()).toList()
        val idPattern = Regex(""""id"\s*:\s*"([^"]+)"""")
        val ids = idPattern.findAll(sectorsFile.readText()).map { it.groupValues[1] }.toList()

        sectorEntries.forEachIndexed { index, match ->
            val tags = match.groupValues[1].split(",").map { it.trim().trim('"') }
            val sectorId = ids.getOrElse(index) { "unknown" }
            tags.filter { it !in sourceTags }.forEach { tag ->
                errors.add("Sector '$sectorId' uses tag '$tag' which does not exist in any source in newsroom/config/sources.yml")
            }
        }

        if (errors.isNotEmpty()) {
            throw GradleException("Tag alignment errors:\n  ${errors.joinToString("\n  ")}")
        }
        logger.lifecycle("Tag validation passed: all sector tags exist in sources.yml")
    }
}

// Curate task — fetch, filter, dedup, write candidates to feed.yml
tasks.register<JavaExec>("curateNews") {
    description = "Fetch news and write candidates to feed.yml for curation"
    group = "newsroom"
    dependsOn("classes")
    mainClass = "org.mavai.ch.Main"
    classpath = sourceSets["main"].runtimeClasspath
    args = mutableListOf("curate", "--config=$configDir", "--feed=$feedFile")
    if (project.hasProperty("tiers")) {
        args("--tiers=${project.property("tiers")}")
    }
    if (project.hasProperty("dryRun")) {
        args("--dry-run")
    }
    environment("ANTHROPIC_API_KEY", System.getenv("ANTHROPIC_API_KEY") ?: "")
}

// Consolidated feed — all accepted items, written to build/site/
tasks.register<JavaExec>("generateFeed") {
    description = "Generate consolidated feed (all accepted items)"
    group = "newsroom"
    dependsOn("classes")
    mainClass = "org.mavai.ch.Main"
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf(
        "generate",
        "--feed=$feedFile",
        "--output=$siteDir",
        "--site-url=$siteUrl"
    )
}

// Per-sector feed tasks — filtered by tags, written to build/site/<sector>/
sectors.forEach { sector ->
    tasks.register<JavaExec>("generateFeed-${sector.id}") {
        description = "Generate ${sector.title} feed (tags: ${sector.tags})"
        group = "newsroom"
        dependsOn("classes")
        mainClass = "org.mavai.ch.Main"
        classpath = sourceSets["main"].runtimeClasspath
        args = listOf(
            "generate",
            "--feed=$feedFile",
            "--output=$siteDir/${sector.id}",
            "--tags=${sector.tags.joinToString(",")}",
            "--title=${sector.title}",
            "--description=${sector.description}",
            "--site-url=${siteUrl}${sector.id}/"
        )
    }
}

// Generate everything — consolidated + all sector feeds
tasks.register("generateAllFeeds") {
    description = "Generate consolidated feed and all sector feeds"
    group = "newsroom"
    dependsOn("validateTags")
    dependsOn("generateFeed")
    dependsOn(sectors.map { "generateFeed-${it.id}" })
}

// ═══════════════════════════════════════════════════════════════════════════
// Link checker — validate every URL that ends up in published content
// ═══════════════════════════════════════════════════════════════════════════

tasks.register<JavaExec>("checkLinks") {
    description = "Validate that URLs in published content are reachable"
    group = "verification"
    dependsOn("scriptsClasses")
    mainClass = "org.mavai.ch.linkcheck.LinkCheckKt"
    classpath = sourceSets["scripts"].runtimeClasspath
    workingDir = rootProject.projectDir
    systemProperty("linkcheck.root", rootProject.projectDir.absolutePath)
    // Explicitly forward the markdown-report path env var. This is more
    // reliable than relying on JavaExec's inherited environment because
    // the Gradle daemon may have been started before the env var was set.
    environment("LINKCHECK_REPORT_MD", System.getenv("LINKCHECK_REPORT_MD") ?: "")
    if (project.hasProperty("strict")) args("--strict")
    if (project.hasProperty("listOnly")) args("--list")
}

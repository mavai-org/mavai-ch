# Directive: SEO improvements for mavai.ch

**Status:** implemented — external GSC follow-up pending (javai.org Change of Address; see Operational notes)
**Created:** 2026-05-21
**Target repo:** mavai-org/mavai-ch (this repo)
**Specification (not template):** the equivalent mavai.org work — mavai-org/mavai-org.github.io PR #6, commit `32d7251` ("Add SEO structured data, pillar page, and crawler hints"). Use it as a statement of *what* to achieve; adapt *how* to mavai.ch's multilingual, regulation-focused structure.

## Why

mavai.ch is not being picked up well in search. The sister site mavai.org
received a round of SEO improvements (structured data, a pillar page, crawler
hints, a search-console verification hook). mavai.ch should get the equivalent,
adapted to its differences.

**mavai.ch is materially different from mavai.org and the work must respect that:**

- **Multilingual.** Four languages (en/de/fr/it) with
  `defaultContentLanguageInSubdir = true`, so URLs are `/en/…`, `/de/…`, etc.
  `head.html` already emits `hreflang` alternates — preserve and build on that.
  All new structured data must carry the correct `inLanguage` per page, and any
  new pillar page must exist in **all four languages**.
- **No "punit" name-collision problem.** mavai.org's core SEO problem was the
  contested "punit" term. mavai.ch's discoverability target is *regulatory
  intent*: "AI regulation Switzerland", "FINMA AI requirements", "ISO 42001
  Switzerland", "KI-Regulierung Schweiz", "réglementation IA Suisse", and the
  compliance questions Swiss enterprises and cantonal government actually search.
- **Different brand/site identity.** Site title "Mavai Schweiz", OG image
  `images/hero.jpg` (there is no `og-default.png`), separate Plausible script.
- **Separate search-console property and DNS.** mavai.ch is verified
  independently from mavai.org, with its own token and its own DNS records.

## Scope of work

### 1. Structured data (JSON-LD) in `layouts/partials/head.html`

Mirror the mavai.org additions, adapted for multilingual output:

- `Organization` + `WebSite` on every page. `Organization.sameAs` should link
  the mavai GitHub org and the relevant LinkedIn / company profiles, and SHOULD
  cross-link **mavai.org** so the two sites are understood as one entity.
- `Article` on dated posts (the `posts` / "Insights" section, and `regulations`
  if those carry dates). Include `headline`, `description`, `datePublished`,
  `dateModified` (if `lastmod` present), `author`, `image`, `publisher`, and
  **`inLanguage` set to the page's `.Lang`**.
- Use `mainEntityOfPage`/`url` = `.Permalink`.
- **Critical Hugo gotcha (already hit on mavai.org):** JSON-LD emitted inside
  `<script type="application/ld+json">` gets double-escaped by Hugo's JS-context
  escaper. Pipe through `safeJS`: `{{ $data | jsonify | safeJS }}`. Validate the
  built output parses as JSON before considering it done.

### 2. Search-engine verification hook

- Add empty `googleSiteVerification` and `bingSiteVerification` params (in the
  top-level `[params]` block, shared across languages), and emit the meta tags
  in `head.html` when set. The site owner pastes mavai.ch's *own* tokens — do
  not reuse mavai.org's.
- Note for the owner: mavai.ch verification is a separate Google Search Console
  property with its own DNS TXT record. Prefer the DNS/Domain verification
  method (a `google-site-verification=…` TXT record at the apex). Watch for the
  failure mode seen on mavai.org: a too-short or fragment value in DNS that is
  not the full `google-site-verification=…` token.

### 3. Crawler hints

- Add `layouts/robots.txt` emitting `User-agent: * / Allow: / / Sitemap:`.
  **Multilingual caveat:** with four languages Hugo produces a sitemap index at
  `/sitemap.xml` referencing per-language sitemaps — confirm the `Sitemap:`
  line points at the index and that it resolves after build.
- Add `/llms.txt` (place under `static/`, or generate per-language if
  warranted). List the key regulation/insight pages and projects in clean text.
  Unlike mavai.org's, there is no name to disambiguate; instead state plainly
  that mavai.ch covers Swiss AI regulation and links to mavai.org for the
  testing tooling.

### 4. Pillar page(s) — regulatory intent

The legitimate equivalent of mavai.org's `/probabilistic-testing/` pillar page,
but aimed at compliance search intent. A strong candidate:
"AI regulation in Switzerland: what's changing and what to do about it" —
covering FINMA expectations, ISO 42001, the EU AI Act's Swiss spillover, and
linking internally to the regulation-news and insights sections, and outward to
mavai.org's testing tooling as the practical answer.

- **Must be authored in all four languages** (`content/{en,de,fr,it}/`), with
  proper translations and `hreflang` wiring (Hugo handles this when the
  translations share a translation key / path).
- Add to each language's `[[languages.<lang>.menus.main]]` block with a
  localised nav label.
- This is a genuine, useful page — **not** a keyword-stuffed document. Keyword
  stuffing hurts rankings; thorough, well-linked topical content is what helps.

### 5. Per-post Open Graph image (minor parity gap)

mavai.ch's `head.html` only emits the site-wide `ogImage`. Add per-page
override (`.Params.image` wins, else site default) as mavai.org does, and add
the `article:published_time` / `article:modified_time` OG tags on dated pages.

## Operational notes

### 2026-06-01 — Search Console "Change of Address" for the old domains

Post-migration, the old domains 301-redirect to the new ones (verified live):
`javai.ch` + `www.javai.ch` → `mavai.ch`, `javai.org` → `mavai.org`. Both old
zones are fronted by the same Cloudflare account (nameservers
`sam`/`rosemary.ns.cloudflare.com`), serve an identical managed robots.txt
(Googlebot allowed for search; only `Google-Extended` / AI-training crawlers
disallowed), and 301 cleanly over IPv4 **and** IPv6, including under the
Googlebot user-agent. The destinations (`mavai.org`, `mavai.ch`) are on GitHub
Pages (`185.199.108–111.153`, IPv4 only).

- **`javai.ch` Change of Address: succeeded.**
- **`javai.org` Change of Address: failed** with *"Couldn't fetch the page
  http://javai.org/"*.

Diagnosis: this is a **fetch failure, not a redirect failure** — the redirect
and DNS for `javai.org` were checked at every layer Googlebot could use (http,
https, IPv6, Googlebot UA, robots.txt) and are byte-for-byte identical to the
`javai.ch` setup that succeeded. There is no live-infrastructure difference, so
the failure is almost certainly **transient** (a momentary Cloudflare challenge
or fetch timeout when Google tried) rather than a misconfiguration.

Remedy, in order:
1. **Retry** the Change of Address for `javai.org` after a day — usually clears
   when the redirect is demonstrably good.
2. Confirm GSC parity with the working `.ch` move: the destination `mavai.org`
   is a **verified, Owner-level** property in the same account; the old
   `javai.org` property is still verified and Owner-level; and old/new property
   **types match** (Domain → Domain, as used for `.ch`).
3. Check Cloudflare **Bot Fight Mode** on the `javai.org` zone (Security → Bots):
   ensure verified search bots are allowed, so Googlebot is not intermittently
   challenged.

Reassurance: even if Change of Address never goes through, the **301 redirects
are what pass link equity and rankings** — those are solid and permanent. The
tool is an accelerant/signal to Google, not a prerequisite; the
`javai.org` → `mavai.org` migration is already doing its job.

## Out of scope

- Backlink building and content cadence (ongoing, owner-driven).
- Any change to mavai.org (already done).

## Acceptance criteria

- Site builds clean with `hugo --minify`.
- Every emitted JSON-LD block parses as valid JSON (verify against built output,
  not just source), and carries the correct per-page `inLanguage`.
- `hreflang` alternates remain correct and are not regressed.
- `robots.txt` references a sitemap URL that resolves post-build.
- The pillar page exists and is navigable in all four languages, with working
  cross-language `hreflang`.
- Verification params exist but are empty (owner supplies tokens).

## Workflow

Implement on a feature branch in this repo, mirroring the mavai.org PR. Keep the
commit focused on SEO; do not fold in unrelated content changes. Reference this
directive in the PR description.

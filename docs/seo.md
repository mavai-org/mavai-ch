# Directive: SEO improvements for mavai.ch

**Status:** open
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

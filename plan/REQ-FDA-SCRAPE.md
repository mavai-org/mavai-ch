# FDA AI Coverage — Scraping Source Specification

## Overview

Add coverage of the U.S. Food and Drug Administration (FDA) to the
javai-ch newsroom pipeline. The FDA does not publish a dedicated
AI-focused feed, so coverage must be obtained by scraping a small set
of FDA HTML surfaces and filtering each for AI relevance.

The work in this requirement is **architectural as well as editorial**:
the same scraping pattern is expected to apply to other regulators and
sectors in future (for example, the UK National Health Service). The
implementation must therefore be **modular and reusable**, with
FDA-specific configuration plugging into a generic mechanism that lives
in the shared `../javai-newsroom` library.

## Background

An earlier `sources.yml` entry under the id `fda-ai` fetched the
generic FDA Drug News RSS feed. Despite its id, that feed contained
press releases, generic approvals, warning letters, and recalls — none
of which were AI-specific. Over the lifetime of the pipeline that
source contributed no items to `newsroom/data/feed.yml` and was
removed.

A first-cut replacement was sketched against the FDA's curated *AI/ML-
Enabled Medical Devices* list. That approach was rejected during scope
review: it produces hundreds of per-device clearance records, none of
which are interesting to a Swiss compliance officer who wants to know
when the FDA changes its policy stance on AI in healthcare. The
javai.ch reader cares about **stipulations and announcements that
affect categories of devices or applications of AI** — not individual
510(k) clearances.

The replacement source set defined in this document targets that
broader, policy-level coverage.

## Goal

Provide a small set of FDA scrape sources that:

1. Surface FDA policy moves on AI — guidance documents, press
   announcements, digital-health updates — within one pipeline run of
   the FDA publishing them.
2. Discard the overwhelming majority of non-AI FDA output **before** it
   reaches the LLM relevance filter, so token spend stays bounded.
3. Detect FDA page restructures automatically and surface them as
   actionable GitHub issues, so a silently-broken scraper does not turn
   into a silently-empty feed.
4. Land as the **first consumer** of a generic, reusable scrape
   strategy that subsequent sources (e.g. NHS, EMA, SwissMedic) can
   plug into without further library work.

## Architecture

The implementation has two halves: a generic mechanism in the shared
library, and FDA-specific configuration in this repository.

### Generic mechanism (in `../javai-newsroom`)

Add a `ScrapeStrategy` abstraction that extends the existing
`html-scrape` fetcher with three orthogonal capabilities:

1. **Selectors** — exactly as today (`item`, `title`, `link`, `date`,
   optionally `summary`).
2. **Keyword pre-filter** — an optional list of AI-related substrings
   (`artificial intelligence`, `machine learning`, `algorithm`,
   `software as a medical device`, etc.). Items whose title and
   summary contain none of the substrings are discarded **before**
   they reach the LLM relevance filter. Items that match are passed
   through unchanged. The pre-filter is per-source: sources that don't
   need it omit the field and behave exactly as they do today.
3. **Structural litmus tests** — an optional list of assertions about
   the live page structure that should pass today and fail if the
   page is restructured. Each assertion is a CSS selector plus an
   expected condition (`exists`, `count >= N`, `text contains`,
   etc.). On the first failed assertion the strategy aborts the
   fetch for that source, logs the failure, and signals the
   workflow.

When a litmus test fails the GitHub Actions workflow opens an issue
in the consuming repository (here, javai-ch), mirroring the pattern
used by the link checker. The issue title names the source; the body
quotes the failed assertion and links to the live page so a human can
inspect the change. **The remaining sources continue to fetch
normally** — a single broken scraper must not block the rest of the
pipeline.

The abstraction must be designed so a future source — say UK NHS, or
SwissMedic — declares its own selectors, keywords, and litmus tests in
`sources.yml` without any code change in the library. FDA is the first
consumer; it must not be the only one possible.

### FDA-specific configuration (in `javai-ch`)

One entry in `newsroom/config/sources.yml`, targeting the only FDA
surface that is scrapable with the current Jsoup-based HTML fetcher:

| Source id   | FDA surface                        | Status      |
|-------------|------------------------------------|-------------|
| `fda-press` | FDA Newsroom — Press Announcements | Implemented |

Two originally-planned surfaces were found to be **not viable** during
live-page inspection and are deferred to a follow-up requirement:

| Surface                       | Blocker                                                                     |
|-------------------------------|-----------------------------------------------------------------------------|
| FDA Guidance Documents        | Table body is JavaScript-rendered (Drupal DataTables). Jsoup gets no rows. Requires a headless browser fetcher or discovery of the underlying AJAX/JSON endpoint. |
| FDA Digital Health CoE        | Static hub page with no dates, no summaries, and no chronological content. Not a news feed. |

The implemented `fda-press` entry uses the new scrape-strategy fields:
- selectors derived from live page inspection (Drupal Views listing);
- the AI keyword pre-filter, so non-AI press releases are discarded at
  fetch time;
- three structural litmus tests that detect page restructures;
- the existing LLM relevance filter applied to whatever survives the
  pre-filter (i.e. **no `bypassFilter` flag**).

Tier assignment: all three sources start at **tier 2** (weekly fetch)
to bound token spend during initial bedding-in. Promotion to tier 1
(daily) is a follow-up decision once we have observed the volume.

## Tag mapping

Items from all three FDA sources are tagged for the existing **health**
sector defined in `data/sectors.json`. No new sector is created. The
`fda` and `us` tags remain in the vocabulary but are not promoted to
sector-level keys. Items that are clearly about software or devices may
additionally carry the `medical-device` or `software` tag, but these
are advisory, not aggregation keys.

## Backfill behaviour

The first run of each new source seeds the `StateManager` from the
items currently visible on the page and **proposes nothing** as a
candidate. Only items that appear after the first run are considered
new. This is the default pipeline behaviour and is the right one here:
a one-shot dump of every historical FDA press release into the
curation PR would drown the curator and produce no editorial value.

## Data shape produced by the fetcher

Each surviving item becomes a candidate in `feed.yml` in the standard
shape:

```yaml
- title: "FDA issues final guidance on AI/ML-enabled device software functions"
  url: "https://www.fda.gov/..."
  date: "2026-04-02"
  source: "FDA"
  summary: "<scraped from the page; may be empty for sources where the surface lists titles only>"
  tags: ["us", "health", "ai", "regulatory", "fda"]
  relevance: "<populated by LLM relevance filter>"
  accepted: n
  featured: n
```

The summary field is intentionally minimal: no secondary fetch of the
linked record, no enrichment. Curators visit the source page when they
need more context. (This deliberately defers the richer-summary
question to a later requirement.)

## Scope of this work

**In scope**

1. Add the `ScrapeStrategy` abstraction (selectors + keyword pre-filter
   + litmus tests + issue-on-failure hook) to `../javai-newsroom` on a
   sibling `feature/fda-scrape` branch. Existing sources continue to
   work unchanged.
2. Inspect the live FDA Press Announcements page and derive working
   selectors and litmus tests. (Guidance Documents and Digital Health
   CoE were inspected and found not viable — see deferred surfaces
   table above.)
3. Add `fda-press` to `newsroom/config/sources.yml` in javai-ch,
   configured as a `ScrapeStrategy` consumer with keyword pre-filter
   and structural checks.
4. Confirm `data/sectors.json` already routes the `health` tag to the
   health sector feed; no schema change required.
5. Run the local dry-run gates (`validateTags`, `fetchNews -Ptiers=2
   -PdryRun`, `checkLinks`) and confirm zero errors.
6. Commit on `feature/fda-scrape` in both repositories. Do not push.
   Do not open PRs. Do not mark any candidate `accepted: y`.

**Out of scope**

- Scraping any other FDA surface (CDER newsletters, warning letters,
  recall announcements, individual device clearance records).
- Bypassing the LLM relevance filter for any source. Revisit only if
  token cost becomes a measurable problem.
- Translating FDA titles into German, French, or Italian. The
  pipeline already passes through the original-language title for all
  sources.
- Fetching the linked detail page to enrich the summary. Defer to a
  later requirement.
- Promoting any FDA source from tier 2 to tier 1. Defer until we have
  observed the volume in production.
- Working around any FDA IP-reputation block on the Jsoup HTTP stack.
  If the FDA host blocks the newsroom fetcher (separately from the
  link-checker block already documented), that is a problem to solve
  in the newsroom library, not here.
- Implementing additional consumers of `ScrapeStrategy` (NHS,
  SwissMedic, etc.). The architecture must permit them; this
  requirement does not deliver them.

## Acceptance criteria

A pull request implementing this requirement is complete when:

1. `./gradlew validateTags` passes — all tags emitted by the new
   sources are already in use or already mapped to a sector.
2. `./gradlew fetchNews -Ptiers=2 -PdryRun` completes without error
   against the new `fda-press` source. Because the StateManager seeds on
   first fetch, the run is **not** required to produce candidate
   items; it is required to complete cleanly and to log that each
   source's litmus tests passed.
3. `./gradlew checkLinks` passes against the new source URLs. If the
   FDA host blocks the link checker from CI (IP-reputation WAF), the
   host is added to `.linkcheck-ignore` with a comment explaining why
   and the decision is noted in the change log of this document.
4. The `ScrapeStrategy` abstraction in `../javai-newsroom` carries
   unit tests covering: keyword pre-filter pass/fail, litmus-test
   pass/fail, and that an existing `html-scrape` source without any
   strategy fields continues to behave as it does today.
5. A documented mechanism exists for the GitHub Actions workflow to
   open an issue when a litmus test fails. (The mechanism is
   exercised by a unit test or workflow dry-run; an actual issue is
   not required.)
6. No existing source's behaviour is changed. The Swiss federal
   administration source, the EU sources, and any other existing
   `html-scrape` source produce identical output before and after
   this change.

## Design decisions (resolved)

1. **Litmus test philosophy**: assertions must centre on whether
   meaningful content can be located — not on cosmetic DOM details.
   The key signal is: if a previous scrape of this source successfully
   produced meaningful content, but the current scrape produces none,
   the assertion has failed. The litmus tests are therefore a
   comparison against prior successful state, not a static list of
   CSS class names. Implementer's judgement on the specific assertions,
   but they must be few, specific, and load-bearing — three or four
   per source.

2. **Source tier promotion**: confirmed. All three FDA sources start at
   tier 2 (weekly). Promotion to tier 1 is a manual `sources.yml`
   edit after observing volume. No code change required; not part of
   this requirement.

3. **NHS as abstraction stress-test**: preliminary research on NHS AI
   communications is documented in
   [`docs/nhs_ai_communications_summary.md`](../docs/nhs_ai_communications_summary.md).
   The NHS landscape spans four distinct domains (`digital.nhs.uk`,
   `transform.england.nhs.uk`, `www.england.nhs.uk`, NHS London),
   multiple page structures (knowledge repositories, guidance pages,
   press announcements, long-reads, PDFs), and four communication
   modes (policy/governance, procurement/implementation, operational
   deployment, ethics/assurance). The `ScrapeStrategy` abstraction
   must be shaped so that these heterogeneous surfaces can each be
   declared as a standalone `sources.yml` entry — different selectors,
   different keyword filters, different litmus tests — without any
   library code change. The implementer must sketch at least one NHS
   source entry during development and confirm the abstraction
   supports it.

## Change log

- **2026-04-14** — `www.fda.gov` added to `.linkcheck-ignore`. The host
  returns HTTP 404 to non-browser user agents (WAF / IP-reputation) while
  remaining reachable in a real browser. This matches the precedent set
  by `www.finma.ch`, `nvlpubs.nist.gov`, and other hosts already on the
  list. The same WAF behaviour causes the Jsoup-based fetcher to return
  0 items from `fda-press`; that fetcher-side workaround is out of scope
  for this requirement (see "Out of scope") and should be raised as a
  follow-up against `javai-newsroom`.

## Related documents

- `README.md` — feed curation workflow, how a curator accepts items.
- `CLAUDE.md` — pipeline architecture, relevance filter overview.
- `../javai-newsroom/README.md` — fetcher implementations, including
  the Jsoup-based HTML scrape fetcher that `ScrapeStrategy` extends.

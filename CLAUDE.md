# Purpose

This repository contains the source for [javai.ch](https://javai.ch/), a multilingual Hugo static website focused on AI regulation and compliance in Switzerland. It is the Swiss-focused companion to [javai.org](https://javai.org/), which hosts the technical open-source projects.

javai.ch covers standards and regulations that demand probabilistic testing of AI systems, with emphasis on:
- **FINMA** — Swiss financial market supervision and AI governance
- **ISO 42001** — international standard for AI management systems
- Other Swiss and international regulations as they emerge

The target audience is business managers, compliance officers, and IT leaders in Swiss enterprises and cantonal government — not developers. Content explains statistical and testing concepts in accessible, non-technical terms. Visitors seeking technical tools are directed to javai.org.

##Languages

The site is published in four languages: **English**, **German**, **French**, and **Italian**.

## Swiss German orthography

All German content **must** use Swiss Standard German (Schweizer Hochdeutsch) orthography. The key rule: **never use ß (Eszett)**. Always use **ss** instead.

Examples:
- ~~groß~~ → **gross**
- ~~daß~~ → **dass**
- ~~Straße~~ → **Strasse**
- ~~weiß~~ → **weiss**
- ~~Maßnahme~~ → **Massnahme**
- ~~gemäß~~ → **gemäss**
- ~~schließen~~ → **schliessen**
- ~~einschließlich~~ → **einschliesslich**

This applies to all German text: content files, i18n strings, commit messages, and any other German prose in this repository.

## Content guidelines

### ⚠️ NO RELATIVE DATES — use absolute dates only

**All content must use absolute dates (specific dates or months/years).** Never use relative references like "last week", "recently", "two months ago", "earlier this year", "currently", or "now". This applies to **all text**: opening sentences, summaries, body copy, and everywhere else. Relative dates become outdated quickly and damage journalistic credibility as content ages.

**Relative dates to avoid:** last week, last month, recently, a few years ago, currently, now, soon, earlier this year, two months ago, this April, this month

**Bad:** "Last week, the EU announced new AI compliance requirements..." or "In April meetings, UN officials exposed..."

**Good:** "In April 2026, the EU announced new AI compliance requirements..." or "At meetings from April 20–24, UN officials exposed..."

This ensures content remains accurate and contextual regardless of when it is read.

### Journalistic tone: hedge claims and avoid loaded adjectives

Write in a journalistic voice—reporting observations, not asserting conclusions. Use hedging language to maintain professional distance and credibility.

**Avoid unhedged claims:** "will determine," "will shape," "will prove," "has crystallised"

**Use hedging instead:**
- "will" → "could," "may," or "appears to"
- "determines" → "could influence," "may affect"
- "crucial," "enormous," "rare" → "significant," "notable," "focused"

**Examples:**

**Bad (assertive, opinion-sounding):**
- "This will shape global regulation for years"
- "A crucial moment in AI governance"
- "These debates will determine who benefits from AI"

**Good (journalistic, hedged):**
- "This could shape global regulation for years"
- "A significant moment in AI governance"
- "These debates could influence who benefits from AI"

This applies to **all content**: titles, descriptions, summaries, opening sentences, body text, and conclusions. Hedging maintains the credibility that comes from reporting facts rather than predicting outcomes.

## Content directory structure

```
content/
├── en/    # English content
├── de/    # German content (Swiss orthography)
├── fr/    # French content
└── it/    # Italian content
```

Each language has its own content directory. When adding new content, it must be created in all four languages.

# News feed pipeline

The site aggregates AI regulation news through an automated fetch-filter-publish pipeline.

## Architecture

- **javai-newsroom** is a shared Java library (not a standalone service), included via Gradle composite build from the sibling `../javai-newsroom` directory.
- **javai-ch** is the consuming project. It provides all configuration (sources, relevance prompt, seed items, sector definitions) and defines the GitHub Actions workflows. The library provides the fetching, filtering, state management, and feed generation machinery.

## End-to-end flow

The pipeline is driven by the `.github/workflows/fetch-news.yml` scheduled GitHub Action:

1. **Fetch** — Fetchers (RSS/Atom parser, HTML scraper via Jsoup) pull from sources configured in `newsroom/config/sources.yml` (~31 sources, 4 tiers). The schedule determines which tiers are fetched:
   - Daily (06:00 UTC): tiers 1–2
   - Weekly (Monday 07:00 UTC): tiers 1–3
   - Monthly (1st, 08:00 UTC): tiers 1–4
   - Manual dispatch is also available via `workflow_dispatch`.

2. **Deduplicate** — `StateManager` checks each item against `state.json` (persisted on the `data` git branch) using SHA-256 hashes of title+url+date. Only new/modified items proceed.

3. **Filter** — `RelevanceFilter` sends each new item to Claude (Haiku) with a strict relevance prompt (`newsroom/config/relevance-prompt.txt`). The prompt is narrowly scoped to concrete software developer obligations, technical standards, and AI-specific regulation — not general AI news. Items classified as `RELEVANT` get a reason attached; others are discarded. Filter accuracy is validated with probabilistic tests using punit (`src/test/java/org/javai/ch/RelevanceClassificationTest.java`).

4. **Merge seed items** — Pre-curated foundational documents (EU AI Act, FINMA guidance, ISO standards, etc. in `newsroom/config/seed-items.yml`) bypass the LLM filter entirely.

5. **Persist state** — Updated `state.json` is committed to the `data` branch using low-level git tree commands (no branch checkout).

6. **Generate feeds** — One consolidated feed plus 4 sector-specific feeds (banking, pharma, health, federal-government), each filtered by tags defined in `data/sectors.json`. Output is RSS (`feed.xml`) and JSON Feed (`feed.json`) format.

7. **Build & deploy** — Hugo builds the static site, feeds are merged into the output, and the result is deployed to GitHub Pages.

## Key configuration files

| File | Purpose |
|------|---------|
| `newsroom/config/sources.yml` | News source definitions (URLs, selectors, tiers, tags) |
| `newsroom/config/relevance-prompt.txt` | LLM system prompt for relevance classification |
| `newsroom/config/seed-items.yml` | Pre-curated foundational documents (bypass LLM filter) |
| `newsroom/config/relevance-test-data.yml` | Labelled test data for probabilistic filter validation |
| `newsroom/data/state.json` | Persistent fetch state (seen items, hashes, timestamps) |
| `data/sectors.json` | Sector definitions and tag mappings |
| `Newsroom_report.xlsx` | Curation planning spreadsheet (see below) |

## Newsroom Report

The **Newsroom_report.xlsx** Excel file is a curation planning tool and inventory of the news feed pipeline. It serves three functions:

1. **Feed Architecture** (Sheet 1) — Overview of all feeds and their subsections (Swiss Governance, EU/UK Governance, etc.), with resource counts and tags.

2. **Resource Links Catalog** (Sheet 2) — All curated resource links (manual links added to feed pages), organized by feed → subsection. Each row includes title, URL, summary, status, and last-checked date.

3. **Sources → Feeds Mapping** (Sheet 3) — All news sources from `sources.yml` with their tier, type, tags, and which feed(s) they feed into. Tier 1 sources are highlighted in green (daily fetch), Tier 2 in yellow (weekly), Tier 3 in red (monthly), Tier 4 in gray (manual/infrequent).

### Keeping the report in sync

**When updating `sources.yml`:**
- Add new sources to Sheet 3 with their tier, type, tags, and target feed(s)
- Update the "Primary Tags" column to reflect any tag changes
- Update feed counts in Sheet 1 if tier/feed assignments change

**When adding resource links to feed pages** (`content/*/feeds/*.md`):
- Add corresponding rows to Sheet 2 with the feed, subsection, title, URL, summary, and today's date in "Last Checked"
- Update resource counts in Sheet 1

**Maintenance:**
- Review "Last Checked" dates periodically to identify links needing re-verification
- Update dates when links are verified/tested

The report is the single source of truth for curation planning. Update it before submitting changes, so the next curator has a complete inventory.

## Gradle tasks

```
./gradlew fetchNews                # Fetch from configured sources (optional --Ptiers=1,2)
./gradlew generateFeed             # Generate consolidated RSS + JSON feed
./gradlew generateFeed-{sector}    # Generate sector-specific feed (e.g. generateFeed-banking)
./gradlew generateAllFeeds         # Run all feed generation tasks
./gradlew probabilisticTest        # Run LLM filter accuracy tests (requires ANTHROPIC_API_KEY)
```

# Technology

- **Hugo** static site generator (extended edition)
- **GitHub Pages** for hosting
- **GitHub Actions** for automated build and deploy on push to `main`

# Project instructions

See @README.md for project overview
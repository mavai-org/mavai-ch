# mavai.ch

The source for [mavai.ch](https://mavai.ch/) — a multilingual static website covering AI regulation and compliance in Switzerland. It is the Swiss-focused companion to [mavai.org](https://mavai.org/), which hosts the technical open-source projects.

The site serves as an information hub for parties interested in the latest AI regulation news of particular relevance to Switzerland. Sources include Swiss regulatory authorities and watchdogs (e.g. FINMA), EU regulators, international regulators where relevant for Switzerland, and international standards such as ISO 42001.

The site is published in four languages: English, German (Swiss orthography), French, and Italian.

## Prerequisites

- [Hugo extended edition](https://gohugo.io/installation/) (latest version)
- Git

## Getting started

Clone the repository and run the local development server:

```sh
git clone https://github.com/mavai-org/mavai-ch.git
cd mavai-ch
hugo server
```

The site will be available at `http://localhost:1313/`. Hugo watches for file changes and reloads automatically.

## Content structure

Content lives under `content/`, with a subdirectory per language (`en/`, `de/`, `fr/`, `it/`). New content must be created in all four languages.

See `CLAUDE.md` for content guidelines, including the Swiss German orthography rules.

## Drafts and ideas

The `drafts/` directory is for work in progress that is not ready for publication:

- `drafts/ideas/` — early-stage post ideas: a title, a few notes, maybe a link
- `drafts/wip/` — drafts being actively written

When a draft is ready for review, copy it into `content/<lang>/posts/` on a feature branch and open a pull request. Nothing in `drafts/` is ever published.

## Feed curation

The site publishes sector-specific feeds of AI regulation news (banking, pharma, health, federal government). New items are proposed automatically but nothing reaches the public site without human approval.

### How it works

An automated pipeline runs on a schedule. It fetches news from regulatory sources, filters out irrelevant items using an LLM, and then opens a pull request with the new candidates for review. No items are published until a curator approves them.

All feed items — published and unpublished — live in a single file: **`newsroom/data/feed.yml`**. This file is the source of truth for every feed on the site.

### What a curator sees

When the pipeline finds new items, you will receive a GitHub notification for a pull request titled **"Feed candidates for review"**. Open the PR and look at the changes to `newsroom/data/feed.yml`.

New items appear at the top of the file under a comment that reads:

```
# -- New candidates --------------------------------------------------------
```

Each item looks like this:

```yaml
- title: "EBA report on AI Act implications for EU banking"
  url: "https://www.eba.europa.eu/..."
  date: "2025-11-01"
  source: "EBA"
  summary: "Maps the AI Act against banking-sector obligations..."
  tags: ["global", "eu", "financial", "ai-act"]
  relevance: "Maps AI Act obligations onto banking software"
  accepted: n
  featured: n
```

### Approving or rejecting items

To **approve** an item, change `accepted: n` to `accepted: y`. The item will appear in the public feed once you merge the PR.

To **reject** an item, leave it as `accepted: n`. It will stay in the file (so the pipeline does not propose it again) but will never appear on the public site.

You can also:

- **Edit tags** to change which sector feeds the item appears in. Items tagged `global` appear in all sectors. Items tagged `financial` appear in the banking feed, and so on.
- **Feature an item** by changing `featured: n` to `featured: y`. Featured items are highlighted as key documents on their sector pages, in addition to appearing in the regular feed.
- **Unpublish an existing item** by changing its `accepted: y` back to `accepted: n` anywhere in the file.

When you are done, merge the pull request. The site rebuilds and deploys automatically.

### Duplicate detection

The pipeline automatically eliminates exact duplicates (same document arriving from different sources with slightly different URLs). You will never see these.

If the pipeline detects a *suspicious near-duplicate* — for example, an item with a very similar title to something already in the feed — it will flag it with a comment directly above the item:

```
# WARNING: POTENTIAL DUPLICATE (see "EBA report on AI Act implications..." below)
```

In this case, compare the two items and decide whether to approve one, both, or neither.

### Timing

The pipeline runs daily, weekly, and monthly depending on source priority. If a previous PR is still open when the next run happens, it is updated in place rather than creating a new one. Curation is expected to take a few minutes — review the candidates, set your approvals, and merge.

## Contributing workflow

1. Create a branch from `main` with a descriptive name, e.g. `post/march-compliance-update` or `regulation/new-finma-circular`
2. Add or edit content on that branch
3. Push the branch and open a pull request
4. A repository administrator reviews the content and merges to `main` if approved

Merging to `main` triggers deployment automatically — no manual publish step is needed.

## Deployment

The site is built and deployed to GitHub Pages via a [GitHub Actions workflow](.github/workflows/deploy.yml). On every push to `main`:

1. Hugo builds the site with `--minify`
2. The built output is deployed to GitHub Pages

The site is served at [mavai.ch](https://mavai.ch/) with HTTPS enforced.

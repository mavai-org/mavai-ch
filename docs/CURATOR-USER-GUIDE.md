# Curator's guide to the mavai.ch news feed

This guide explains how to review, approve, and manage news items for the
mavai.ch regulation feeds. It is written for curators who are not software
developers but are comfortable learning a few tools.

## What you are doing

The mavai.ch website publishes feeds of AI regulation news, organised by sector
(banking, pharma, health, federal government). An automated pipeline scans
regulatory sources and proposes new items. Your job is to decide which items
reach the public.

Nothing is published without your approval. You are the quality gate.

## How the process works

1. A scheduled pipeline fetches news from regulatory sources several times a
   week.
2. It filters out obviously irrelevant items automatically.
3. It places the remaining candidates into a file called `feed.yml` and asks
   you to review them by opening a **pull request** on GitHub.
4. You review the candidates, approve or reject each one, and merge the pull
   request.
5. The website rebuilds automatically with your approved items.

The whole review typically takes a few minutes.

## Key concepts

You only need to understand a handful of concepts to do this work.

**Repository** — A repository (or "repo") is a project stored on GitHub. The
mavai.ch repository contains everything that makes up the website: pages,
templates, configuration, and the feed data you will be curating.

**Branch** — A branch is a parallel version of the repository. The main branch
(`main`) is what the live website is built from. When the pipeline proposes new
items, it creates a separate branch so your review happens without affecting the
live site.

**Pull request (PR)** — A pull request is a proposal to merge changes from one
branch into another. When the pipeline opens a PR, it is asking you: "Here are
some new items — do you want to add them to the live feed?" You review the
changes, make your decisions, and merge the PR to publish.

**Commit** — A commit is a saved snapshot of changes. When you edit the file
during your review, GitHub saves your edits as a commit on the PR branch.

**Merge** — Merging takes the changes from the PR branch and applies them to
the main branch. Once you merge, the website rebuilds with your approved items.

## Getting set up

You need a GitHub account with access to the
[mavai-org/mavai-ch](https://github.com/mavai-org/mavai-ch) repository. Ask a
project administrator to add you if you do not already have access.

### Notifications

To be alerted when new candidates are ready for review, configure GitHub
notifications:

1. Go to the [mavai-ch repository](https://github.com/mavai-org/mavai-ch) on
   GitHub.
2. Click the **Watch** button near the top right.
3. Select **Custom** and tick **Pull requests**.
4. Click **Apply**.

You will now receive an email (and a notification in the GitHub app) whenever a
new PR is opened or an existing one is updated with new candidates.

## Reviewing candidates

### On your Mac or iPad (web browser)

1. Open the notification email or go directly to the
   [pull requests page](https://github.com/mavai-org/mavai-ch/pulls).

2. Open the PR titled **"Feed candidates for review"**.

3. Click the **Files changed** tab. You will see the changes to
   `newsroom/data/feed.yml`. New items appear at the top of the file under a
   comment line that reads:

   ```
   # -- New candidates --------------------------------------------------------
   ```

4. To edit the file, click the **three-dot menu** (⋯) on the right side of the
   file header and choose **Edit file**.

5. You are now editing the file directly. Make your changes (see
   "Approving and rejecting items" below).

6. When you are done, scroll down to the **Commit changes** section. Leave the
   default commit message or write your own, ensure the option
   **"Commit directly to the `newsroom/curate` branch"** is selected, and click
   **Commit changes**.

7. Go back to the PR's **Conversation** tab and click the green
   **Merge pull request** button, then **Confirm merge**.

The website will rebuild and deploy automatically within a few minutes.

### On the GitHub mobile app (iOS)

1. Open the GitHub app and navigate to the **mavai-org/mavai-ch** repository.

2. Tap **Pull requests** and open **"Feed candidates for review"**.

3. Tap **Files changed** to see the diff.

4. Tap the file name (`newsroom/data/feed.yml`) to open it, then tap the
   **pencil icon** to edit.

5. Make your changes, tap **Commit**, and confirm.

6. Return to the PR conversation and tap **Merge**.

## Approving and rejecting items

Each item in the file has a field called `accepted`. New candidates arrive with
`accepted: n`. Your job is to change this to `accepted: y` for items you want
to publish.

### To approve an item

Find the line that reads `accepted: n` and change it to:

```yaml
  accepted: y
```

The item will appear on the public site after you merge the PR.

### To reject an item

Leave it as `accepted: n`. The item stays in the file so the pipeline does not
propose it again, but it will never appear on the public site.

### To feature an item

Featured items are highlighted as key documents at the top of their sector
page. To feature an item, change:

```yaml
  featured: n
```

to:

```yaml
  featured: y
```

A featured item also appears in the regular feed — featuring does not remove it
from the list.

### To unpublish a previously approved item

Scroll down to the published section of the file and find the item. Change
`accepted: y` to `accepted: n`. After you merge, the item will disappear from
the public feeds.

### To adjust which sectors an item appears in

Each item has a `tags` field that determines which sector feeds it appears in:

- Items tagged `global` appear in **all** sector feeds.
- Items tagged `financial` appear in the **banking** feed.
- Items tagged `pharma`, `biotech`, `medicine` appear in the **pharma** feed.
- Items tagged `health` appear in the **health** feed.
- Items tagged `swiss`, `policy`, `federal` appear in the
  **federal government** feed.

You can add or remove tags to control placement. For example, if an item is
tagged `["global", "eu", "ai-act"]` but you think it is only relevant to
banking, you could change it to `["eu", "financial", "ai-act"]`.

## Duplicate warnings

Sometimes the pipeline detects that a new candidate looks very similar to an
item already in the feed — for example, the same document published under a
slightly different title or URL. When this happens, you will see a warning
comment directly above the item:

```
# WARNING: POTENTIAL DUPLICATE (see "EBA report on AI Act implications..." below)
```

Compare the candidate with the existing item named in the warning. Then decide:

- If it is genuinely the same document, leave the candidate as `accepted: n`.
- If it is a different document that happens to have a similar title, approve it
  as you normally would.

Exact duplicates (identical URLs) are removed automatically and will never
appear in your review.

## What the file looks like

Here is a simplified example of `feed.yml` during a review:

```yaml
# -- New candidates --------------------------------------------------------
- title: "EDPB Guidelines on AI and consent"
  url: "https://www.edpb.europa.eu/..."
  date: "2026-03-15"
  source: "EDPB"
  summary: "New guidelines on consent requirements for AI training data."
  tags: ["global", "eu", "data-protection", "consent"]
  relevance: "Consent requirements for AI training data"
  accepted: n
  featured: n

# -- Published feed ---------------------------------------------------------
- title: "FINMA Guidance 08/2024: Governance and risk management when using AI"
  url: "https://www.finma.ch/..."
  date: "2024-12-18"
  source: "FINMA"
  summary: "FINMA supervisory guidance on AI governance in financial institutions."
  tags: ["swiss", "financial", "supervision", "finma"]
  relevance: "Concrete AI governance obligations for financial institutions"
  accepted: y
  featured: y
```

Everything above the `Published feed` line is new and needs your decision.
Everything below it is already on the site.

## Timing and frequency

The pipeline runs automatically:

- **Daily** — checks the highest-priority sources
- **Weekly** (Monday) — checks a broader set of sources
- **Monthly** (1st of the month) — checks all sources including lower-priority ones

It can also be triggered manually by an administrator.

If a previous PR is still open when the next run happens, it is updated in place
rather than creating a new PR. You will not end up with multiple open PRs.

## If something goes wrong

- **You approved the wrong item** — Open a new PR (or edit `feed.yml` directly
  on the `main` branch) and change the item's `accepted: y` back to
  `accepted: n`. The website will rebuild and the item will disappear.

- **The website does not update after merging** — Check the
  [Actions tab](https://github.com/mavai-org/mavai-ch/actions) in the
  repository. The "Build and Deploy" workflow should be running or recently
  completed. If it failed, contact a project administrator.

- **You are unsure about an item** — Leave it as `accepted: n`. You can always
  approve it later by editing the file in a new PR. There is no deadline.

## Summary

| Action | What to do |
|--------|-----------|
| Approve an item | Change `accepted: n` to `accepted: y` |
| Reject an item | Leave it as `accepted: n` |
| Feature an item | Change `featured: n` to `featured: y` |
| Unpublish an item | Change `accepted: y` to `accepted: n` |
| Change sector placement | Edit the `tags` list |
| Finish your review | Merge the pull request |

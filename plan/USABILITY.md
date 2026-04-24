# Usability

Tracks usability issues on javai.ch, prompted by [issue #17](https://github.com/javai-org/javai-ch/issues/17).

Current focus is smartphone UX — phones roughly 360–430 px wide. iPad and larger are out of scope; they already render acceptably. The target is a first-class experience on iPhone and narrow Android.

**Method.** Headless Chrome (`--headless=new`) screenshots at 390 × N and direct geometry inspection via the Chrome DevTools Protocol (`Emulation.setDeviceMetricsOverride` → `Runtime.evaluate`). Geometry is authoritative; screenshots are illustrative and can mislead at scale-down.

## Status overview

| # | Item                                                            | Severity | Status                                                                                                    |
|---|-----------------------------------------------------------------|----------|-----------------------------------------------------------------------------------------------------------|
| 1 | Timeline card overlap on phones                                 | High     | **Fixed** — PR [#18](https://github.com/javai-org/javai-ch/pull/18), commit `18d88c8` (merged 2026-04-24) |
| 2 | Navigation density on phone                                     | Medium   | **Fixed** — PR [#20](https://github.com/javai-org/javai-ch/pull/20), commit `54d155d` (merged 2026-04-24) |
| 3 | Single 768 px breakpoint treats phone and tablet identically    | Medium   | **Fixed** — PR [#20](https://github.com/javai-org/javai-ch/pull/20), commit `54d155d` (merged 2026-04-24) |
| 4 | Hero and section banners take excessive vertical space on phone | Low      | **Fixed** — PR #21 (merged 2026-04-24)                                                                    |

## 1. Timeline card overlap — FIXED

**Problem.** On phones, timeline event cards that were placed on opposite sides by the desktop two-column layout collided vertically. The CSS at `assets/css/main.css:1580-1585` collapses both columns onto a single left spine on mobile, but the JS positioning loop in `layouts/timeline/single.html` still computed `top` values with per-side spacing (`lastLeftY`, `lastRightY`, `cardSpacing = 80`) — which only prevents overlap when left and right cards are visually side-by-side.

Measured at 390 px before the fix: event 4 spanned T705–B770, event 5 spanned T725–B790 — 45 px of overlap. Events 0/1 overlapped by 14 px.

**Fix.** At render time, detect `window.matchMedia('(max-width: 768px)')`. On narrow viewports:
- Abandon per-side spacing; each card must clear `narrowNextY`, the bottom of the previously placed card plus a 16 px visual gap.
- After `container.appendChild(eventEl)`, measure `cardEl.getBoundingClientRect().height` and advance `narrowNextY` by the measured height. The fixed 80 px top-to-top spacing is unsafe on mobile because phone-width headlines wrap more often than the constant assumed, leaving cards taller than 80 px.

Desktop layout is unchanged. The existing resize listener re-renders the timeline when the viewport crosses the 768 px threshold.

Post-fix measurement (first six events at 390 px): `40, 190, 321, 452, 583, 664` — each card fully clears the previous one.

## 2. Navigation density on phone — FIXED

**Problem.** At ≤480 px the top nav wrapped onto three rows: primary nav spilled across two rows, with the language switcher on a third. Every item fit and was clickable — this was a density / vertical-space complaint, not a clipping bug — but the header ate a noticeable fraction of the first screenful before the reader saw any content.

**Fix.** Compact-mode approach: no markup change, no JS. Inside the new ≤480 px block, shrink nav font-size (0.9rem → 0.75rem), tighten `gap` and `letter-spacing`, and compact the language switcher (font, gap, padding). Hamburger was considered and rejected as overkill for a five-item nav.

Measured header heights at 390 px after the fix:
- EN: 116 px (was ~140)
- DE / FR / IT: 116 px each, primary nav fits in two rows with language switcher sharing the second row
- DE at 360 px: 147 px (language switcher wraps, edge case — acceptable)

## 3. Single 768 px breakpoint — FIXED

**Problem.** `assets/css/main.css:1505` had exactly one mobile breakpoint — `@media (max-width: 768px)` — so rules inside it were shared between iPad portrait (768 px) and iPhone SE (375 px). The rules had been authored at the tablet end of that range, which is why several phone-specific complaints collapsed to "these rules never got a phone-specific version."

**Fix.** Added a second breakpoint at `max-width: 480px` below the existing 768 px block. The new block is reserved for phone-specific concessions only — structural changes shared with small tablets still belong in the 768 px block. Items §2 and §4 landed inside the new block.

## 4. Hero and section banners take excessive vertical space on phone — FIXED

**Problem.** On phones the hero was fixed at `min-height: 400px` (`main.css:1508`) and section banners at `height: 180px` (`main.css:1527`). At 390 × 844 the hero alone consumed nearly half the viewport before content, and long subtitles in German on section banners clipped below the 180 px fixed height.

**Fix.** Inside the ≤480 px block:
- `.hero { min-height: 280px }` and container padding reduced from `--space-xxl` (7 rem) to `--space-md` (2 rem). Hero h1 from 2.25rem → 1.875rem, hero-sub from 1.05rem → 0.95rem.
- `.section-banner { height: auto; min-height: 120px }` — converts a fixed height to a minimum, so the banner contracts around short titles ("About" → 141 px) and expands around long German subtitles rather than clipping them ("Alle Sektoren" feed page → 190 px, all content visible).
- Section-banner h1 1.75rem → 1.5rem, subtitle 1.05rem → 0.95rem.

Measured at 390 px: home hero 368 px (down from ~400), `/en/about/` banner 141 px (down from 180), `/en/feeds/all/` banner 165 px (down from 180), `/de/feeds/all/` banner 190 px (was clipping at 180). iPad and desktop measurements unchanged.

## Investigated and retracted

An earlier pass (2026-04-24) flagged three items that subsequent CDP geometry measurement disproved. Recording them here so we don't rechase the same ground:

- **Right-edge clipping on content pages.** Home news bulletin, Key Topics card bodies, regulations sector cards, about-page bio, posts card titles. CDP measurement at 390 px showed all are `overflow: visible`, `scrollWidth === clientWidth`, and fit their containers. What looked like clipping in screenshots was wrapped text displayed at 1.24× scale-down, which made adjacent lines hard to separate visually.

- **German navbar overflow.** `Regulierungsnachrichten` measures 236 px, `Zeitstrahl` 97 px, lang-switcher items ≤30 px — every item falls fully inside 390 px. The nav wraps across three rows but nothing is clipped.

- **Feed pages showing "Could not load the feed"** in headless-Chrome screenshots. A screenshot-timing artefact (the capture fires before the async `fetch()` resolves). Real browsers render items correctly. Don't treat this as a bug.

**Rule of thumb for future audits.** Before opening a PR for any symptom observed only in a screenshot, verify with CDP geometry (`getBoundingClientRect`, `scrollWidth`/`clientWidth`, `getComputedStyle`) or on a real device. Scaled-down screenshots are unreliable for detecting clipping vs. wrapping at phone widths.

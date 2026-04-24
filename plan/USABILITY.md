# Usability

Tracks usability issues on javai.ch, prompted by [issue #17](https://github.com/javai-org/javai-ch/issues/17).

Current focus is smartphone UX — phones roughly 360–430 px wide. iPad and larger are out of scope; they already render acceptably. The target is a first-class experience on iPhone and narrow Android.

**Method.** Headless Chrome (`--headless=new`) screenshots at 390 × N and direct geometry inspection via the Chrome DevTools Protocol (`Emulation.setDeviceMetricsOverride` → `Runtime.evaluate`). Geometry is authoritative; screenshots are illustrative and can mislead at scale-down.

## Status overview

| # | Item                                                            | Severity | Status                                                                                                    |
|---|-----------------------------------------------------------------|----------|-----------------------------------------------------------------------------------------------------------|
| 1 | Timeline card overlap on phones                                 | High     | **Fixed** — PR [#18](https://github.com/javai-org/javai-ch/pull/18), commit `18d88c8` (merged 2026-04-24) |
| 2 | Navigation density on phone                                     | Medium   | Open                                                                                                      |
| 3 | Single 768 px breakpoint treats phone and tablet identically    | Medium   | Open                                                                                                      |
| 4 | Hero and section banners take excessive vertical space on phone | Low      | Open                                                                                                      |

## 1. Timeline card overlap — FIXED

**Problem.** On phones, timeline event cards that were placed on opposite sides by the desktop two-column layout collided vertically. The CSS at `assets/css/main.css:1580-1585` collapses both columns onto a single left spine on mobile, but the JS positioning loop in `layouts/timeline/single.html` still computed `top` values with per-side spacing (`lastLeftY`, `lastRightY`, `cardSpacing = 80`) — which only prevents overlap when left and right cards are visually side-by-side.

Measured at 390 px before the fix: event 4 spanned T705–B770, event 5 spanned T725–B790 — 45 px of overlap. Events 0/1 overlapped by 14 px.

**Fix.** At render time, detect `window.matchMedia('(max-width: 768px)')`. On narrow viewports:
- Abandon per-side spacing; each card must clear `narrowNextY`, the bottom of the previously placed card plus a 16 px visual gap.
- After `container.appendChild(eventEl)`, measure `cardEl.getBoundingClientRect().height` and advance `narrowNextY` by the measured height. The fixed 80 px top-to-top spacing is unsafe on mobile because phone-width headlines wrap more often than the constant assumed, leaving cards taller than 80 px.

Desktop layout is unchanged. The existing resize listener re-renders the timeline when the viewport crosses the 768 px threshold.

Post-fix measurement (first six events at 390 px): `40, 190, 321, 452, 583, 664` — each card fully clears the previous one.

## 2. Navigation density on phone

**Problem.** At ≤480 px the top nav wraps onto three rows: primary nav spills across two rows, with the language switcher on a third. Every item fits and is clickable — this is a density / vertical-space complaint, not a clipping bug. The header eats a noticeable fraction of the first screenful before the reader sees any content, which matches the "cluttered" complaint in #17.

**Candidate directions** (not yet decided; pick one before starting the PR):

- **Hamburger menu at ≤480 px.** Idiomatic for phones, collapses nav to a single icon. Costs a small amount of JS plus accessibility state (focus trap, `aria-expanded`, ESC-to-close).
- **Horizontally scrollable nav strip.** Zero JS, nav remains visible in one row. Less familiar as a pattern but lighter to build and maintain.
- **Shrink the language switcher.** A compact `EN ▾` dropdown recovers one row without restructuring the primary nav.

## 3. Single 768 px breakpoint

**Problem.** `assets/css/main.css:1505` has exactly one mobile breakpoint — `@media (max-width: 768px)` — so rules inside it are shared between iPad portrait (768 px) and iPhone SE (375 px). The rules were evidently authored at the tablet end of that range. Because iPad is reported as fine and phones are not, several of the downstream complaints collapse to "these rules never got a phone-specific version."

**Fix direction.** Introduce a second breakpoint at `max-width: 480px` for phone-only overrides. Keep the 768 px block for the in-between sizes (small tablet, large phone landscape). Items #2 and #4 should land inside the new block.

## 4. Hero and section banners take excessive vertical space on phone

**Problem.** On phones the hero sets `min-height: 400px` (`main.css:1508`) and section banners set `height: 180px` (`main.css:1527`). At 390 × 844 that is roughly a quarter to half of the first viewport before the reader sees body content. Not broken — the banner's heading and subtitle render correctly inside — but reinforces the "cluttered" feel from #17.

**Fix direction.** Under a phone-only breakpoint (see §3), reduce hero `min-height` to something like `280px` and convert section-banner `height: 180px` to a `min-height` + vertical padding, so it contracts around its text on phone rather than reserving a fixed height.

## Investigated and retracted

An earlier pass (2026-04-24) flagged three items that subsequent CDP geometry measurement disproved. Recording them here so we don't rechase the same ground:

- **Right-edge clipping on content pages.** Home news bulletin, Key Topics card bodies, regulations sector cards, about-page bio, posts card titles. CDP measurement at 390 px showed all are `overflow: visible`, `scrollWidth === clientWidth`, and fit their containers. What looked like clipping in screenshots was wrapped text displayed at 1.24× scale-down, which made adjacent lines hard to separate visually.

- **German navbar overflow.** `Regulierungsnachrichten` measures 236 px, `Zeitstrahl` 97 px, lang-switcher items ≤30 px — every item falls fully inside 390 px. The nav wraps across three rows but nothing is clipped.

- **Feed pages showing "Could not load the feed"** in headless-Chrome screenshots. A screenshot-timing artefact (the capture fires before the async `fetch()` resolves). Real browsers render items correctly. Don't treat this as a bug.

**Rule of thumb for future audits.** Before opening a PR for any symptom observed only in a screenshot, verify with CDP geometry (`getBoundingClientRect`, `scrollWidth`/`clientWidth`, `getComputedStyle`) or on a real device. Scaled-down screenshots are unreliable for detecting clipping vs. wrapping at phone widths.

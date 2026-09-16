---
title: "Compliance and the Mavai Method"
description: "How the obligations tracked on mavai.ch are met by the method documented on mavai.org: Baseline · Monitor · Comply."
keywords: ["AI compliance Switzerland", "FINMA AI evidence", "ISO 42001 evidence", "EU AI Act post-market monitoring", "AI baseline", "probabilistic testing compliance"]
summary: "mavai.ch tracks what regulators ask for. mavai.org documents how Mavai answers. This page joins the two: each obligation on one side, the step of the method that produces its evidence on the other."
---

mavai.ch follows the regulatory landscape for AI in Switzerland: what FINMA
expects, what ISO/IEC 42001 certifies, and where the EU AI Act reaches across
the border. [mavai.org](https://mavai.org/) documents the method Mavai uses to
meet those expectations. This page connects the two.

## The obligation

Every regime on this site asks the same question in different words: **can you
show that your AI system performs as you say it does, not once, but for as long
as it runs?**

- **[FINMA](/en/regulations/finma/)** expects an inventory of AI applications,
  governance over their risks, and demonstrable control over model behaviour.
- **[ISO/IEC 42001](/en/regulations/iso-42001/)** certifies an AI management
  system with performance evaluation and continual improvement built in.
- **The [EU AI Act](/en/ai-regulation-switzerland/)** requires lifecycle risk
  management (Art. 9), post-market monitoring (Art. 72) and technical
  documentation of testing and results (Annex IV) for high-risk systems, and it
  reaches Swiss providers whose systems are used in the EU.

None of these can be satisfied by a one-off audit before go-live. Each asks for
evidence that keeps being produced.

## The method: Baseline · Monitor · Comply

**Baseline.** Any one call to an AI service can be judged right or wrong. What
nobody knows in advance is how often the service gets it right. A baseline
measures that rate at a stated confidence and records it together with the
model, the prompts, and the circumstances it was measured under.

**Monitor.** The live service is held to its baseline for as long as it runs:
every release, every model or prompt change, and on a schedule in between.
Drift beyond the agreed bounds is flagged, at the confidence the baseline was
set at, before it reaches production, let alone a supervisor.

**Comply.** The service is held to a contract: what a good response is, and the
rate at which it must be delivered. Every verdict says whether the service
complies, at the stated confidence. Where a regulator sets the bar, the same
contract carries it.

Every measurement and every verdict is a structured record that states what was
measured, how many times, against what bar and at what confidence. That record
is the evidence.

## Obligation to evidence

| The regime asks for | The method provides |
|---|---|
| FINMA: inventory and control of AI risk | One baseline per service, which is the inventory and the control |
| ISO/IEC 42001: performance evaluation, continual improvement | Baselines and monitoring records as management-system evidence |
| EU AI Act Art. 9: lifecycle risk management with defined metrics | Baselines with stated thresholds and confidence; testing at every change |
| EU AI Act Art. 72: post-market monitoring | Scheduled monitoring against the baseline, with drift flagged |
| EU AI Act Annex IV: documented testing procedures and results | The persisted measurement and verdict records |

The names differ. The evidence is the same evidence, and it is the evidence a
team would want anyway. Regulation is the reason more teams are asking for it
now; the method is the same for any team whose service behaves as a rate rather
than a single value.

## Next steps

The full method, in business terms, is on
[How We Help](https://mavai.org/how-we-help/). The open-source tools that
implement it are [punit](https://mavai.org/projects/punit/) for Java,
[feotest](https://mavai.org/projects/feotest/) for Rust and
[baseltest](https://mavai.org/projects/baseltest/) for Python.

If you are deploying an AI service under one of the regimes above, a first
conversation covers where your project stands today and how its testing needs
to change. [Contact us](/en/contact/) or
[talk to Mavai directly](https://mavai.org/contact/).

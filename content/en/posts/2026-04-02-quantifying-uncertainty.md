---
title: "Quantifying Risk: Easy. Quantifying Likelihood: Hard."
date: 2026-04-02
description: "Most organisations deploying AI cannot answer a basic question: how often does this system fail? Probabilistic testing provides the answer — and regulators are starting to demand it."
summary: "The NASA risk matrix has two axes. Consequence is tractable. Likelihood — especially for stochastic AI systems — is not. Regulators are now demanding quantitative evidence, and probabilistic testing is how you produce it."
image: /images/swiss-risk-matrix-en.svg
author: "Mike Mannion"
---

<figure>
<img src="/images/swiss-risk-matrix-en.svg" alt="NASA-style risk matrix — consequence on the horizontal axis, likelihood on the vertical">
<figcaption>NASA's risk matrix: as relevant as ever</figcaption>
</figure>

The NASA risk matrix plots consequence on one axis and likelihood on the other. It appears in everything from spacecraft engineering to financial regulation. But the two axes are not equally difficult to assess.

Consequence — monetary cost, remediation effort, regulatory penalties, reputational damage — is hard work, but the methods are well established. Insurance, actuarial science, and quality-adjusted health metrics have been quantifying impact for centuries.

Likelihood is another matter. For some risks, historical data provides an empirical basis. For novel systems — unprecedented conditions, emergent behaviours, AI models that produce confidently wrong answers — there is often no track record at all. These questions go unasked, or are answered with intuition dressed up as analysis.

## LLMs have made the question unavoidable

Large language models are stochastic by design. A single test run tells you almost nothing. The question "does this work?" must be replaced with "how often does this work?" — and most organisations deploying LLM-powered systems have no rigorous answer.

This is not a niche concern. LLMs are being deployed in customer service, medical triage, legal research, and financial analysis — domains where confidence in system behaviour matters enormously. Yet the testing tools available to most teams produce binary verdicts when what is needed is a probability distribution.

## Regulators are starting to notice

FINMA issued Guidance 08/2024 [1] in December 2024, setting expectations for AI governance in supervised institutions. Notably, FINMA expects institutions to use *performance indicators* — quantitative measures of how well a system achieves its objectives. This is a significant departure from pass/fail testing. It requires measurement, not merely verification.

The EU AI Act's obligations for high-risk AI systems — including those in finance, healthcare, and public administration — apply from August 2026 [2], and Swiss organisations serving EU customers must comply regardless of Switzerland's non-member status. Requirements include declared accuracy levels, ongoing performance monitoring, and risk management testing (Articles 9, 15, and 72 of Regulation 2024/1689). ISO 42001 [3] requires organisations to define and track performance metrics.

The regulatory direction is clear: organisations must demonstrate, with evidence, that their AI systems perform within acceptable bounds. "We think it works" will not satisfy auditors.

## The answer is statistical

If the question is "how often does this work?", the method must be statistical. Probabilistic testing runs a test many times, measures the success rate, and applies statistical inference to determine whether the observed rate meets a defined threshold at a given confidence level. It transforms compliance from a subjective judgement into a measurable, auditable claim.

The [punit](https://github.com/mavai-org/punit) framework implements this approach for Java applications. For technical details and getting-started guides, visit [mavai.org](https://mavai.org).

If one thing is certain, it's that it is time to take uncertainty seriously.

---

*For regulatory context, see [Why Probabilistic Testing Matters for Swiss Regulation](/en/posts/2026-03-21-why-probabilistic-testing/).*

## Sources

[1] FINMA Guidance 08/2024 — Governance and risk management when using artificial intelligence, 18 December 2024. https://www.finma.ch/en/news/2024/12/20241218-mm-finma-am-08-24/

[2] Regulation (EU) 2024/1689 — Artificial Intelligence Act. High-risk AI system obligations (Articles 9, 15, 72) apply from 2 August 2026. https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX:32024R1689

[3] ISO/IEC 42001:2023 — Information technology — Artificial intelligence — Management system. https://www.iso.org/standard/81230.html

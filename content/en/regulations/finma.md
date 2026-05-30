---
title: "FINMA & AI in Banking"
description: "How FINMA's supervisory expectations affect AI adoption in Swiss financial institutions."
weight: 1
---

The Swiss Financial Market Supervisory Authority (FINMA) oversees banks, insurance companies, and other financial institutions in Switzerland. As AI adoption accelerates across the sector, FINMA's expectations around model risk management, operational resilience, and governance have significant implications for how organisations deploy and monitor AI systems.

## What FINMA expects

FINMA does not yet have AI-specific regulation, but its existing supervisory framework — particularly around model risk and operational risk — applies directly to AI systems:

- **Model validation** — institutions must validate models before deployment and on an ongoing basis, demonstrating that they perform within acceptable parameters
- **Operational risk management** — AI systems must be subject to the same operational risk controls as any critical system, including monitoring, incident management, and business continuity
- **Governance and accountability** — clear ownership and accountability for AI systems, with documented decision-making processes
- **Auditability** — the ability to explain and evidence how AI systems arrive at their outputs

## Why probabilistic testing matters

Traditional software testing asks: "Does this function return the correct output?" For AI systems, the question becomes: "Does this system perform within acceptable statistical bounds, consistently, over time?"

FINMA's expectation of ongoing model validation and performance monitoring aligns directly with probabilistic testing approaches. By running statistical hypothesis tests as part of continuous integration, organisations can produce the kind of auditable, reproducible evidence that supervisory reviews require.

## Further reading

For technical tools that implement probabilistic testing, visit [mavai.org](https://mavai.org).

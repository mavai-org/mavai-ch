---
title: "AI Regulation in Switzerland: What's Changing and What to Do About It"
description: "A practical overview of AI regulation affecting Switzerland — FINMA supervisory expectations, ISO/IEC 42001, and the EU AI Act's spillover — and how Swiss organisations can respond with evidence."
keywords: ["AI regulation Switzerland", "FINMA AI requirements", "ISO 42001 Switzerland", "EU AI Act Switzerland", "AI compliance Switzerland", "AI governance Switzerland", "Swiss AI law", "AI risk management"]
summary: "Switzerland has no single AI law, but financial-sector supervision, international management standards, and the EU AI Act's extraterritorial reach already shape what Swiss organisations must do. The common thread is evidence — and producing it is a measurement problem."
---

Switzerland does not have a single, dedicated AI statute. That absence can be
misread as an absence of obligation. It is not. AI systems deployed by Swiss
banks, insurers, hospitals, pharmaceutical companies, and public bodies already
fall under a layering of supervisory expectations, international standards, and
extraterritorial rules. Understanding how these fit together — and what they
have in common — is the first step toward a defensible compliance posture.

The common thread is **evidence**. Each of the regimes below, in its own
language, asks the same underlying question: *can you demonstrate that your AI
system performs within acceptable bounds, consistently, over time?* For
non-deterministic systems — anything built on large language models or
machine-learning inference — that is not a yes/no question. It is a measurement
problem.

## FINMA: supervision without an "AI law"

The Swiss Financial Market Supervisory Authority (FINMA) has not issued
AI-specific regulation, and may not need to. Its existing framework for model
risk, operational risk, and governance applies directly to AI systems used in
banking and insurance. Supervised institutions are expected to validate models
before and during deployment, to manage operational risk around critical
systems, to maintain clear accountability, and to be able to explain and
evidence how a system reaches its outputs.

For a deterministic model, validation can lean on reproducible outputs. For a
stochastic AI system, "it worked when we checked" is not validation — the next
run may differ. What FINMA's expectations imply, in practice, is a need to
characterise behaviour statistically: not a single passing check, but a
quantified claim about how often the system meets its contract.

See our detailed note on [FINMA & AI in banking](/regulations/finma/).

## ISO/IEC 42001: a management system for AI

ISO/IEC 42001 is the international standard for AI management systems — the AI
counterpart to ISO 27001 for information security. It is voluntary, but Swiss
organisations increasingly adopt it because certification offers a recognised,
auditable way to show that AI is governed responsibly. The standard calls for
risk assessment, defined controls, monitoring, and continual improvement across
the AI lifecycle.

Monitoring and continual improvement, again, presuppose measurement. A
management system that cannot quantify how its AI systems actually perform has a
gap between its documented controls and its operational reality.

See our note on [ISO/IEC 42001](/regulations/iso-42001/).

## The EU AI Act: extraterritorial reach into Switzerland

Switzerland is not an EU member, but the EU AI Act can apply to Swiss
organisations whose AI systems are placed on the EU market or whose outputs are
used in the EU. For high-risk systems, the Act sets requirements around risk
management, data governance, technical documentation, accuracy, robustness, and
post-market monitoring. Swiss exporters, and Swiss subsidiaries of EU groups,
may find themselves in scope regardless of domestic law.

The Act's language of "accuracy" and "robustness" is, once more, a demand for
quantified evidence over time — not a one-off certificate.

## The common thread: from obligation to evidence

Across FINMA supervision, ISO/IEC 42001, and the EU AI Act, the practical
demand converges:

- **Characterise behaviour, don't assert it.** A non-deterministic system needs
  a description of its distribution of outcomes, not a single pass.
- **Set thresholds explicitly.** What success rate, at what confidence level, is
  acceptable for this use case?
- **Monitor for drift.** Yesterday's evidence does not certify today's model.
- **Make it auditable.** Evidence a regulator or auditor can inspect beats
  assurances they must take on trust.

These are not legal questions in the first instance. They are measurement
questions — and they are tractable.

## What to do about it

The discipline that answers these questions is **probabilistic testing**:
treating each run of an AI system as a trial, observing the success rate over
many runs, and applying statistical inference to decide whether the true
underlying rate meets a defined threshold at a stated confidence level. This
turns "the model seems fine" into a quantified, repeatable, auditable claim —
exactly the form of evidence the regimes above are reaching for.

The open-source tooling for this lives at our technical sister site,
[mavai.org](https://mavai.org/) — including
[punit](https://mavai.org/projects/punit/) for Java and
[feotest](https://mavai.org/projects/feotest/) for Rust, validated against a
shared statistical oracle. The regulation creates the obligation; probabilistic
testing is a practical way to produce the evidence.

To follow how these requirements are evolving, see our
[Regulation News](/regulations/) feed and longer-form
[Insights](/posts/).

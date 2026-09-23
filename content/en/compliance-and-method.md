---
title: "Compliance and the Mavai Method"
description: "How AI system monitoring requirements are met through the Mavai® method: Baseline · Monitor · Comply."
keywords: ["AI compliance Switzerland", "FINMA AI evidence", "ISO 42001 evidence", "EU AI Act post-market monitoring", "AI baseline", "probabilistic testing compliance"]
---

Mavai.ch follows the regulatory landscape in AI Governance and its impact on Switzerland: what FINMA
expects, what ISO/IEC 42001 certifies, and where the EU AI Act reaches across
the border. [mavai.org](https://mavai.org/) documents the method Mavai® uses to
meet those expectations. This page connects the two.

## The Requirements

Every AI oversight or quality framework we mention here asks the same question in different words: **can you
show that your AI system performs as you say it does, not once, but for as long
as it runs?**

- **[FINMA](/en/regulations/finma/)** expects an inventory of AI applications,
  governance over their risks, and demonstrable control over model behaviour.
- **[ISO/IEC 42001](/en/regulations/iso-42001/)** certifies an AI management
  system with performance evaluation and continual improvement built in.
- **[EU AI Act](/en/ai-regulation-switzerland/)** requires lifecycle risk
  management (Art. 9), post-market monitoring (Art. 72) and technical
  documentation of testing and results (Annex IV) for high-risk systems, and it
  reaches Swiss providers whose systems are used in the EU.

While requirements may differ regarding their enforcement, focus and reach, each asks for continuous evidence on system performance.

## The Mavai® method: Baseline · Monitor · Comply

Mavai uses a statistical baseline for each AI service you deploy, continuous monitoring against it, and a method documented in public — with the tools and the know-how to build it into your delivery pipeline.

**Baseline.** Any one call to an AI service can be judged right or wrong. What
nobody knows in advance is how often the service gets it right. A baseline measures that rate over a stated number of runs and records it together with the
model, the prompts, and the circumstances it was measured under.

**Monitor.** The live service is held to its baseline for as long as it runs:
every release, every model or prompt change, and on a schedule in between.
Each check takes a fresh sample of the live service and compares its success rate with the bound the baseline implies for a sample of that size. A rate below the bound is flagged as degradation, with a stated confidence, typically 95%, before it reaches production, let alone a supervisor.

**Comply.** The baseline is the record, monitoring is the evidence, and the
method is documented in public: the
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) sets out
the statistics and the [open-source frameworks](https://mavai.org/projects/)
implement it line by line. Together they are the technical documentation the
EU AI Act and other regulatory guidelines ask for regarding managing AI systems, and what any supervisor, auditor or standard can read.

Every measurement and every verdict is a structured record that states what was measured and how many times, and, for every verdict, the bound it was judged against and the confidence of the claim. That record
is the evidence you need.

## What the method provides

<style>
  .obligation-table {
    width: 100%;
    border-collapse: collapse;
    margin: 2rem 0;
    background: #ffffff;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
    overflow: hidden;
  }
  
  .obligation-table thead {
    background: #c8102e;
    color: #ffffff;
  }
  
  .obligation-table th {
    padding: 1.25rem;
    text-align: left;
    font-weight: 600;
    font-size: 1rem;
    border: none;
  }
  
  .obligation-table td {
    padding: 1.25rem;
    border-bottom: 1px solid #e0d5d5;
  }
  
  .obligation-table tbody tr:nth-child(odd) {
    background: #fdf2f2;
  }
  
  .obligation-table tbody tr:hover {
    background: #f9f5f5;
  }
  
  .obligation-table tbody tr:last-child td {
    border-bottom: none;
  }
</style>

<table class="obligation-table">
  <thead>
    <tr>
      <th>The framework requires</th>
      <th>The evidence you need</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>FINMA:</strong> inventory and control of AI risk</td>
      <td>One baseline per service, which is the inventory and the control</td>
    </tr>
    <tr>
      <td><strong>ISO/IEC 42001:</strong> performance evaluation, continual improvement</td>
      <td>Baselines and monitoring records as management-system evidence</td>
    </tr>
    <tr>
      <td><strong>EU AI Act Art. 9:</strong> lifecycle risk management with defined metrics</td>
      <td>Baselines over a stated number of runs; tests against them at a stated confidence, at every change</td>
    </tr>
    <tr>
      <td><strong>EU AI Act Art. 72:</strong> post-market monitoring</td>
      <td>Scheduled monitoring against the baseline, with drift flagged</td>
    </tr>
    <tr>
      <td><strong>EU AI Act Art. 11 and Annex IV:</strong> technical documentation of the method, its testing and results</td>
      <td>The Statistical Companion and open-source frameworks document the method; the persisted records hold the testing and results</td>
    </tr>
  </tbody>
</table>

The Mavai Method can provide the evidence to the requirements above and more.

## What Teams should know

### In summary

Put simply, Mavai.ch and Mavai.org 
[can help you](https://mavai.org/how-we-help/) manage new requirements and provide you with the tools to produce the evidence you need to fulfill these requirements. In addition, the open-source tools by Mavai® that
implement it are [punit](https://mavai.org/projects/punit/) for Java,
[feotest](https://mavai.org/projects/feotest/) for Rust and
[baseltest](https://mavai.org/projects/baseltest/) for Python.

If you are developing or are already deploying an AI service and want to ensure you can comply with the requirements above, a first
conversation covers where your project stands today and how its testing needs
to change. [Contact us](/en/contact/) or
[talk to Mavai directly](https://mavai.org/contact/).

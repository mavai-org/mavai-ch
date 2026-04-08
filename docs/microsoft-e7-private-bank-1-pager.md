# Advisory Note: Microsoft 365 E7 and Copilot for a Swiss Private Bank

## Executive view

Microsoft 365 E7 is now a real option for institutions that want a bundled route into enterprise AI. [Microsoft announced](https://blogs.microsoft.com/blog/2026/03/09/introducing-the-first-frontier-suite-built-on-intelligence-trust/) the suite on 9 March 2026, with general availability from 1 May 2026. It packages Microsoft 365 E5, Microsoft 365 Copilot, Agent 365, and the Microsoft Entra Suite. For a private bank, that makes it commercially plausible as the licensing basis for AI-assisted productivity in the front office.

The likely business case is straightforward: relationship managers and assistants spend substantial time drafting, reworking, translating, and polishing client communications. Microsoft 365 Copilot is designed to work across Outlook, Word, Teams, Excel, and related Microsoft 365 services, and Microsoft's licensing guidance confirms that Copilot access is part of the relevant enterprise licensing structure rather than a purely consumer feature.

However, the licence is not the main decision. The control model is.

For a Swiss private bank, the core question is not whether Copilot can speed up email drafting. It can. The real question is whether the bank can allow AI-assisted drafting of client-facing communications while preserving confidentiality, conduct discipline, suitability controls, auditability, and supervisory defensibility. [FINMA's Guidance 08/2024](https://www.finma.ch/en/news/2024/12/20241218-mm-finma-am-08-24/) is directly on point: supervised institutions are expected to identify, assess, manage, and monitor risks arising from internal and external AI applications.

## Why this is attractive

Used narrowly, Copilot could yield real value in a private-banking context. The most promising early use cases are drafting first versions of routine messages, rewriting already-approved content into another language, summarising internal notes into a banker-reviewed draft, and helping staff adapt tone and structure for international clientele. This is consistent with the kind of productivity support Microsoft positions Copilot to provide inside Microsoft 365.

There is also a timing argument. [FINMA's 2025 survey](https://www.finma.ch/en/news/2025/04/20250424-mm-umfrage-ki/) found that AI use is already gaining traction across Swiss financial institutions, with about half of surveyed institutions using AI in day-to-day work. So a bank that explores this space carefully is not acting unusually; it is moving with an already visible industry trend.

## What makes this risky

The first technical risk is **permission amplification**. [Microsoft states](https://learn.microsoft.com/en-us/copilot/microsoft-365/microsoft-365-copilot-privacy) that Microsoft 365 Copilot only accesses data a user is already authorised to access and inherits existing Microsoft 365 permissions, labels, and retention controls. That is useful, but it means weak entitlement hygiene becomes more dangerous, not less dangerous. If Exchange, SharePoint, Teams, or OneDrive permissions are loose, Copilot can make overexposed information easier to discover and re-use.

The second risk is **data-boundary misunderstanding**. [Microsoft says](https://learn.microsoft.com/en-us/copilot/microsoft-365/microsoft-365-copilot-privacy) prompts and responses under enterprise data protection are not used to train foundation models, which is an important enterprise safeguard. At the same time, [Microsoft's own documentation](https://learn.microsoft.com/en-us/privacy/eudb/eu-data-boundary-learn) states that the EU Data Boundary does not apply to web search queries, and that Anthropic model usage is outside the EU Data Boundary when those models are invoked in certain Copilot experiences. For a Swiss bank, this does not automatically rule the platform out, but it does mean feature-level decisions matter.

The third risk is **output risk**. In client communications, a plausible-sounding but inaccurate draft can create legal, conduct, reputational, or supervisory issues. That is especially true in private banking, where wording may touch investment positioning, product framing, performance implications, next-step recommendations, or confidential relationship context. FINMA's guidance is principles-based, but its message is clear: AI usage belongs inside governance, controls, monitoring, and accountability structures.

## Data sovereignty: Swiss datacentre does not mean Swiss jurisdiction

Microsoft operates two Azure regions in Switzerland — Switzerland North (Zurich) and Switzerland West (Geneva). Core Microsoft 365 workloads (Exchange Online, SharePoint, OneDrive, Teams) can be provisioned with data-at-rest in these Swiss regions. So the baseline infrastructure for keeping data in Switzerland exists.

However, E7 bundles Copilot, and Copilot introduces processing flows that go beyond simple data-at-rest. When a user invokes Copilot, the prompt and surrounding context are sent to a large language model for inference. [Microsoft has committed](https://learn.microsoft.com/en-us/privacy/eudb/eu-data-boundary-learn) to processing EU Data Boundary customers' data within the EU, but Switzerland is not in the EU. The bank needs to verify whether Microsoft contractually commits to processing Copilot prompts within Swiss or EU boundaries, or whether inference may occur in US datacentres. As noted above, web search grounding and Anthropic-backed model invocations are explicitly outside the EU Data Boundary — concrete leakage paths even if the underlying mailbox data stays in Zurich.

The deeper issue is **legal jurisdiction, not geography**. Microsoft is a US-headquartered company subject to the [US CLOUD Act](https://www.congress.gov/bill/115th-congress/house-bill/4943), which can compel US providers to produce data regardless of where it is stored, and to potential FISA Section 702 exposure for non-US persons' data. Swiss banking secrecy (Art. 47 of the Banking Act) creates a direct tension: a bank that stores client communication data in a Microsoft tenancy — even a Swiss-hosted one — must assess whether US government access requests could force disclosure that would violate Swiss law.

A bank concerned about data sovereignty should therefore:

- **Not assume "Swiss datacentre = Swiss data sovereignty."** The storage location and the legal/processing jurisdiction are different questions.
- **Demand contractual clarity** from Microsoft on where Copilot inference occurs for Swiss tenants, not just where data is stored at rest.
- **Disable or block features** (web grounding, Anthropic-backed experiences) that explicitly route data outside preferred boundaries.
- **Assess CLOUD Act exposure** as part of its FINMA-required risk assessment, potentially with an opinion from external counsel.
- **Consider the FDPIC's guidance** — the Swiss Federal Data Protection and Information Commissioner has published positions on US cloud providers that are relevant here.

This does not rule E7 out, but it means "data stays in Switzerland" is not something the licence delivers out of the box. It requires active feature-level configuration, contractual due diligence, and a legal assessment of US jurisdictional reach — all of which the bank would need to document to satisfy FINMA's governance expectations.

## Prudent recommendation

The prudent course is conditional approval, not open-ended rollout.

A bank considering E7 would be well advised to treat it as an enabler for a governed AI communications capability, not as a blanket permission to let the front office "use AI". The appropriate initial posture is that Copilot may assist with drafting, rewriting, summarising, and formatting, but a human banker remains accountable for every outbound client communication. That is the safest and most defensible operating model under current expectations, given Microsoft's enterprise-control model and FINMA's emphasis on governance and risk management for AI use.

## Minimum controls before rollout

Before any front-office deployment, a small but explicit control framework would typically be expected.

First, **define permitted use cases**. For example: drafting routine service emails; translating or refining already-approved wording; summarising meeting notes into a proposed draft; proposing follow-up questions; and adapting tone to house style. Free-form generation of substantive investment advice, legal commitments, or product statements would normally be excluded unless tightly template-bound and separately controlled. This follows from the risk profile of the use case rather than a direct Microsoft or FINMA rule, but it is the most prudent reading of the documented constraints.

Second, **perform a data-access hygiene review** across Exchange, SharePoint, Teams, and OneDrive for the pilot population. Because Copilot works from existing authorisations, a permissions clean-up is not optional; it is part of readiness.

Third, **decide which Copilot features to enable**, especially around public web grounding, agent creation, and any features that may invoke models or processing paths outside preferred geographic or contractual boundaries. Microsoft's documentation is explicit that web search queries and certain Anthropic-backed features have distinct data-boundary implications.

Fourth, **require human review before sending** for all client-facing emails generated or materially rewritten by AI. In practice, that means AI may propose, but not autonomously communicate. This aligns with FINMA's control expectations and with the known fallibility of generative systems.

Fifth, **establish monitoring and evidence**: sampling of generated drafts, categorisation of failure modes, language-quality checks, confidentiality incident review, and a documented decision log for approved and prohibited uses. FINMA's guidance expects supervised institutions to manage and monitor AI-related risks, and such evidence would matter if the bank later needs to justify its operating model to control functions or supervisors.

## Bottom line

A Swiss private bank can make a credible case for Microsoft 365 E7 and Copilot in the front office. The near-term value proposition is real, particularly for multilingual drafting and communication support. But the correct framing is not "buy E7 and let bankers use AI". The correct framing is: license E7, then introduce AI-assisted client communications under a narrowly defined, monitored, review-based control regime. That is the version most likely to deliver productivity without creating an avoidable governance problem.

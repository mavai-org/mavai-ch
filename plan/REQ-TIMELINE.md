# Timeline Tab — Prototype Specification

## Overview

Build a self-contained, single-page HTML prototype for a new "Timeline" tab on javai.ch. The page displays a vertical chronological timeline of AI-related events — both **regulatory** and **technological** — on a single scrollable axis. Today's date is centred on initial load.

## Technology

Single HTML file. Vanilla JavaScript, no frameworks. All CSS inline or in a `<style>` block. All event data defined as a JSON array in a `<script>` block. The prototype must work by opening the HTML file directly in a browser — no build step, no server.

## Layout

The viewport is divided into two regions:

1. **Main pane** (left, ~85% width): the full-detail scrollable timeline.
2. **Minimap pane** (right, ~15% width, fixed position): a compressed representation of the entire timeline, always visible as long as the viewport height ≥ 1024 px. Hidden below that threshold.

## Main Pane — The Timeline

### Structure

- A single continuous vertical line runs down the centre of the main pane.
- Time flows **upward**: the top of the line is the future, the bottom is the past.
- Each event is represented by a **dot** on the line, positioned proportionally to its date.
- A distinct dot labelled **"Today"** marks the current date and is styled differently (e.g. pulsing ring, larger radius, or unique colour).

### Event Dots

Each dot has two visual categories distinguished by colour:

| Category       | Colour  |
|----------------|---------|
| Regulatory     | Blue    |
| Technological  | Orange  |

### Event Card (Level 1 — always visible)

Beside each dot, alternating left and right of the centre line, display a compact card containing:

- **Date** (e.g. "1 Aug 2024")
- **Headline** — short title of the event (one line)
- **Flag / origin label** — a small tag showing the jurisdiction or origin: `USA`, `EU`, `CH`, `UK`, `CN`, `OECD`, `UN`, etc. Use text labels, not emoji flags.
- **Expand button** — a `[+]` or chevron that toggles Level 2 detail.

Cards are connected to their dot with a short horizontal connector line.

### Event Detail (Level 2 — toggled)

When the user clicks the expand button, the card grows in place (inline expansion, no reflow of other cards) to reveal:

- **Summary** — 1–3 sentence description of the event.
- **Source link** — a hyperlink labelled "View source →" pointing to the original documentation, legislation, paper, or announcement.

Clicking the button again collapses back to Level 1.

### Scrolling & Initial Position

- The main pane is vertically scrollable.
- On initial load, the viewport scrolls so that the **"Today" dot is vertically centred** in the main pane.

## Minimap Pane

### Purpose

Provide at-a-glance orientation and fast navigation across the full timeline.

### Structure

- A thin vertical line mirroring the main timeline, scaled to fit the full date range within the visible viewport height.
- Every event is represented by a **small coloured dot** (same colour coding: blue / orange) at the proportionally correct position.
- The "Today" dot is also shown, distinctly styled.
- Year labels are placed alongside the minimap line at regular intervals.
- A **viewport indicator** (a translucent rectangle or bracket) shows which portion of the main timeline is currently visible.

### Interaction

- **Click on any dot** on the minimap: the main pane smoothly auto-scrolls so the corresponding event is centred.
- **Click on the "Today" dot**: the main pane scrolls to centre today's date.
- **Drag the viewport indicator**: scrolls the main pane proportionally.
- The viewport indicator updates in real time as the user scrolls the main pane.

### Visibility Rule

The minimap is rendered only when the viewport height is ≥ 1024 px. Below that threshold it is hidden entirely (CSS media query on min-height).

## Event Data

Populate the timeline with the following seed events. Store them as a JSON array so more can be added easily.

### Technological Landmarks (orange)

| Date       | Headline                                      | Origin | Summary | Source URL |
|------------|-----------------------------------------------|--------|---------|------------|
| 2017-06-12 | "Attention Is All You Need" paper published    | USA    | Google Brain and Google Research publish the Transformer architecture paper, foundational to modern LLMs. | https://arxiv.org/abs/1706.03762 |
| 2018-06-11 | GPT-1 released                                | USA    | OpenAI releases the first Generative Pre-trained Transformer model. | https://cdn.openai.com/research-covers/language-unsupervised/language_understanding_paper.pdf |
| 2018-10-11 | BERT released                                 | USA    | Google releases BERT, a bidirectional Transformer model that set new benchmarks across NLP tasks. | https://arxiv.org/abs/1810.04805 |
| 2020-06-11 | GPT-3 released                                | USA    | OpenAI releases GPT-3 with 175 billion parameters, demonstrating strong few-shot learning. | https://arxiv.org/abs/2005.14165 |
| 2022-11-30 | ChatGPT launched                              | USA    | OpenAI launches ChatGPT, bringing LLM interaction to the general public. | https://openai.com/blog/chatgpt |
| 2023-02-06 | Google Bard announced                         | USA    | Google announces Bard, its conversational AI service powered by LaMDA. | https://blog.google/technology/ai/bard-google-ai-search-updates/ |
| 2023-02-24 | Llama 1 released                              | USA    | Meta releases LLaMA, its first open-weight large language model, initially for researchers. | https://ai.meta.com/blog/large-language-model-llama-meta-ai/ |
| 2023-03-14 | GPT-4 released                                | USA    | OpenAI releases GPT-4, a multimodal large language model. | https://openai.com/research/gpt-4 |
| 2023-03-14 | Claude 1 released                             | USA    | Anthropic releases Claude 1, its first publicly available large language model. | https://www.anthropic.com/news/introducing-claude |
| 2023-07-11 | Claude 2 released                             | USA    | Anthropic releases Claude 2 with improved coding, math, and reasoning capabilities. | https://www.anthropic.com/news/claude-2 |
| 2023-07-18 | Llama 2 released                              | USA    | Meta releases Llama 2, an open-weight large language model family available for commercial use. | https://ai.meta.com/llama/ |
| 2023-09-27 | Mistral 7B released                           | FR     | Mistral AI releases its first model, a 7-billion-parameter open-weight model that outperforms Llama 2 13B on all benchmarks. | https://mistral.ai/news/announcing-mistral-7b/ |
| 2023-12-06 | Gemini 1.0 released                           | USA    | Google DeepMind releases Gemini, a natively multimodal model family. | https://deepmind.google/technologies/gemini/ |
| 2024-02-15 | Sora announced                                | USA    | OpenAI announces Sora, a text-to-video generative model. | https://openai.com/sora |
| 2024-02-21 | Gemini 1.5 Pro released                       | USA    | Google DeepMind releases Gemini 1.5 Pro with a 1-million-token context window. | https://blog.google/technology/ai/google-gemini-next-generation-model-february-2024/ |
| 2024-02-26 | Mistral Large released                        | FR     | Mistral AI releases its first frontier-class model, competitive with GPT-4. | https://mistral.ai/news/mistral-large/ |
| 2024-03-04 | Claude 3 released                             | USA    | Anthropic releases the Claude 3 model family (Haiku, Sonnet, Opus). | https://www.anthropic.com/news/claude-3-family |
| 2024-04-18 | Llama 3 released                              | USA    | Meta releases Llama 3 in 8B and 70B parameter variants. | https://ai.meta.com/blog/meta-llama-3/ |
| 2024-05-13 | GPT-4o released                               | USA    | OpenAI releases GPT-4o ("omni"), a natively multimodal model with voice, vision, and text. | https://openai.com/index/hello-gpt-4o/ |
| 2024-06-20 | Claude 3.5 Sonnet released                    | USA    | Anthropic releases Claude 3.5 Sonnet, outperforming Claude 3 Opus at lower cost. | https://www.anthropic.com/news/claude-3-5-sonnet |
| 2024-07-23 | Llama 3.1 405B released                       | USA    | Meta releases Llama 3.1 with a 405-billion-parameter open-weight model. | https://ai.meta.com/blog/meta-llama-3-1/ |
| 2024-09-12 | OpenAI o1-preview released                    | USA    | OpenAI releases o1-preview, a model trained with reinforcement learning to perform complex reasoning. | https://openai.com/index/introducing-openai-o1-preview/ |
| 2024-12-05 | Gemini 2.0 Flash released                     | USA    | Google DeepMind releases Gemini 2.0 Flash with agentic capabilities. | https://deepmind.google/technologies/gemini/flash/ |
| 2024-12-20 | OpenAI o3 announced                           | USA    | OpenAI announces o3, a next-generation reasoning model, initially available to researchers. | https://openai.com/index/deliberative-alignment/ |
| 2025-01-20 | DeepSeek-R1 released                          | CN     | DeepSeek releases R1, an open-weight reasoning model competitive with leading Western models. | https://github.com/deepseek-ai/DeepSeek-R1 |
| 2025-02-24 | Grok 3 released                               | USA    | xAI releases Grok 3, claiming state-of-the-art reasoning benchmarks. | https://x.ai/blog/grok-3 |
| 2025-03-04 | Claude 3.7 Sonnet released                    | USA    | Anthropic releases Claude 3.7 Sonnet with extended thinking capability. | https://www.anthropic.com/news/claude-3-7-sonnet |
| 2025-03-25 | Gemini 2.5 Pro released                       | USA    | Google DeepMind releases Gemini 2.5 Pro, a thinking model with strong reasoning and coding. | https://blog.google/technology/google-deepmind/gemini-model-thinking-updates-march-2025/ |
| 2025-04-05 | Llama 4 Scout and Maverick released           | USA    | Meta releases Llama 4 Scout and Maverick, the first open-weight natively multimodal MoE models. | https://ai.meta.com/blog/llama-4-multimodal-intelligence/ |
| 2025-04-16 | OpenAI o4-mini released                       | USA    | OpenAI releases o4-mini, a compact but powerful reasoning model in the o-series. | https://openai.com/index/introducing-o3-and-o4-mini/ |
| 2025-05-22 | Claude 4 Opus and Sonnet released             | USA    | Anthropic releases Claude 4 Opus and Claude 4 Sonnet with major capability gains. | https://www.anthropic.com/news/claude-4 |
| 2025-06-25 | Mistral Magistral released                    | FR     | Mistral AI releases Magistral Small (open-source) and Medium, its first reasoning models with chain-of-thought capabilities. | https://mistral.ai/news/magistral/ |
| 2025-08-07 | GPT-5 released                                | USA    | OpenAI releases GPT-5, a major leap in intelligence across coding, math, writing, and vision. | https://openai.com/index/introducing-gpt-5/ |
| 2025-10-15 | Claude Haiku 4.5 released                     | USA    | Anthropic releases Claude Haiku 4.5, a fast and cost-efficient model. | https://www.anthropic.com/news/claude-haiku-4-5 |
| 2025-11-24 | Claude Sonnet 4.5 and Opus 4.5 released       | USA    | Anthropic releases Claude 4.5 Sonnet and Opus with further capability improvements. | https://www.anthropic.com/news/claude-4-5 |
| 2025-12-02 | Mistral 3 family released                     | FR     | Mistral AI releases the Mistral 3 family: Large 3 frontier model plus nine dense open-weight models. | https://mistral.ai/news/mistral-3 |
| 2026-02-05 | Claude Opus 4.6 released                      | USA    | Anthropic releases Claude Opus 4.6. | https://www.anthropic.com/news/claude-4-6 |
| 2026-02-17 | Claude Sonnet 4.6 released                    | USA    | Anthropic releases Claude Sonnet 4.6 with improved agentic search and lower token consumption. | https://www.anthropic.com/news/claude-sonnet-4-6 |
| 2026-02-19 | Gemini 3.1 Pro released                       | USA    | Google DeepMind releases Gemini 3.1 Pro with significantly improved reasoning. | https://blog.google/innovation-and-ai/models-and-research/gemini-models/gemini-3-1-pro/ |
| 2026-03-17 | GPT-5.4 Mini and Nano released                | USA    | OpenAI releases GPT-5.4 Mini and Nano, cost-efficient versions of their flagship model. | https://openai.com/index/gpt-5-4-mini-nano/ |

### Regulatory Events (blue)

| Date       | Headline                                      | Origin | Summary | Source URL |
|------------|-----------------------------------------------|--------|---------|------------|
| 2019-05-22 | OECD AI Principles adopted                    | OECD   | OECD member countries adopt principles for trustworthy AI. | https://oecd.ai/en/ai-principles |
| 2021-04-21 | EU AI Act proposed                            | EU     | European Commission publishes its proposal for a regulation on artificial intelligence. | https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX:52021PC0206 |
| 2022-09-28 | China AI algorithm regulation effective        | CN     | China's regulation on deep synthesis (deepfakes) and algorithmic recommendation comes into force. | http://www.cac.gov.cn/2022-12/11/c_1672221949354811.htm |
| 2023-07-21 | China Generative AI regulation effective        | CN     | China's Interim Measures for the Management of Generative AI Services take effect. | http://www.cac.gov.cn/2023-07/13/c_1690898327029107.htm |
| 2023-10-30 | US Executive Order on AI                      | USA    | President Biden signs Executive Order 14110 on safe, secure, and trustworthy AI. | https://www.whitehouse.gov/briefing-room/presidential-actions/2023/10/30/executive-order-on-the-safe-secure-and-trustworthy-development-and-use-of-artificial-intelligence/ |
| 2024-03-13 | EU AI Act adopted by Parliament               | EU     | European Parliament votes to adopt the EU AI Act. | https://www.europarl.europa.eu/news/en/press-room/20240308IPR19015/artificial-intelligence-act-meps-adopt-landmark-law |
| 2024-08-01 | EU AI Act enters into force                   | EU     | The EU AI Act is published in the Official Journal and enters into force. | https://eur-lex.europa.eu/eli/reg/2024/1689/oj |
| 2024-09-01 | Switzerland FADP fully effective               | CH     | Switzerland's revised Federal Act on Data Protection (nFADP) is fully in effect, with relevance to AI data processing. | https://www.fedlex.admin.ch/eli/cc/2022/491/en |
| 2025-02-02 | EU AI Act — prohibited practices apply         | EU     | The EU AI Act's provisions on prohibited AI practices and AI literacy obligations become applicable. | https://artificialintelligenceact.eu/article/5/ |
| 2025-06-01 | Japan AI Promotion Act effective               | JP     | Japan's Act on the Promotion of Research and Development and Utilisation of AI-Related Technologies takes effect, establishing a non-binding coordination framework. | https://www.japaneselawtranslation.go.jp/ |
| 2025-08-02 | EU AI Act — GPAI model rules apply             | EU     | Rules for general-purpose AI models and governance obligations under the EU AI Act become applicable. | https://artificialintelligenceact.eu/article/51/ |
| 2026-08-02 | EU AI Act — high-risk system rules apply        | EU     | Obligations for high-risk AI systems (Annex III) and transparency rules under the EU AI Act become applicable. | https://artificialintelligenceact.eu/article/6/ |
| 2027-08-02 | EU AI Act — full enforcement                   | EU     | Full roll-out of the EU AI Act, including obligations for high-risk AI systems under existing harmonisation legislation (Annex I). | https://artificialintelligenceact.eu/implementation-timeline/ |

## Visual Design

- Clean, modern aesthetic. Light background (`#fafafa` or similar). Dark text.
- The centre line is a thin solid line (`#cbd5e1` or similar light grey).
- Event cards have a subtle border, slight shadow, rounded corners.
- Regulatory cards have a blue left-border accent. Technological cards have an orange left-border accent.
- The "Today" marker should stand out: consider a horizontal dashed line across the main pane width, with a label.
- Use a readable sans-serif font (system font stack is fine).
- Smooth scroll animations for all navigation interactions (CSS `scroll-behavior: smooth` or JS `scrollIntoView({ behavior: 'smooth' })`).

## Behaviour Summary

1. Page loads → timeline renders → viewport scrolls to centre "Today".
2. User scrolls main pane → minimap viewport indicator tracks position.
3. User clicks minimap dot → main pane smooth-scrolls to that event.
4. User clicks `[+]` on a card → card expands inline to show summary and source link.
5. User clicks `[+]` again → card collapses.
6. Viewport < 1024 px height → minimap hidden.
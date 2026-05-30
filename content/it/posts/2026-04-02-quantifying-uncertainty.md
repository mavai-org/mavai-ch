---
title: "Quantificare il rischio: facile. Quantificare la probabilità: difficile."
date: 2026-04-02
description: "La maggior parte delle organizzazioni che utilizzano l'IA non sa rispondere a una domanda fondamentale: quanto spesso questo sistema fallisce? I test probabilistici forniscono la risposta — e le autorità di regolamentazione iniziano a richiederlo."
summary: "La matrice di rischio della NASA ha due assi. La conseguenza è gestibile. La probabilità — specialmente per i sistemi di IA stocastici — no. Le autorità di regolamentazione richiedono ora prove quantitative, e i test probabilistici sono il modo per produrle."
image: /images/swiss-risk-matrix-it.svg
author: "Mike Mannion"
---

<figure>
<img src="/images/swiss-risk-matrix-it.svg" alt="Matrice di rischio NASA — conseguenza sull'asse orizzontale, probabilità sull'asse verticale">
<figcaption>La matrice di rischio della NASA: più rilevante che mai</figcaption>
</figure>

La matrice di rischio della NASA pone la conseguenza su un asse e la probabilità sull'altro. Si ritrova in tutto, dall'ingegneria aerospaziale alla regolamentazione finanziaria. Ma i due assi non sono ugualmente difficili da valutare.

La conseguenza — costi monetari, sforzo di rimedio, sanzioni normative, danni reputazionali — richiede impegno, ma i metodi sono consolidati. Il settore assicurativo, la scienza attuariale e le metriche sanitarie corrette per la qualità quantificano l'impatto da secoli.

La probabilità è un'altra questione. Per alcuni rischi, i dati storici forniscono una base empirica. Per i sistemi nuovi — condizioni senza precedenti, comportamenti emergenti, modelli di IA che forniscono risposte errate con sicurezza — spesso non esiste alcuno storico. Queste domande non vengono poste, o ricevono risposte basate sull'intuizione mascherata da analisi.

## I LLM hanno reso la domanda ineludibile

I grandi modelli linguistici sono stocastici per progettazione. Un singolo test dice quasi nulla. La domanda «funziona?» deve essere sostituita con «quanto spesso funziona?» — e la maggior parte delle organizzazioni che utilizzano sistemi basati su LLM non ha una risposta rigorosa.

Non è una preoccupazione di nicchia. I LLM vengono impiegati nel servizio clienti, nel triage medico, nella ricerca giuridica e nell'analisi finanziaria — ambiti in cui la fiducia nel comportamento del sistema è fondamentale. Eppure gli strumenti di test disponibili per la maggior parte dei team producono verdetti binari quando ciò che serve è una distribuzione di probabilità.

## Le autorità di regolamentazione se ne stanno accorgendo

La FINMA ha pubblicato la Comunicazione 08/2024 [1] nel dicembre 2024, definendo le aspettative per la governance dell'IA negli istituti vigilati. In particolare, la FINMA si aspetta che gli istituti utilizzino *indicatori di prestazione* — misure quantitative di quanto un sistema raggiunge i propri obiettivi. Si tratta di un cambiamento significativo rispetto ai test pass/fail. Richiede misurazione, non mera verifica.

Gli obblighi del regolamento europeo sull'IA per i sistemi di IA ad alto rischio — compresi quelli nei settori finanziario, sanitario e della pubblica amministrazione — si applicano da agosto 2026 [2], e le organizzazioni svizzere che servono clienti nell'UE devono conformarsi indipendentemente dallo status di non membro della Svizzera. I requisiti comprendono livelli di accuratezza dichiarati, monitoraggio continuo delle prestazioni e test di gestione del rischio (articoli 9, 15 e 72 del regolamento 2024/1689). La norma ISO 42001 [3] richiede alle organizzazioni di definire e monitorare metriche di prestazione.

La direzione normativa è chiara: le organizzazioni devono dimostrare, con prove, che i loro sistemi di IA operano entro limiti accettabili. «Pensiamo che funzioni» non soddisferà i revisori.

## La risposta è statistica

Se la domanda è «quanto spesso funziona?», il metodo deve essere statistico. I test probabilistici eseguono un test molte volte, misurano il tasso di successo e applicano l'inferenza statistica per determinare se il tasso osservato raggiunge una soglia definita a un dato livello di confidenza. Trasformano la conformità da un giudizio soggettivo a un'affermazione misurabile e verificabile.

Il framework [punit](https://github.com/mavai-org/punit) implementa questo approccio per le applicazioni Java. Per dettagli tecnici e guide introduttive, visitate [mavai.org](https://mavai.org).

Se c'è una cosa certa, è che è ora di prendere l'incertezza sul serio.

---

*Per il contesto normativo, vedere [Perché i test probabilistici sono importanti per la regolamentazione svizzera](/it/posts/2026-03-21-why-probabilistic-testing/).*

## Fonti

[1] Comunicazione FINMA sulla vigilanza 08/2024 — Governance e gestione del rischio nell'utilizzo dell'intelligenza artificiale, 18 dicembre 2024. https://www.finma.ch/it/news/2024/12/20241218-mm-finma-am-08-24/

[2] Regolamento (UE) 2024/1689 — Legge sull'intelligenza artificiale. Gli obblighi per i sistemi di IA ad alto rischio (articoli 9, 15, 72) si applicano dal 2 agosto 2026. https://eur-lex.europa.eu/legal-content/IT/TXT/?uri=CELEX:32024R1689

[3] ISO/IEC 42001:2023 — Tecnologie dell'informazione — Intelligenza artificiale — Sistema di gestione. https://www.iso.org/standard/81230.html

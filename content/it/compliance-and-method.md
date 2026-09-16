---
title: "Conformità e il metodo Mavai"
description: "Come gli obblighi seguiti su mavai.ch vengono soddisfatti dal metodo documentato su mavai.org: Baseline · Monitor · Comply."
keywords: ["conformità IA Svizzera", "FINMA IA evidenze", "ISO 42001 evidenze", "AI Act monitoraggio post-commercializzazione", "baseline IA", "test probabilistici conformità"]
summary: "mavai.ch segue ciò che le autorità richiedono. mavai.org documenta la risposta di Mavai. Questa pagina collega le due cose: da un lato l'obbligo, dall'altro il passo del metodo che ne produce l'evidenza."
---

mavai.ch segue il panorama normativo dell'IA in Svizzera: cosa si aspetta la
FINMA, cosa certifica ISO/IEC 42001 e fin dove l'AI Act europeo si estende oltre
il confine. [mavai.org](https://mavai.org/) documenta il metodo con cui Mavai™
risponde a queste aspettative. Questa pagina collega le due cose.

## L'obbligo

Ogni regime presentato su questo sito pone la stessa domanda con parole diverse:
**potete dimostrare che il vostro sistema di IA funziona come affermate, non una
volta sola, ma per tutto il tempo in cui è in esercizio?**

- La **[FINMA](/it/regulations/finma/)** si aspetta un inventario delle
  applicazioni di IA, una governance dei relativi rischi e un controllo
  dimostrabile del comportamento dei modelli.
- **[ISO/IEC 42001](/it/regulations/iso-42001/)** certifica un sistema di
  gestione dell'IA con valutazione delle prestazioni e miglioramento continuo
  integrati.
- L'**[AI Act europeo](/it/ai-regulation-switzerland/)** richiede, per i sistemi
  ad alto rischio, una gestione dei rischi lungo l'intero ciclo di vita (art. 9),
  un monitoraggio post-commercializzazione (art. 72) e una documentazione tecnica
  dei test e dei risultati (allegato IV), e si applica ai fornitori svizzeri i
  cui sistemi sono utilizzati nell'UE.

Nessuno di questi obblighi si soddisfa con un audit una tantum prima della messa
in esercizio. Ciascuno richiede evidenze prodotte in continuo.

## Il metodo: Baseline · Monitor · Comply

**Baseline.** Ogni singola chiamata a un servizio di IA può essere giudicata
giusta o sbagliata. Ciò che nessuno sa in anticipo è quanto spesso il servizio
risponde correttamente. Una baseline misura quel tasso a un livello di confidenza
dichiarato e lo registra insieme al modello, ai prompt e alle circostanze della
misurazione.

**Monitor.** Il servizio in esercizio è tenuto alla sua baseline per tutto il
tempo in cui funziona: a ogni rilascio, a ogni modifica del modello o dei prompt
e, nel frattempo, secondo un calendario. Ogni deriva oltre i limiti concordati
viene segnalata, al livello di confidenza della baseline, prima che raggiunga la
produzione, e a maggior ragione un'autorità di vigilanza.

**Comply.** La baseline è la registrazione, il monitoraggio è l'evidenza e il
metodo è documentato pubblicamente: lo
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) espone
la statistica e i [framework open source](https://mavai.org/projects/) la
implementano riga per riga. Insieme costituiscono la documentazione tecnica
richiesta dall'AI Act europeo, e ciò che qualsiasi autorità, revisore o
standard può leggere.

Ogni misurazione e ogni verdetto sono una registrazione strutturata che indica
che cosa è stato misurato, quante volte, contro quale asticella e a quale livello
di confidenza. Quella registrazione è l'evidenza.

## Dall'obbligo all'evidenza

| Il regime richiede | Il metodo fornisce |
|---|---|
| FINMA: inventario e controllo dei rischi dell'IA | Una baseline per servizio, che è al tempo stesso inventario e controllo |
| ISO/IEC 42001: valutazione delle prestazioni, miglioramento continuo | Baseline e registrazioni di monitoraggio come evidenze del sistema di gestione |
| AI Act art. 9: gestione dei rischi lungo il ciclo di vita con metriche definite | Baseline con soglie e confidenza dichiarate; test a ogni modifica |
| AI Act art. 72: monitoraggio post-commercializzazione | Monitoraggio pianificato rispetto alla baseline, con segnalazione delle derive |
| AI Act art. 11 e allegato IV: documentazione tecnica del metodo, dei suoi test e dei risultati | Lo Statistical Companion e i framework open source documentano il metodo; le registrazioni conservate contengono test e risultati |

I nomi cambiano. L'evidenza è la stessa evidenza, ed è quella che un team
vorrebbe comunque. La regolamentazione è il motivo per cui oggi più team la
chiedono; il metodo è lo stesso per qualsiasi team il cui servizio si comporta
come un tasso e non come un valore singolo.

## Prossimi passi

Il metodo completo, in termini di business, è su
[How We Help](https://mavai.org/how-we-help/). Gli strumenti open source che lo
implementano sono [punit](https://mavai.org/projects/punit/) per Java,
[feotest](https://mavai.org/projects/feotest/) per Rust e
[baseltest](https://mavai.org/projects/baseltest/) per Python.

Se state implementando un servizio di IA sotto uno dei regimi sopra indicati, un
primo colloquio chiarisce dove si trova oggi il vostro progetto e come devono
cambiare i suoi test. [Contattateci](/it/contact/) o
[parlate direttamente con Mavai](https://mavai.org/contact/).

---
title: "Conformità e il metodo Mavai"
description: "Come i requisiti di monitoraggio dei sistemi di IA sono soddisfatti attraverso il metodo Mavai®: Baseline · Monitor · Comply."
keywords: ["conformità IA Svizzera", "FINMA IA evidenze", "ISO 42001 evidenze", "AI Act monitoraggio post-commercializzazione", "baseline IA", "test probabilistici conformità"]
summary: "mavai.ch segue ciò che la regolamentazione richiede. mavai.org documenta la risposta di Mavai. Questa pagina collega le due cose: da un lato l'obbligo, dall'altro il passo del metodo che ne produce l'evidenza."
---

mavai.ch segue il panorama normativo nella governance dell'IA e il suo impatto sulla Svizzera: cosa si aspetta la
FINMA, cosa certifica ISO/IEC 42001 e fin dove l'AI Act europeo si estende oltre
il confine. [mavai.org](https://mavai.org/) documenta il metodo con cui Mavai®
risponde a queste aspettative. Questa pagina collega le due cose.

## Gli Obblighi

Ogni quadro di **vigilanza o qualità** per l'IA che menzioniamo qui pone la stessa domanda con parole diverse:
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

Sebbene i requisiti differiscono nel loro rispetto, nella loro portata e nella loro ampiezza, ciascuno richiede evidenze continue sulla performance del sistema.

## Il metodo Mavai®: Baseline · Monitor · Comply

Mavai utilizza una baseline statistica per ogni servizio di IA che distribuire, il monitoraggio continuo rispetto ad essa e un metodo documentato pubblicamente — con gli strumenti e il know-how per incorporarlo nella vostra pipeline di distribuzione.

**Baseline.** Ogni singola chiamata a un servizio di IA può essere giudicata
giusta o sbagliata. Ciò che nessuno sa in anticipo è quanto spesso il servizio
risponde correttamente. Una baseline misura quel tasso su un numero dichiarato di chiamate e lo registra insieme al modello, ai prompt e alle circostanze della
misurazione.

**Monitor.** Il servizio in esercizio è tenuto alla sua baseline per tutto il
tempo in cui funziona: a ogni rilascio, a ogni modifica del modello o dei prompt
e, nel frattempo, secondo un calendario. Ogni controllo preleva un nuovo campione del servizio in esercizio e ne confronta il tasso di successo con il limite che la baseline implica per un campione di quella dimensione. Un tasso inferiore al limite viene segnalato come degrado, con un livello di confidenza dichiarato, tipicamente il 95 %, prima che raggiunga la produzione, e a maggior ragione un'autorità di vigilanza.

**Comply.** La baseline è la registrazione, il monitoraggio è l'evidenza e il
metodo è documentato pubblicamente: lo
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) espone
la statistica e i [framework open source](https://mavai.org/projects/) la
implementano riga per riga. Insieme costituiscono la documentazione tecnica richiesta dall'AI Act europeo e da altri orientamenti normativi riguardanti la gestione dei sistemi di IA, e ciò che qualsiasi autorità, revisore o
standard può leggere.

Ogni misurazione e ogni verdetto sono una registrazione strutturata che indica che cosa è stato misurato e quante volte, e, per ogni verdetto, il limite adottato e il livello di confidenza dell'affermazione. Quella registrazione è l'evidenza di cui hai bisogno.

## Ciò che il metodo fornisce

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
      <th>Il quadro richiede</th>
      <th>L'evidenza di cui hai bisogno</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>FINMA:</strong> inventario e controllo dei rischi dell'IA</td>
      <td>Una baseline per servizio, che è al tempo stesso inventario e controllo</td>
    </tr>
    <tr>
      <td><strong>ISO/IEC 42001:</strong> valutazione delle prestazioni, miglioramento continuo</td>
      <td>Baseline e registrazioni di monitoraggio come evidenze del sistema di gestione</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 9:</strong> gestione dei rischi lungo il ciclo di vita con metriche definite</td>
      <td>Baseline su un numero dichiarato di chiamate; test contro di esse a un livello di confidenza dichiarato, a ogni modifica</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 72:</strong> monitoraggio post-commercializzazione</td>
      <td>Monitoraggio pianificato rispetto alla baseline, con segnalazione delle derive</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 11 e allegato IV:</strong> documentazione tecnica del metodo, dei suoi test e dei risultati</td>
      <td>Lo Statistical Companion e i framework open source documentano il metodo; le registrazioni conservate contengono test e risultati</td>
    </tr>
  </tbody>
</table>

Il Metodo Mavai può fornire l'evidenza per i requisiti di cui sopra e oltre.

## Ciò che i team dovrebbero sapere

### In sintesi

Semplicemente detto, Mavai.ch e Mavai.org [possono aiutarvi](https://mavai.org/how-we-help/) a gestire i nuovi requisiti e fornirvi gli strumenti per produrre l'evidenza di cui avete bisogno per adempiere a questi requisiti. Inoltre, gli strumenti open source di Mavai® che li implementano sono [punit](https://mavai.org/projects/punit/) per Java, [feotest](https://mavai.org/projects/feotest/) per Rust e [baseltest](https://mavai.org/projects/baseltest/) per Python.

Se state sviluppando o state già distribuendo un servizio di IA e volete assicurarvi di poter rispettare i requisiti di cui sopra, una prima conversazione copre dove si trova oggi il vostro progetto e come i suoi test devono cambiare. [Contattateci](/it/contact/) o [parlate direttamente con Mavai](https://mavai.org/contact/).

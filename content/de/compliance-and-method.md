---
title: "Compliance und die Mavai-Methode"
description: "Wie Anforderungen an die Überwachung von KI-Systemen durch die Mavai®-Methode erfüllt werden: Baseline · Monitor · Comply."
keywords: ["KI-Compliance Schweiz", "FINMA KI Nachweis", "ISO 42001 Nachweis", "EU KI-Gesetz Marktüberwachung", "KI-Baseline", "probabilistisches Testen Compliance"]
summary: "mavai.ch verfolgt, was die Regelwerke verlangen. mavai.org dokumentiert, wie Mavai darauf antwortet. Diese Seite verbindet beides: auf der einen Seite die Pflicht, auf der anderen der Schritt der Methode, der den Nachweis liefert."
---

mavai.ch beobachtet die regulatorische Landschaft in KI-Governance und deren Auswirkungen auf die Schweiz: was die
FINMA erwartet, was ISO/IEC 42001 zertifiziert und wo das EU-KI-Gesetz über die
Grenze hinaus wirkt. [mavai.org](https://mavai.org/) dokumentiert die Methode,
mit der Mavai® diese Erwartungen erfüllt. Diese Seite verbindet beides.

## Die Anforderungen

Jeder KI-Oversight- oder Qualitätsrahmen, den wir hier erwähnen, stellt dieselbe Frage in anderen Worten:
**Können Sie zeigen, dass Ihr KI-System so funktioniert, wie Sie es behaupten,
nicht einmal, sondern so lange es läuft?**

- Die **[FINMA](/de/regulations/finma/)** erwartet ein Inventar der
  KI-Anwendungen, eine Governance über deren Risiken und nachweisbare Kontrolle
  über das Modellverhalten.
- **[ISO/IEC 42001](/de/regulations/iso-42001/)** zertifiziert ein
  KI-Managementsystem mit eingebauter Leistungsbewertung und fortlaufender
  Verbesserung.
- Das **[EU-KI-Gesetz](/de/ai-regulation-switzerland/)** verlangt für
  Hochrisikosysteme ein Risikomanagement über den gesamten Lebenszyklus (Art. 9),
  eine Beobachtung nach dem Inverkehrbringen (Art. 72) und eine technische
  Dokumentation der Tests und Ergebnisse (Anhang IV), und es erfasst Schweizer
  Anbieter, deren Systeme in der EU eingesetzt werden.

Obwohl sich Anforderungen in ihrer Durchsetzung, ihrem Fokus und ihrer Reichweite unterscheiden, verlangen alle kontinuierliche Nachweise zur Leistung des Systems.

## Die Mavai®-Methode: Baseline · Monitor · Comply

Mavai nutzt für jeden KI-Dienst, den Sie bereitstellen, eine statistische Baseline, kontinuierliche Überwachung dagegen und eine öffentlich dokumentierte Methode – mit den Werkzeugen und dem Know-how, um sie in Ihre Delivery-Pipeline zu integrieren.

**Baseline.** Jeder einzelne Aufruf eines KI-Dienstes lässt sich als richtig
oder falsch beurteilen. Was niemand im Voraus weiss, ist, wie oft der Dienst
richtig liegt. Eine Baseline misst diese Rate über eine angegebene Zahl von Aufrufen und hält sie zusammen mit dem Modell, den Prompts und den Umständen der Messung
fest.

**Monitor.** Der laufende Dienst wird an seiner Baseline gemessen, so lange er
in Betrieb ist: bei jedem Release, jeder Änderung an Modell oder Prompts und
zwischendurch nach Zeitplan. Jede Prüfung zieht eine neue Stichprobe des laufenden Dienstes und vergleicht deren Erfolgsrate mit der Schranke, die die Baseline für eine Stichprobe dieser Grösse vorgibt. Eine Rate unterhalb der Schranke wird als Verschlechterung gemeldet, mit angegebener Konfidenz, typischerweise 95 %, bevor sie die Produktion erreicht, geschweige denn eine Aufsichtsbehörde.

**Comply.** Die Baseline ist der Datensatz, das Monitoring ist der Nachweis,
und die Methode ist öffentlich dokumentiert: der
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) legt die
Statistik dar, und die
[Open-Source-Frameworks](https://mavai.org/projects/) setzen sie Zeile für Zeile
um. Zusammen sind sie die technische Dokumentation, die das EU-KI-Gesetz und andere regulatorische Richtlinien bezüglich der Verwaltung von KI-Systemen verlangen, und das, was jede Aufsichtsbehörde, jeder Prüfer und jeder Standard lesen kann.

Jede Messung und jedes Urteil ist ein strukturierter Datensatz, der festhält, was gemessen wurde und wie oft, und für jedes Urteil die Schranke, an der es gemessen wurde, und die Konfidenz der Aussage.
Dieser Datensatz ist der Nachweis, den Sie benötigen.

## Was die Methode bietet

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
      <th>Der Rahmen verlangt</th>
      <th>Die Evidenz, die Sie benötigen</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>FINMA:</strong> Inventar und Kontrolle der KI-Risiken</td>
      <td>Eine Baseline pro Dienst, die zugleich Inventar und Kontrolle ist</td>
    </tr>
    <tr>
      <td><strong>ISO/IEC 42001:</strong> Leistungsbewertung, fortlaufende Verbesserung</td>
      <td>Baselines und Monitoring-Datensätze als Nachweise für das Managementsystem</td>
    </tr>
    <tr>
      <td><strong>EU-KI-Gesetz Art. 9:</strong> Risikomanagement über den Lebenszyklus mit definierten Metriken</td>
      <td>Baselines über eine angegebene Zahl von Aufrufen; Tests dagegen mit angegebener Konfidenz, bei jeder Änderung</td>
    </tr>
    <tr>
      <td><strong>EU-KI-Gesetz Art. 72:</strong> Beobachtung nach dem Inverkehrbringen</td>
      <td>Planmässiges Monitoring gegen die Baseline mit Meldung von Abweichungen</td>
    </tr>
    <tr>
      <td><strong>EU-KI-Gesetz Art. 11 und Anhang IV:</strong> technische Dokumentation der Methode, ihrer Tests und Ergebnisse</td>
      <td>Statistical Companion und Open-Source-Frameworks dokumentieren die Methode; die gespeicherten Datensätze enthalten Tests und Ergebnisse</td>
    </tr>
  </tbody>
</table>

Die Mavai-Methode kann die Nachweise für die oben genannten Anforderungen und mehr liefern.

## Was Teams wissen sollten

### Zusammenfassung

Einfach gesagt: Mavai.ch und Mavai.org [können Ihnen helfen](https://mavai.org/how-we-help/), neue Anforderungen zu verwalten und Ihnen die Werkzeuge zur Verfügung zu stellen, um die Nachweise zu produzieren, die Sie benötigen, um diese Anforderungen zu erfüllen. Darüber hinaus implementieren die Open-Source-Werkzeuge von Mavai® diese: [punit](https://mavai.org/projects/punit/) für Java, [feotest](https://mavai.org/projects/feotest/) für Rust und [baseltest](https://mavai.org/projects/baseltest/) für Python.

Wenn Sie einen KI-Dienst entwickeln oder bereits bereitstellen und sicherstellen möchten, dass Sie die oben genannten Anforderungen erfüllen können, deckt ein erstes Gespräch ab, wo Ihr Projekt heute steht und wie sich sein Testen ändern muss. [Kontaktieren Sie uns](/de/contact/) oder [sprechen Sie direkt mit Mavai](https://mavai.org/contact/).

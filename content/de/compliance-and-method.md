---
title: "Compliance und die Mavai-Methode"
description: "Wie die auf mavai.ch verfolgten Pflichten durch die auf mavai.org dokumentierte Methode erfüllt werden: Baseline · Monitor · Comply."
keywords: ["KI-Compliance Schweiz", "FINMA KI Nachweis", "ISO 42001 Nachweis", "EU KI-Gesetz Marktüberwachung", "KI-Baseline", "probabilistisches Testen Compliance"]
summary: "mavai.ch verfolgt, was Aufsichtsbehörden verlangen. mavai.org dokumentiert, wie Mavai darauf antwortet. Diese Seite verbindet beides: auf der einen Seite die Pflicht, auf der anderen der Schritt der Methode, der den Nachweis liefert."
---

mavai.ch beobachtet die regulatorische Landschaft für KI in der Schweiz: was die
FINMA erwartet, was ISO/IEC 42001 zertifiziert und wo das EU-KI-Gesetz über die
Grenze hinaus wirkt. [mavai.org](https://mavai.org/) dokumentiert die Methode,
mit der Mavai diese Erwartungen erfüllt. Diese Seite verbindet beides.

## Die Pflicht

Jedes Regelwerk auf dieser Website stellt dieselbe Frage in anderen Worten:
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

Keine dieser Pflichten lässt sich mit einer einmaligen Prüfung vor dem Go-live
erfüllen. Jede verlangt Nachweise, die fortlaufend entstehen.

## Die Methode: Baseline · Monitor · Comply

**Baseline.** Jeder einzelne Aufruf eines KI-Dienstes lässt sich als richtig
oder falsch beurteilen. Was niemand im Voraus weiss, ist, wie oft der Dienst
richtig liegt. Eine Baseline misst diese Rate mit einer angegebenen Konfidenz
und hält sie zusammen mit dem Modell, den Prompts und den Umständen der Messung
fest.

**Monitor.** Der laufende Dienst wird an seiner Baseline gemessen, so lange er
in Betrieb ist: bei jedem Release, jeder Änderung an Modell oder Prompts und
zwischendurch nach Zeitplan. Abweichungen über die vereinbarten Grenzen hinaus
werden mit der Konfidenz der Baseline gemeldet, bevor sie die Produktion
erreichen, geschweige denn eine Aufsichtsbehörde.

**Comply.** Der Dienst wird an einem Vertrag gemessen: was eine gute Antwort
ist und mit welcher Rate sie geliefert werden muss. Jedes Urteil sagt, ob der
Dienst den Vertrag erfüllt, mit der angegebenen Konfidenz. Wo eine
Aufsichtsbehörde die Messlatte setzt, trägt derselbe Vertrag sie.

Jede Messung und jedes Urteil ist ein strukturierter Datensatz, der festhält,
was gemessen wurde, wie oft, gegen welche Messlatte und mit welcher Konfidenz.
Dieser Datensatz ist der Nachweis.

## Von der Pflicht zum Nachweis

| Das Regelwerk verlangt | Die Methode liefert |
|---|---|
| FINMA: Inventar und Kontrolle der KI-Risiken | Eine Baseline pro Dienst, die zugleich Inventar und Kontrolle ist |
| ISO/IEC 42001: Leistungsbewertung, fortlaufende Verbesserung | Baselines und Monitoring-Datensätze als Nachweise für das Managementsystem |
| EU-KI-Gesetz Art. 9: Risikomanagement über den Lebenszyklus mit definierten Metriken | Baselines mit festgelegten Schwellen und Konfidenz; Tests bei jeder Änderung |
| EU-KI-Gesetz Art. 72: Beobachtung nach dem Inverkehrbringen | Planmässiges Monitoring gegen die Baseline mit Meldung von Abweichungen |
| EU-KI-Gesetz Anhang IV: dokumentierte Testverfahren und Ergebnisse | Die gespeicherten Mess- und Urteilsdatensätze |

Die Namen unterscheiden sich. Der Nachweis ist derselbe Nachweis, und es ist der
Nachweis, den ein Team ohnehin haben wollte. Die Regulierung ist der Grund,
weshalb mehr Teams jetzt danach fragen; die Methode ist dieselbe für jedes Team,
dessen Dienst sich als Rate und nicht als einzelner Wert verhält.

## Nächste Schritte

Die vollständige Methode, in geschäftlichen Begriffen, steht unter
[How We Help](https://mavai.org/how-we-help/). Die Open-Source-Werkzeuge, die
sie umsetzen, sind [punit](https://mavai.org/projects/punit/) für Java,
[feotest](https://mavai.org/projects/feotest/) für Rust und
[baseltest](https://mavai.org/projects/baseltest/) für Python.

Wenn Sie einen KI-Dienst unter einem der genannten Regelwerke einsetzen, klärt
ein erstes Gespräch, wo Ihr Projekt heute steht und wie sich sein Testen ändern
muss. [Kontaktieren Sie uns](/de/contact/) oder
[sprechen Sie direkt mit Mavai](https://mavai.org/contact/).

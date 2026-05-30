---
title: "Risiko messen: einfach. Wahrscheinlichkeit messen: schwer."
date: 2026-04-02
description: "Die meisten Organisationen, die KI einsetzen, können eine grundlegende Frage nicht beantworten: Wie oft versagt dieses System? Probabilistisches Testen liefert die Antwort — und die Regulierungsbehörden beginnen, dies einzufordern."
summary: "Die NASA-Risikomatrix hat zwei Achsen. Auswirkung ist handhabbar. Wahrscheinlichkeit — insbesondere bei stochastischen KI-Systemen — nicht. Regulierungsbehörden verlangen jetzt quantitative Nachweise, und probabilistisches Testen ist der Weg dorthin."
image: /images/swiss-risk-matrix-de.svg
author: "Mike Mannion"
---

<figure>
<img src="/images/swiss-risk-matrix-de.svg" alt="NASA-Risikomatrix — Auswirkung auf der horizontalen Achse, Wahrscheinlichkeit auf der vertikalen">
<figcaption>Die NASA-Risikomatrix: relevanter denn je</figcaption>
</figure>

Die NASA-Risikomatrix stellt Auswirkung auf einer Achse und Wahrscheinlichkeit auf der anderen dar. Sie findet sich in allem von der Raumfahrttechnik bis zur Finanzregulierung. Aber die beiden Achsen sind nicht gleich schwer zu bewerten.

Auswirkung — monetäre Kosten, Sanierungsaufwand, regulatorische Strafen, Reputationsschäden — ist aufwendig, aber die Methoden sind etabliert. Versicherungswesen, Aktuarwissenschaft und qualitätsbereinigte Gesundheitsmetriken quantifizieren Auswirkungen seit Jahrhunderten.

Wahrscheinlichkeit ist eine andere Sache. Für einige Risiken liefern historische Daten eine empirische Grundlage. Für neuartige Systeme — beispiellose Bedingungen, emergentes Verhalten, KI-Modelle, die mit Überzeugung falsche Antworten liefern — gibt es oft keine Erfahrungswerte. Diese Fragen werden nicht gestellt oder mit Intuition beantwortet, die als Analyse verkleidet ist.

## LLMs haben die Frage unausweichlich gemacht

Grosse Sprachmodelle sind von Grund auf stochastisch. Ein einzelner Testlauf sagt fast nichts aus. Die Frage «Funktioniert das?» muss ersetzt werden durch «Wie oft funktioniert das?» — und die meisten Organisationen, die LLM-basierte Systeme einsetzen, haben keine belastbare Antwort.

Das ist kein Nischenthema. LLMs werden im Kundenservice, in der medizinischen Triage, in der juristischen Recherche und in der Finanzanalyse eingesetzt — Bereiche, in denen Vertrauen in das Systemverhalten enorm wichtig ist. Doch die den meisten Teams zur Verfügung stehenden Testwerkzeuge liefern binäre Urteile, wenn eigentlich eine Wahrscheinlichkeitsverteilung benötigt wird.

## Die Regulierungsbehörden werden aufmerksam

Die FINMA hat im Dezember 2024 die Aufsichtsmitteilung 08/2024 [1] veröffentlicht und damit Erwartungen an die KI-Governance in beaufsichtigten Instituten formuliert. Bemerkenswert ist, dass die FINMA von den Instituten den Einsatz von *Leistungsindikatoren* erwartet — quantitative Masse dafür, wie gut ein System seine Ziele erreicht. Das ist eine deutliche Abkehr vom Pass/Fail-Testen. Es erfordert Messung, nicht bloss Verifikation.

Die Pflichten des EU AI Act für Hochrisiko-KI-Systeme — einschliesslich solcher in Finanzwesen, Gesundheitswesen und öffentlicher Verwaltung — gelten ab August 2026 [2], und Schweizer Organisationen mit EU-Kunden müssen ungeachtet des Nicht-Mitgliedsstatus der Schweiz die Vorschriften einhalten. Die Anforderungen umfassen deklarierte Genauigkeitsniveaus, laufende Leistungsüberwachung und Risikomanagement-Tests (Artikel 9, 15 und 72 der Verordnung 2024/1689). ISO 42001 [3] verlangt von Organisationen die Definition und Verfolgung von Leistungskennzahlen.

Die regulatorische Richtung ist klar: Organisationen müssen mit Belegen nachweisen, dass ihre KI-Systeme innerhalb akzeptabler Grenzen arbeiten. «Wir glauben, dass es funktioniert» wird Prüfer nicht zufriedenstellen.

## Die Antwort ist statistisch

Wenn die Frage «Wie oft funktioniert das?» lautet, muss die Methode statistisch sein. Probabilistisches Testen führt einen Test viele Male durch, misst die Erfolgsrate und wendet statistische Inferenz an, um festzustellen, ob die beobachtete Rate einen definierten Schwellenwert bei einem gegebenen Konfidenzniveau erreicht. Es verwandelt Compliance von einer subjektiven Beurteilung in eine messbare, überprüfbare Aussage.

Das [punit](https://github.com/mavai-org/punit)-Framework implementiert diesen Ansatz für Java-Anwendungen. Technische Details und Einstiegshilfen finden Sie auf [mavai.org](https://mavai.org).

Wenn eines sicher ist, dann dass es Zeit ist, Unsicherheit ernst zu nehmen.

---

*Für den regulatorischen Kontext siehe [Warum probabilistisches Testen für die Schweizer Regulierung wichtig ist](/de/posts/2026-03-21-why-probabilistic-testing/).*

## Quellen

[1] FINMA-Aufsichtsmitteilung 08/2024 — Governance und Risikomanagement beim Einsatz von Künstlicher Intelligenz, 18. Dezember 2024. https://www.finma.ch/de/news/2024/12/20241218-mm-finma-am-08-24/

[2] Verordnung (EU) 2024/1689 — Gesetz über Künstliche Intelligenz. Pflichten für Hochrisiko-KI-Systeme (Artikel 9, 15, 72) gelten ab 2. August 2026. https://eur-lex.europa.eu/legal-content/DE/TXT/?uri=CELEX:32024R1689

[3] ISO/IEC 42001:2023 — Informationstechnologie — Künstliche Intelligenz — Managementsystem. https://www.iso.org/standard/81230.html

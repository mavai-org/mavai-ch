---
title: "Warum probabilistisches Testen für die Schweizer Regulierung wichtig ist"
date: 2026-03-21
description: "Eine Einführung, warum traditionelles Testen für die KI-Compliance nicht ausreicht."
summary: "Schweizer Regulierungsbehörden verlangen Nachweise, dass KI-Systeme zuverlässig funktionieren. Traditionelles Testen kann diese nicht liefern. Hier erfahren Sie, warum statistische Ansätze die Lösung sind."
---

Wenn eine Bank ein KI-Modell zur Kreditrisikobewertung einsetzt oder eine kantonale Behörde maschinelles Lernen zur Antragstriage verwendet, folgt eine natürliche Frage: Woher wissen Sie, dass es funktioniert?

Für traditionelle Software ist die Antwort einfach. Man schreibt Tests. Jeder Test prüft, ob eine bestimmte Eingabe eine bestimmte Ausgabe erzeugt. Wenn alle Tests bestehen, hat man Vertrauen in das System.

KI funktioniert so nicht.

## Das Nicht-Determinismus-Problem

KI-Systeme — insbesondere solche, die auf grossen Sprachmodellen oder statistischem Lernen basieren — sind von Natur aus nicht-deterministisch. Stellen Sie dieselbe Frage zweimal und Sie erhalten möglicherweise unterschiedliche Antworten. Das ist kein Fehler; es ist eine grundlegende Eigenschaft dieser Systeme.

Dies schafft ein Problem für die Compliance. Die FINMA erwartet Modellvalidierung. ISO 42001 verlangt Leistungsmessung. Prüfer wollen Nachweise. Aber Nachweise *wofür* genau, wenn die Systemausgabe designbedingt variiert?

## Von Pass/Fail zu Erfolgsraten

Die Antwort liegt im Wechsel von binärem Testen (hat es funktioniert?) zu statistischem Testen (wie oft funktioniert es, und ist das oft genug?).

Anstatt zu behaupten, dass eine Funktion einen bestimmten Wert zurückgibt, führt man sie viele Male aus und fragt, ob die beobachtete Erfolgsrate einen definierten Schwellenwert bei einem gegebenen Konfidenzniveau erreicht. Dies ist die Grundlage des probabilistischen Testens.

Für einen Geschäftsführer ist die Analogie die Qualitätskontrolle in der Fertigung. Man testet nicht jedes einzelne Stück — man testet eine Stichprobe und trifft ein statistisches Urteil über die Charge. Dasselbe Prinzip gilt für KI.

## Was das in der Praxis bedeutet

Probabilistisches Testen produziert strukturierte, quantitative Nachweise: Erfolgsraten, Konfidenzintervalle, Latenzverteilungen, Trenddaten. Das ist genau die Art von Nachweisen, die regulatorische Rahmenwerke verlangen.

Es transformiert KI-Compliance von einem subjektiven Urteil («wir denken, es funktioniert») in eine messbare, prüfbare Aussage («wir haben mit 95% Konfidenz nachgewiesen, dass die Erfolgsrate dieses Systems 99,5% übersteigt»).

Für technische Implementierungsdetails besuchen Sie [mavai.org](https://mavai.org).

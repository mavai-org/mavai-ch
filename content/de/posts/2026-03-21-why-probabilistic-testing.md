---
title: "Warum probabilistische Tests für Schweizer Regulierung wichtig sind"
date: 2026-03-21
description: "Eine Einführung, warum traditionelles Testen für KI-Compliance nicht ausreicht."
summary: "Schweizer Behörden fordern den Nachweis, dass KI-Systeme zuverlässig funktionieren. Traditionelles Testen kann dies nicht leisten. Hier erfahren Sie, warum statistische Methoden die Lösung sind."
---

Wenn eine Bank ein KI-Modell zur Kreditrisikobewertung einsetzt oder eine Kantonsregierung maschinelles Lernen zur Priorisierung von Anträgen nutzt, folgt eine natürliche Frage: Woher weiss man, dass es funktioniert?

Bei traditioneller Software ist die Antwort unkompliziert. Man schreibt Tests. Jeder Test überprüft, ob ein bestimmter Input einen bestimmten Output erzeugt. Wenn alle Tests bestanden werden, hat man Vertrauen in das System.

KI funktioniert anders.

## Das Nichtdeterminismus-Problem

KI-Systeme – besonders solche auf Basis grosser Sprachmodelle oder statistischem Lernen – sind grundsätzlich nicht deterministisch. Stellt man zweimal die gleiche Frage, kann man unterschiedliche Antworten erhalten. Dies ist kein Bug; es ist eine fundamentale Eigenschaft dieser Systeme.

Dies schafft ein Compliance-Problem. FINMA erwartet Modellvalidierung. ISO 42001 fordert Leistungsmessung. Prüfer wollen Nachweise. Aber Nachweise für *was* genau, wenn sich die Ausgabe des Systems konstruktionsbedingt unterscheidet?

## Von Ja/Nein zu Erfolgsquoten

Die Antwort liegt darin, vom binären Testen (funktioniert es?) zum statistischen Testen (wie oft funktioniert es und ist das oft genug?) überzugehen.

Statt zu behaupten, dass eine Funktion einen bestimmten Wert zurückgibt, führt man sie viele Male aus und fragt, ob die beobachtete Erfolgsquote einen definierten Schwellenwert bei einem bestimmten Konfidenzniveau erreicht. Dies ist die Grundlage probabilistischen Testens.

Für Geschäftsführer ist die Analogie die Qualitätskontrolle in der Fertigung. Man testet nicht jeden einzelnen Artikel – man testet eine Stichprobe und trifft eine statistische Bewertung über die Charge. Das gleiche Prinzip gilt für KI.

## Was dies in der Praxis bedeutet

Probabilistische Tests erzeugen strukturierte, quantitative Nachweise: Erfolgsquoten, Konfidenzintervalle, Latenzverteilungen, Trenddaten. Dies ist genau die Art von Nachweisen, die regulatorische Rahmenwerke fordern.

Es transformiert KI-Compliance von einer subjektiven Bewertung („wir denken, es funktioniert") in eine messbare, prüfbare Aussage („wir haben mit 95% Konfidenz nachgewiesen, dass die Erfolgsquote dieses Systems 99,5% übersteigt").

Für technische Implementierungsdetails besuchen Sie [javai.org](https://javai.org).

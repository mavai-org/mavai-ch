---
title: "Microsoft 365 E7 und Copilot: Was Schweizer Privatbanken beachten sollten"
date: 2026-04-06
description: "Microsoft 365 E7 bündelt Copilot in die Unternehmenslizenzierung. Für Schweizer Privatbanken ist die Chance real — aber ebenso die Fragen zu Governance, Datensouveränität und Aufsicht."
summary: "Microsoft 365 E7 macht KI-gestützte Produktivität im Frontoffice kommerziell zugänglich. Doch für eine Schweizer Privatbank ist die Lizenz der einfache Teil. Vertraulichkeit, Datensouveränität, FINMA-Erwartungen und der US CLOUD Act bestimmen, ob und wie Copilot sicher eingesetzt werden kann."
---

[Microsoft hat Microsoft 365 E7 am 9. März 2026 angekündigt](https://blogs.microsoft.com/blog/2026/03/09/introducing-the-first-frontier-suite-built-on-intelligence-trust/), mit allgemeiner Verfügbarkeit ab 1. Mai 2026. Die Suite bündelt Microsoft 365 E5, Microsoft 365 Copilot, Agent 365 und die Microsoft Entra Suite in einer einzigen Lizenz. Für Privatbanken, die KI-gestützte Produktivität im Frontoffice evaluieren, wird der kommerzielle Einstieg damit erstmals unkompliziert.

Das Wertversprechen liegt auf der Hand. Kundenberater und ihre Assistenten verbringen erhebliche Zeit mit dem Verfassen, Überarbeiten, Übersetzen und Feinschliff von Kundenkorrespondenz. Copilot arbeitet direkt in Outlook, Word, Teams und Excel — genau den Werkzeugen, die das Frontoffice bereits nutzt. Frühe Anwendungsfälle wie das Entwerfen von Routinenachrichten, das Übersetzen freigegebener Inhalte, das Zusammenfassen von Besprechungsnotizen und die Anpassung des Tons an internationale Kundschaft liegen gut im Leistungsspektrum des Tools.

Auch das Markt-Timing spricht dafür. Die [FINMA-Umfrage 2025](https://www.finma.ch/de/news/2025/04/20250424-mm-umfrage-ki/) ergab, dass etwa die Hälfte der befragten Schweizer Finanzinstitute KI bereits im Tagesgeschäft einsetzt. Eine Bank, die diesen Bereich sorgfältig erkundet, handelt nicht ungewöhnlich — sie bewegt sich mit einem Branchentrend, der bereits deutlich sichtbar ist.

Doch die Lizenz ist nicht die Herausforderung. Das Kontrollmodell ist es.

## Die eigentliche Frage

Für eine Schweizer Privatbank lautet die Frage nicht, ob Copilot das Verfassen von E-Mails beschleunigen kann. Das kann es. Die Frage ist, ob KI-gestütztes Entwerfen erlaubt werden kann, ohne Vertraulichkeit, Verhaltensdisziplin, Angemessenheitskontrollen, Nachvollziehbarkeit und aufsichtsrechtliche Vertretbarkeit zu gefährden.

Die [FINMA-Aufsichtsmitteilung 08/2024](https://www.finma.ch/de/news/2024/12/20241218-mm-finma-am-08-24/) ist unmittelbar einschlägig. Beaufsichtigte Institute sollen Risiken aus internen und externen KI-Anwendungen identifizieren, bewerten, steuern und überwachen. Diese Leitlinie verbietet den Einsatz von Werkzeugen wie Copilot nicht, setzt aber eine klare Erwartung: KI gehört in Governance-Strukturen, nicht ausserhalb davon.

## Drei Risiken, die zählen

**Berechtigungsverstärkung.** [Microsoft erklärt](https://learn.microsoft.com/de-de/copilot/microsoft-365/microsoft-365-copilot-privacy), dass Copilot nur auf Daten zugreift, für die ein Benutzer bereits autorisiert ist, und bestehende Berechtigungen, Bezeichnungen und Aufbewahrungskontrollen übernimmt. Das klingt beruhigend — bedeutet aber, dass schwache Berechtigungshygiene gefährlicher wird, nicht weniger gefährlich. Sind SharePoint-, Exchange-, Teams- oder OneDrive-Berechtigungen zu weit gefasst, macht Copilot überexponierte Informationen leichter auffindbar und wiederverwendbar.

**Datengrenzen-Lücken.** [Microsoft gibt an](https://learn.microsoft.com/de-de/copilot/microsoft-365/microsoft-365-copilot-privacy), dass Prompts und Antworten unter dem Enterprise-Datenschutz nicht zum Training von Foundation-Modellen verwendet werden — eine wichtige Schutzklausel. Allerdings [dokumentiert Microsoft](https://learn.microsoft.com/de-de/privacy/microsoft-365-copilot-eu-data-boundary), dass Websuchanfragen von der EU-Datengrenze ausgenommen sind und dass die Nutzung von Anthropic-Modellen ausserhalb der EU-Datengrenze liegt, wenn diese in bestimmten Copilot-Erfahrungen aufgerufen werden. Für eine Schweizer Bank schliesst das die Plattform nicht aus, bedeutet aber, dass Entscheidungen auf Feature-Ebene zählen.

**Ausgaberisiko.** Im Private Banking kann ein plausibel klingender, aber ungenauer Entwurf rechtliche, verhaltens-, reputations- oder aufsichtsrechtliche Probleme verursachen. Formulierungen können Anlagepositionierung, Produktdarstellung, Performanceimplikationen oder vertraulichen Beziehungskontext berühren. Generative KI versteht das regulatorische Gewicht dessen, was sie schreibt, nicht.

## Datensouveränität ist nicht nur eine Frage des Rechenzentrums

Microsoft betreibt zwei Azure-Regionen in der Schweiz — Zürich und Genf. Zentrale Microsoft-365-Arbeitslasten können mit Data-at-Rest in diesen Regionen bereitgestellt werden. Die Infrastruktur, um Daten in der Schweiz zu halten, existiert.

Aber Copilot führt Verarbeitungsflüsse ein, die über die reine Datenspeicherung hinausgehen. Wenn ein Benutzer Copilot aufruft, werden der Prompt und der umgebende Kontext an ein grosses Sprachmodell zur Inferenz gesendet. [Microsoft hat sich verpflichtet](https://learn.microsoft.com/de-de/privacy/microsoft-365-copilot-eu-data-boundary), Daten von EU-Datengrenze-Kunden innerhalb der EU zu verarbeiten — aber die Schweiz ist nicht in der EU. Ob Microsoft sich vertraglich verpflichtet, Copilot-Prompts für Schweizer Mandanten innerhalb schweizerischer oder europäischer Grenzen zu verarbeiten, bedarf der Prüfung. Web-Grounding und Anthropic-gestützte Modellanrufe liegen explizit ausserhalb der EU-Datengrenze — konkrete Pfade, auf denen Daten die Schweiz verlassen können, selbst wenn das zugrundeliegende Postfach in Zürich bleibt.

Das tiefere Problem ist die rechtliche Zuständigkeit. Microsoft ist ein US-Unternehmen, das dem [US CLOUD Act](https://www.congress.gov/bill/115th-congress/house-bill/4943) unterliegt, der US-Anbieter zur Herausgabe von Daten verpflichten kann, unabhängig davon, wo diese gespeichert sind, sowie einer möglichen FISA Section 702-Exposition für Nicht-US-Personen. Das Schweizer Bankgeheimnis nach Art. 47 des Bankengesetzes schafft eine direkte Spannung. Eine Bank, die Kundenkommunikationsdaten in einem Microsoft-Mandanten speichert — auch in einem Schweizer Rechenzentrum — muss prüfen, ob US-Herausgabeanordnungen eine Offenlegung erzwingen könnten, die gegen Schweizer Recht verstösst.

«Schweizer Rechenzentrum» und «Schweizer Datensouveränität» sind nicht dasselbe.

## Wie ein kontrollierter Rollout aussieht

Der umsichtige Weg ist eine bedingte Genehmigung innerhalb eines definierten Governance-Rahmens, kein offener Rollout. Die angemessene Haltung ist, dass Copilot beim Entwerfen, Umformulieren, Zusammenfassen und Formatieren unterstützen darf, aber ein menschlicher Bankberater für jede ausgehende Kundenkorrespondenz verantwortlich bleibt.

Ein Kontrollrahmen vor jeder Frontoffice-Bereitstellung würde typischerweise umfassen:

- **Definierte zulässige Anwendungsfälle.** Routinemässige Service-E-Mails entwerfen, freigegebene Formulierungen übersetzen, Besprechungsnotizen zusammenfassen, Ton an den Hausstil anpassen — ja. Freie Generierung von Anlageberatung, rechtlichen Zusagen oder Produktaussagen — nein, es sei denn streng vorlagenbasiert und separat kontrolliert.
- **Datenzugriffs-Hygieneprüfung.** Da Copilot bestehende Berechtigungen übernimmt, ist eine Berechtigungsbereinigung über Exchange, SharePoint, Teams und OneDrive kein optionaler Schritt — sie ist Teil der Bereitschaft.
- **Entscheidungen auf Feature-Ebene.** Web-Grounding, Agent-Erstellung und alle Features, die Modelle oder Verarbeitungspfade ausserhalb bevorzugter Grenzen aufrufen, sollten einzeln bewertet werden. Microsofts Dokumentation ist explizit darüber, welche Features eigene Datengrenzen-Implikationen haben.
- **Menschliche Überprüfung vor dem Versand.** Alle kundenseitigen E-Mails, die von KI generiert oder wesentlich überarbeitet wurden, sollten vom verantwortlichen Bankberater vor dem Versand geprüft werden. KI darf vorschlagen; sie sollte nicht autonom kommunizieren.
- **Überwachung und Evidenz.** Stichproben generierter Entwürfe, Kategorisierung von Fehlermodi, Sprachqualitätsprüfungen, Vertraulichkeitsvorfälle und ein dokumentiertes Entscheidungsprotokoll. Die FINMA erwartet von beaufsichtigten Instituten, dass sie KI-bezogene Risiken steuern und überwachen, und solche Nachweise wären bei jeder aufsichtlichen Überprüfung relevant.

## Fazit

Eine Schweizer Privatbank kann einen glaubwürdigen Fall für Microsoft 365 E7 und Copilot im Frontoffice machen. Die kurzfristigen Produktivitätsgewinne — insbesondere für mehrsprachiges Entwerfen und Kommunikationsunterstützung — sind real. Aber die richtige Rahmung ist nicht «E7 kaufen und Banker KI nutzen lassen». Die richtige Rahmung ist: E7 lizenzieren, dann KI-gestützte Kundenkommunikation unter einem eng definierten, überwachten, prüfungsbasierten Kontrollregime einführen.

Das ist die Version, die am ehesten Produktivität liefert, ohne ein Governance-Problem zu schaffen, das nicht hätte entstehen müssen.

---
title: "KI-Regulierung in der Schweiz: Was sich ändert und was zu tun ist"
description: "Ein praxisorientierter Überblick über die KI-Regulierung mit Bezug zur Schweiz — FINMA-Aufsichtserwartungen, ISO/IEC 42001 und die Ausstrahlung des EU-KI-Gesetzes — und wie Schweizer Organisationen mit Nachweisen reagieren können."
keywords: ["KI-Regulierung Schweiz", "FINMA KI Anforderungen", "ISO 42001 Schweiz", "EU KI-Gesetz Schweiz", "KI-Compliance Schweiz", "KI-Governance Schweiz", "Schweizer KI-Recht", "KI-Risikomanagement"]
summary: "Die Schweiz hat kein einzelnes KI-Gesetz, doch die Finanzmarktaufsicht, internationale Managementstandards und die extraterritoriale Reichweite des EU-KI-Gesetzes prägen bereits, was Schweizer Organisationen tun müssen. Der gemeinsame Nenner ist der Nachweis — und ihn zu erbringen ist ein Messproblem."
---

Die Schweiz verfügt über kein einzelnes, eigenständiges KI-Gesetz. Dieses Fehlen
könnte als Fehlen von Pflichten missverstanden werden. Das ist es nicht.
KI-Systeme, die von Schweizer Banken, Versicherern, Spitälern,
Pharmaunternehmen und öffentlichen Stellen eingesetzt werden, unterliegen
bereits einem Geflecht aus Aufsichtserwartungen, internationalen Standards und
extraterritorialen Regeln. Zu verstehen, wie diese zusammenpassen — und was sie
gemeinsam haben —, ist der erste Schritt zu einer belastbaren Compliance-Haltung.

Der gemeinsame Nenner ist der **Nachweis**. Jedes der unten genannten Regelwerke
stellt in seiner eigenen Sprache dieselbe grundlegende Frage: *Können Sie
belegen, dass Ihr KI-System dauerhaft innerhalb akzeptabler Grenzen
funktioniert?* Für nicht-deterministische Systeme — alles, was auf grossen
Sprachmodellen oder maschinellem Lernen beruht — ist das keine Ja/Nein-Frage.
Es ist ein Messproblem.

## FINMA: Aufsicht ohne «KI-Gesetz»

Die Eidgenössische Finanzmarktaufsicht (FINMA) hat keine KI-spezifische
Regulierung erlassen und muss dies möglicherweise auch nicht. Ihr bestehendes
Rahmenwerk für Modellrisiken, operationelle Risiken und Governance gilt direkt
für KI-Systeme im Banken- und Versicherungswesen. Von beaufsichtigten Instituten
wird erwartet, dass sie Modelle vor und während des Einsatzes validieren, das
operationelle Risiko rund um kritische Systeme steuern, klare Verantwortlichkeit
wahren und erklären und belegen können, wie ein System zu seinen Ergebnissen
gelangt.

Bei einem deterministischen Modell kann sich die Validierung auf reproduzierbare
Ergebnisse stützen. Bei einem stochastischen KI-System ist «es hat bei der
Prüfung funktioniert» keine Validierung — der nächste Durchlauf kann anders
ausfallen. Was die FINMA-Erwartungen in der Praxis nahelegen, ist die
Notwendigkeit, das Verhalten statistisch zu charakterisieren: nicht eine
einzelne bestandene Prüfung, sondern eine quantifizierte Aussage darüber, wie
oft das System seinen Vertrag erfüllt.

Siehe unsere ausführliche Notiz zu [FINMA & KI im Bankwesen](/regulations/finma/).

## ISO/IEC 42001: ein Managementsystem für KI

ISO/IEC 42001 ist der internationale Standard für KI-Managementsysteme — das
KI-Pendant zu ISO 27001 für Informationssicherheit. Er ist freiwillig, doch
Schweizer Organisationen führen ihn zunehmend ein, weil die Zertifizierung einen
anerkannten, prüfbaren Weg bietet, verantwortungsvolle KI-Governance zu zeigen.
Der Standard verlangt Risikobeurteilung, definierte Kontrollen, Überwachung und
kontinuierliche Verbesserung über den gesamten KI-Lebenszyklus.

Überwachung und kontinuierliche Verbesserung setzen wiederum Messung voraus. Ein
Managementsystem, das nicht quantifizieren kann, wie seine KI-Systeme
tatsächlich funktionieren, weist eine Lücke zwischen den dokumentierten
Kontrollen und der betrieblichen Realität auf.

Siehe unsere Notiz zu [ISO/IEC 42001](/regulations/iso-42001/).

## Das EU-KI-Gesetz: extraterritoriale Reichweite in die Schweiz

Die Schweiz ist kein EU-Mitglied, doch das EU-KI-Gesetz kann für Schweizer
Organisationen gelten, deren KI-Systeme auf dem EU-Markt in Verkehr gebracht
werden oder deren Ergebnisse in der EU verwendet werden. Für Hochrisikosysteme
legt das Gesetz Anforderungen an Risikomanagement, Daten-Governance, technische
Dokumentation, Genauigkeit, Robustheit und nachgelagerte Überwachung fest.
Schweizer Exporteure und Schweizer Tochtergesellschaften von EU-Konzernen können
unabhängig vom inländischen Recht in den Anwendungsbereich fallen.

Die Begriffe «Genauigkeit» und «Robustheit» des Gesetzes sind erneut eine
Forderung nach quantifizierten Nachweisen über die Zeit — nicht nach einem
einmaligen Zertifikat.

## Der gemeinsame Nenner: von der Pflicht zum Nachweis

Über FINMA-Aufsicht, ISO/IEC 42001 und das EU-KI-Gesetz hinweg läuft die
praktische Anforderung zusammen:

- **Verhalten charakterisieren, nicht behaupten.** Ein nicht-deterministisches
  System braucht eine Beschreibung seiner Ergebnisverteilung, nicht einen
  einzelnen bestandenen Test.
- **Schwellenwerte explizit festlegen.** Welche Erfolgsquote bei welchem
  Vertrauensniveau ist für diesen Anwendungsfall akzeptabel?
- **Auf Abweichung überwachen.** Der Nachweis von gestern zertifiziert nicht
  das Modell von heute.
- **Prüfbar machen.** Nachweise, die eine Aufsichtsbehörde oder ein Prüfer
  einsehen kann, sind besser als Zusicherungen, die sie auf Vertrauen hinnehmen
  müssen.

Das sind zunächst keine rechtlichen Fragen. Es sind Messfragen — und sie sind
lösbar.

## Was zu tun ist

Die Disziplin, die diese Fragen beantwortet, ist das **probabilistische
Testen**: Jeder Durchlauf eines KI-Systems wird als Versuch behandelt, die
Erfolgsquote über viele Durchläufe beobachtet und statistische Inferenz
angewandt, um zu entscheiden, ob die wahre zugrundeliegende Quote einen
definierten Schwellenwert bei einem angegebenen Vertrauensniveau erreicht. Damit
wird aus «das Modell scheint in Ordnung» eine quantifizierte, wiederholbare,
prüfbare Aussage — genau die Form von Nachweis, nach der die obigen Regelwerke
greifen.

Die Open-Source-Werkzeuge dafür finden sich auf unserer technischen
Schwesterseite [mavai.org](https://mavai.org/) — darunter
[punit](https://mavai.org/projects/punit/) für Java und
[feotest](https://mavai.org/projects/feotest/) für Rust, validiert gegen ein
gemeinsames statistisches Orakel. Die Regulierung schafft die Pflicht;
probabilistisches Testen ist ein praktischer Weg, den Nachweis zu erbringen.

Um zu verfolgen, wie sich diese Anforderungen entwickeln, sehen Sie unseren
[Regulierungsnachrichten](/regulations/)-Feed und die ausführlicheren
[Einblicke](/posts/).

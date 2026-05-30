---
title: "Réglementation de l'IA en Suisse : ce qui change et comment agir"
description: "Un aperçu pratique de la réglementation de l'IA concernant la Suisse — attentes prudentielles de la FINMA, ISO/IEC 42001 et effet d'entraînement du règlement européen sur l'IA — et comment les organisations suisses peuvent répondre par des preuves."
keywords: ["réglementation IA Suisse", "exigences FINMA IA", "ISO 42001 Suisse", "règlement IA UE Suisse", "conformité IA Suisse", "gouvernance IA Suisse", "droit suisse de l'IA", "gestion des risques IA"]
summary: "La Suisse n'a pas de loi unique sur l'IA, mais la surveillance du secteur financier, les normes internationales de management et la portée extraterritoriale du règlement européen sur l'IA façonnent déjà ce que les organisations suisses doivent faire. Le fil conducteur est la preuve — et la produire est un problème de mesure."
---

La Suisse ne dispose pas d'une loi unique et dédiée à l'IA. Cette absence peut
être confondue avec une absence d'obligation. Il n'en est rien. Les systèmes
d'IA déployés par les banques, assureurs, hôpitaux, entreprises pharmaceutiques
et organismes publics suisses relèvent déjà d'une superposition d'attentes
prudentielles, de normes internationales et de règles extraterritoriales.
Comprendre comment ces éléments s'articulent — et ce qu'ils ont en commun — est
le premier pas vers une posture de conformité défendable.

Le fil conducteur est la **preuve**. Chacun des régimes ci-dessous pose, dans
son propre langage, la même question de fond : *pouvez-vous démontrer que votre
système d'IA fonctionne dans des limites acceptables, de manière constante, dans
la durée ?* Pour les systèmes non déterministes — tout ce qui repose sur de
grands modèles de langage ou l'inférence par apprentissage automatique — ce
n'est pas une question oui/non. C'est un problème de mesure.

## FINMA : une surveillance sans « loi sur l'IA »

L'Autorité fédérale de surveillance des marchés financiers (FINMA) n'a pas
édicté de réglementation spécifique à l'IA, et n'en a peut-être pas besoin. Son
cadre existant relatif au risque de modèle, au risque opérationnel et à la
gouvernance s'applique directement aux systèmes d'IA utilisés dans la banque et
l'assurance. Les établissements surveillés sont tenus de valider les modèles
avant et pendant leur déploiement, de gérer le risque opérationnel autour des
systèmes critiques, de maintenir une responsabilité claire, et de pouvoir
expliquer et étayer la manière dont un système parvient à ses résultats.

Pour un modèle déterministe, la validation peut s'appuyer sur des résultats
reproductibles. Pour un système d'IA stochastique, « cela fonctionnait lors de
notre vérification » n'est pas une validation — l'exécution suivante peut
différer. Ce que les attentes de la FINMA impliquent, en pratique, est la
nécessité de caractériser le comportement de façon statistique : non pas une
seule vérification réussie, mais une affirmation quantifiée sur la fréquence à
laquelle le système respecte son contrat.

Voir notre note détaillée sur [la FINMA & l'IA dans la banque](/regulations/finma/).

## ISO/IEC 42001 : un système de management pour l'IA

ISO/IEC 42001 est la norme internationale pour les systèmes de management de
l'IA — le pendant pour l'IA de l'ISO 27001 pour la sécurité de l'information.
Elle est volontaire, mais les organisations suisses l'adoptent de plus en plus
parce que la certification offre un moyen reconnu et auditable de montrer que
l'IA est gouvernée de manière responsable. La norme exige une évaluation des
risques, des contrôles définis, une surveillance et une amélioration continue
sur l'ensemble du cycle de vie de l'IA.

La surveillance et l'amélioration continue présupposent, là encore, la mesure.
Un système de management incapable de quantifier la performance réelle de ses
systèmes d'IA présente un écart entre ses contrôles documentés et sa réalité
opérationnelle.

Voir notre note sur [ISO/IEC 42001](/regulations/iso-42001/).

## Le règlement européen sur l'IA : une portée extraterritoriale jusqu'en Suisse

La Suisse n'est pas membre de l'UE, mais le règlement européen sur l'IA peut
s'appliquer aux organisations suisses dont les systèmes d'IA sont mis sur le
marché de l'UE ou dont les résultats sont utilisés dans l'UE. Pour les systèmes
à haut risque, le règlement fixe des exigences en matière de gestion des
risques, de gouvernance des données, de documentation technique, d'exactitude,
de robustesse et de surveillance après commercialisation. Les exportateurs
suisses, et les filiales suisses de groupes de l'UE, peuvent se trouver dans le
champ d'application indépendamment du droit national.

Les notions d'« exactitude » et de « robustesse » du règlement constituent, une
fois encore, une exigence de preuves quantifiées dans la durée — et non d'un
certificat ponctuel.

## Le fil conducteur : de l'obligation à la preuve

À travers la surveillance de la FINMA, l'ISO/IEC 42001 et le règlement européen
sur l'IA, l'exigence pratique converge :

- **Caractériser le comportement, ne pas l'affirmer.** Un système non
  déterministe a besoin d'une description de sa distribution de résultats, et
  non d'un seul test réussi.
- **Fixer des seuils explicites.** Quel taux de réussite, à quel niveau de
  confiance, est acceptable pour ce cas d'usage ?
- **Surveiller la dérive.** La preuve d'hier ne certifie pas le modèle
  d'aujourd'hui.
- **Rendre auditable.** Des preuves qu'un régulateur ou un auditeur peut
  inspecter valent mieux que des assurances qu'il doit accepter sur parole.

Ce ne sont pas, en premier lieu, des questions juridiques. Ce sont des questions
de mesure — et elles sont solubles.

## Comment agir

La discipline qui répond à ces questions est le **test probabiliste** : traiter
chaque exécution d'un système d'IA comme un essai, observer le taux de réussite
sur de nombreuses exécutions, et appliquer l'inférence statistique pour décider
si le taux sous-jacent réel atteint un seuil défini à un niveau de confiance
indiqué. Cela transforme « le modèle semble convenable » en une affirmation
quantifiée, reproductible et auditable — précisément la forme de preuve vers
laquelle tendent les régimes ci-dessus.

Les outils open source pour cela se trouvent sur notre site technique frère,
[mavai.org](https://mavai.org/) — notamment
[punit](https://mavai.org/projects/punit/) pour Java et
[feotest](https://mavai.org/projects/feotest/) pour Rust, validés par rapport à
un oracle statistique commun. La réglementation crée l'obligation ; le test
probabiliste est un moyen pratique de produire la preuve.

Pour suivre l'évolution de ces exigences, consultez notre fil
[Actualités réglementaires](/regulations/) et nos
[Perspectives](/posts/) plus détaillées.

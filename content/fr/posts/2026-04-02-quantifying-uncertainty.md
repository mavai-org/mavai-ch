---
title: "Quantifier le risque : facile. Quantifier la probabilité : difficile."
date: 2026-04-02
description: "La plupart des organisations déployant l'IA ne peuvent pas répondre à une question fondamentale : à quelle fréquence ce système échoue-t-il ? Les tests probabilistes apportent la réponse — et les régulateurs commencent à l'exiger."
summary: "La matrice de risque de la NASA a deux axes. La conséquence est maîtrisable. La probabilité — en particulier pour les systèmes d'IA stochastiques — ne l'est pas. Les régulateurs exigent désormais des preuves quantitatives, et les tests probabilistes sont le moyen de les produire."
image: /images/swiss-risk-matrix-fr.svg
author: "Mike Mannion"
---

<figure>
<img src="/images/swiss-risk-matrix-fr.svg" alt="Matrice de risque NASA — conséquence sur l'axe horizontal, probabilité sur l'axe vertical">
<figcaption>La matrice de risque de la NASA&nbsp;: plus pertinente que jamais</figcaption>
</figure>

La matrice de risque de la NASA place la conséquence sur un axe et la probabilité sur l'autre. On la retrouve aussi bien dans l'ingénierie spatiale que dans la réglementation financière. Mais les deux axes ne sont pas également difficiles à évaluer.

La conséquence — coût monétaire, effort de remédiation, sanctions réglementaires, atteinte à la réputation — demande du travail, mais les méthodes sont bien établies. L'assurance, la science actuarielle et les métriques de santé ajustées par la qualité quantifient l'impact depuis des siècles.

La probabilité est une autre affaire. Pour certains risques, les données historiques fournissent une base empirique. Pour les systèmes nouveaux — conditions sans précédent, comportements émergents, modèles d'IA qui donnent des réponses fausses avec assurance — il n'existe souvent aucun historique. Ces questions ne sont pas posées, ou reçoivent des réponses fondées sur l'intuition déguisée en analyse.

## Les LLM ont rendu la question incontournable

Les grands modèles de langage sont stochastiques par conception. Un seul test ne dit presque rien. La question «&nbsp;est-ce que ça marche&nbsp;?&nbsp;» doit être remplacée par «&nbsp;à quelle fréquence est-ce que ça marche&nbsp;?&nbsp;» — et la plupart des organisations déployant des systèmes basés sur les LLM n'ont pas de réponse rigoureuse.

Ce n'est pas une préoccupation marginale. Les LLM sont déployés dans le service client, le triage médical, la recherche juridique et l'analyse financière — des domaines où la confiance dans le comportement du système est primordiale. Pourtant, les outils de test disponibles pour la plupart des équipes produisent des verdicts binaires alors qu'une distribution de probabilité est nécessaire.

## Les régulateurs commencent à s'en apercevoir

La FINMA a publié la Communication 08/2024 [1] en décembre 2024, définissant ses attentes en matière de gouvernance de l'IA dans les établissements surveillés. La FINMA attend notamment des établissements qu'ils utilisent des *indicateurs de performance* — des mesures quantitatives de la capacité d'un système à atteindre ses objectifs. C'est un changement significatif par rapport aux tests pass/fail. Cela exige de la mesure, pas simplement de la vérification.

Les obligations du règlement européen sur l'IA pour les systèmes d'IA à haut risque — y compris ceux de la finance, de la santé et de l'administration publique — s'appliquent à partir d'août 2026 [2], et les organisations suisses servant des clients européens doivent s'y conformer indépendamment du statut de non-membre de la Suisse. Les exigences comprennent des niveaux de précision déclarés, un suivi continu des performances et des tests de gestion des risques (articles 9, 15 et 72 du règlement 2024/1689). La norme ISO 42001 [3] exige des organisations qu'elles définissent et suivent des métriques de performance.

La direction réglementaire est claire&nbsp;: les organisations doivent démontrer, preuves à l'appui, que leurs systèmes d'IA fonctionnent dans des limites acceptables. «&nbsp;Nous pensons que ça marche&nbsp;» ne satisfera pas les auditeurs.

## La réponse est statistique

Si la question est «&nbsp;à quelle fréquence est-ce que ça marche&nbsp;?&nbsp;», la méthode doit être statistique. Les tests probabilistes exécutent un test de nombreuses fois, mesurent le taux de réussite et appliquent l'inférence statistique pour déterminer si le taux observé atteint un seuil défini à un niveau de confiance donné. Cela transforme la conformité d'un jugement subjectif en une affirmation mesurable et vérifiable.

Le framework [punit](https://github.com/mavai-org/punit) implémente cette approche pour les applications Java. Pour les détails techniques et les guides de démarrage, visitez [mavai.org](https://mavai.org).

S'il y a une certitude, c'est qu'il est temps de prendre l'incertitude au sérieux.

---

*Pour le contexte réglementaire, voir [Pourquoi les tests probabilistes sont importants pour la réglementation suisse](/fr/posts/2026-03-21-why-probabilistic-testing/).*

## Sources

[1] Communication FINMA sur la surveillance 08/2024 — Gouvernance et gestion des risques liés à l'utilisation de l'intelligence artificielle, 18 décembre 2024. https://www.finma.ch/fr/news/2024/12/20241218-mm-finma-am-08-24/

[2] Règlement (UE) 2024/1689 — Législation sur l'intelligence artificielle. Les obligations pour les systèmes d'IA à haut risque (articles 9, 15, 72) s'appliquent à partir du 2 août 2026. https://eur-lex.europa.eu/legal-content/FR/TXT/?uri=CELEX:32024R1689

[3] ISO/IEC 42001:2023 — Technologies de l'information — Intelligence artificielle — Système de management. https://www.iso.org/fr/standard/81230.html

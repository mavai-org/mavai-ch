---
title: "Conformité et la méthode Mavai"
description: "Comment les obligations suivies sur mavai.ch sont satisfaites par la méthode documentée sur mavai.org : Baseline · Monitor · Comply."
keywords: ["conformité IA Suisse", "FINMA IA preuves", "ISO 42001 preuves", "AI Act surveillance après commercialisation", "baseline IA", "tests probabilistes conformité"]
summary: "mavai.ch suit ce que la réglementation exige. mavai.org documente la réponse de Mavai. Cette page relie les deux : d'un côté l'obligation, de l'autre l'étape de la méthode qui en produit la preuve."
---

mavai.ch suit le paysage réglementaire de l'IA en Suisse : ce que la FINMA
attend, ce que certifie ISO/IEC 42001 et jusqu'où l'AI Act européen s'étend
au-delà de la frontière. [mavai.org](https://mavai.org/) documente la méthode
que Mavai™ emploie pour répondre à ces attentes. Cette page relie les deux.

## L'obligation

Chaque régime présenté sur ce site pose la même question en d'autres termes :
**pouvez-vous montrer que votre système d'IA fonctionne comme vous l'affirmez,
non pas une fois, mais aussi longtemps qu'il est en service ?**

- La **[FINMA](/fr/regulations/finma/)** attend un inventaire des applications
  d'IA, une gouvernance de leurs risques et un contrôle démontrable du
  comportement des modèles.
- **[ISO/IEC 42001](/fr/regulations/iso-42001/)** certifie un système de
  management de l'IA intégrant l'évaluation des performances et l'amélioration
  continue.
- L'**[AI Act européen](/fr/ai-regulation-switzerland/)** exige, pour les
  systèmes à haut risque, une gestion des risques sur tout le cycle de vie
  (art. 9), une surveillance après commercialisation (art. 72) et une
  documentation technique des tests et des résultats (annexe IV), et il
  s'applique aux fournisseurs suisses dont les systèmes sont utilisés dans l'UE.

Aucune de ces obligations ne se satisfait d'un audit unique avant la mise en
service. Chacune exige des preuves produites en continu.

## La méthode : Baseline · Monitor · Comply

**Baseline.** Tout appel isolé à un service d'IA peut être jugé correct ou
incorrect. Ce que personne ne sait à l'avance, c'est à quelle fréquence le
service a raison. Une baseline mesure ce taux à un niveau de confiance déclaré
et l'enregistre avec le modèle, les prompts et les circonstances de la mesure.

**Monitor.** Le service en production est tenu à sa baseline aussi longtemps
qu'il fonctionne : à chaque version, à chaque changement de modèle ou de
prompt, et selon un calendrier entre-temps. Toute dérive au-delà des limites
convenues est signalée, au niveau de confiance de la baseline, avant
d'atteindre la production, et a fortiori un superviseur.

**Comply.** La baseline est l'enregistrement, la surveillance en est la preuve,
et la méthode est documentée publiquement : le
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) expose
les statistiques et les [frameworks open source](https://mavai.org/projects/)
les mettent en œuvre ligne par ligne. Ensemble, ils constituent la
documentation technique qu'exige l'AI Act européen, et ce que tout
superviseur, auditeur ou standard peut lire.

Chaque mesure et chaque verdict constituent un enregistrement structuré qui
indique ce qui a été mesuré, combien de fois, contre quelle barre et à quel
niveau de confiance. Cet enregistrement est la preuve.

## De l'obligation à la preuve

| Le régime exige | La méthode fournit |
|---|---|
| FINMA : inventaire et contrôle des risques liés à l'IA | Une baseline par service, qui constitue à la fois l'inventaire et le contrôle |
| ISO/IEC 42001 : évaluation des performances, amélioration continue | Baselines et enregistrements de surveillance comme preuves du système de management |
| AI Act art. 9 : gestion des risques sur le cycle de vie avec des métriques définies | Baselines avec seuils et confiance déclarés ; tests à chaque changement |
| AI Act art. 72 : surveillance après commercialisation | Surveillance planifiée par rapport à la baseline, avec signalement des dérives |
| AI Act art. 11 et annexe IV : documentation technique de la méthode, de ses tests et de ses résultats | Le Statistical Companion et les frameworks open source documentent la méthode ; les enregistrements conservés contiennent les tests et les résultats |

Les noms diffèrent. La preuve est la même preuve, et c'est celle qu'une équipe
voudrait de toute façon. La réglementation est la raison pour laquelle davantage
d'équipes la demandent aujourd'hui ; la méthode est la même pour toute équipe
dont le service se comporte comme un taux plutôt que comme une valeur unique.

## Prochaines étapes

La méthode complète, en termes d'affaires, se trouve sur
[How We Help](https://mavai.org/how-we-help/). Les outils open source qui la
mettent en œuvre sont [punit](https://mavai.org/projects/punit/) pour Java,
[feotest](https://mavai.org/projects/feotest/) pour Rust et
[baseltest](https://mavai.org/projects/baseltest/) pour Python.

Si vous déployez un service d'IA sous l'un des régimes ci-dessus, un premier
entretien permet d'établir où en est votre projet aujourd'hui et comment ses
tests doivent évoluer. [Contactez-nous](/fr/contact/) ou
[parlez directement à Mavai](https://mavai.org/contact/).

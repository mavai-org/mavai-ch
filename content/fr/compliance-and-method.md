---
title: "Conformité et la méthode Mavai"
description: "Comment les exigences de surveillance des systèmes d'IA sont satisfaites par la méthode Mavai™ : Baseline · Monitor · Comply."
keywords: ["conformité IA Suisse", "FINMA IA preuves", "ISO 42001 preuves", "AI Act surveillance après commercialisation", "baseline IA", "tests probabilistes conformité"]
summary: "mavai.ch suit ce que la réglementation exige. mavai.org documente la réponse de Mavai. Cette page relie les deux : d'un côté l'obligation, de l'autre l'étape de la méthode qui en produit la preuve."
---

mavai.ch suit le paysage réglementaire en matière de gouvernance de l'IA et de son impact sur la Suisse : ce que la FINMA
attend, ce que certifie ISO/IEC 42001 et jusqu'où l'AI Act européen s'étend
au-delà de la frontière. [mavai.org](https://mavai.org/) documente la méthode
que Mavai™ emploie pour répondre à ces attentes. Cette page relie les deux.

## Les Obligations

Chaque cadre de **surveillance ou de qualité** pour l'IA que nous mentionnons ici pose la même question en d'autres termes :
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

Bien que les exigences diffèrent dans leur application, leur portée et leur étendue, chacune exige une preuve continue de la performance du système.

## La méthode Mavai™ : Baseline · Monitor · Comply

Mavai utilise une baseline statistique pour chaque service d'IA que vous déployez, une surveillance continue par rapport à celui-ci, et une méthode documentée publiquement — avec les outils et le savoir-faire pour l'intégrer dans votre pipeline de livraison.

**Baseline.** Tout appel isolé à un service d'IA peut être jugé correct ou
incorrect. Ce que personne ne sait à l'avance, c'est à quelle fréquence le
service a raison. Une baseline mesure ce taux sur un nombre déclaré d'appels et l'enregistre avec le modèle, les prompts et les circonstances de la mesure.

**Monitor.** Le service en production est tenu à sa baseline aussi longtemps
qu'il fonctionne : à chaque version, à chaque changement de modèle ou de
prompt, et selon un calendrier entre-temps. Chaque contrôle prélève un nouvel échantillon du service en production et compare son taux de réussite à la borne que la baseline implique pour un échantillon de cette taille. Un taux inférieur à la borne est signalé comme une dégradation, avec un niveau de confiance déclaré, typiquement 95 %, avant d'atteindre la production, et a fortiori un superviseur.

**Comply.** La baseline est l'enregistrement, la surveillance en est la preuve,
et la méthode est documentée publiquement : le
[Statistical Companion](https://r.mavai.org/statistical-companion.pdf) expose
les statistiques et les [frameworks open source](https://mavai.org/projects/)
les mettent en œuvre ligne par ligne. Ensemble, ils constituent la
documentation technique qu'exigent l'AI Act européen et autres directives réglementaires concernant la gestion des systèmes d'IA, et ce que tout
superviseur, auditeur ou standard peut lire.

Chaque mesure et chaque verdict constituent un enregistrement structuré qui indique ce qui a été mesuré et combien de fois, et, pour chaque verdict, la borne retenue et le niveau de confiance de l'affirmation. Cet enregistrement est la preuve dont vous avez besoin.

## Ce que la méthode fournit

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
      <th>Le cadre exige</th>
      <th>La preuve dont vous avez besoin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>FINMA :</strong> inventaire et contrôle des risques liés à l'IA</td>
      <td>Une baseline par service, qui constitue à la fois l'inventaire et le contrôle</td>
    </tr>
    <tr>
      <td><strong>ISO/IEC 42001 :</strong> évaluation des performances, amélioration continue</td>
      <td>Baselines et enregistrements de surveillance comme preuves du système de management</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 9 :</strong> gestion des risques sur le cycle de vie avec des métriques définies</td>
      <td>Baselines sur un nombre déclaré d'appels ; tests contre celles-ci à un niveau de confiance déclaré, à chaque changement</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 72 :</strong> surveillance après commercialisation</td>
      <td>Surveillance planifiée par rapport à la baseline, avec signalement des dérives</td>
    </tr>
    <tr>
      <td><strong>AI Act art. 11 et annexe IV :</strong> documentation technique de la méthode, de ses tests et de ses résultats</td>
      <td>Le Statistical Companion et les frameworks open source documentent la méthode ; les enregistrements conservés contiennent les tests et les résultats</td>
    </tr>
  </tbody>
</table>

La méthode Mavai peut fournir la preuve pour les exigences ci-dessus et plus encore.

## Ce que les équipes doivent savoir

### En résumé

Simplement dit, Mavai.ch et Mavai.org [peuvent vous aider](https://mavai.org/how-we-help/) à gérer les nouvelles exigences et vous fournir les outils pour produire la preuve dont vous avez besoin pour satisfaire à ces exigences. De plus, les outils open source de Mavai™ qui les mettent en œuvre sont [punit](https://mavai.org/projects/punit/) pour Java, [feotest](https://mavai.org/projects/feotest/) pour Rust et [baseltest](https://mavai.org/projects/baseltest/) pour Python.

Si vous développez ou déployez déjà un service d'IA et souhaitez vous assurer que vous pouvez respecter les exigences ci-dessus, une première conversation couvre où en est votre projet aujourd'hui et comment ses tests doivent évoluer. [Contactez-nous](/fr/contact/) ou [parlez directement à Mavai](https://mavai.org/contact/).

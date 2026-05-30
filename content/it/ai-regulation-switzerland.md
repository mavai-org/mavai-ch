---
title: "Regolamentazione dell'IA in Svizzera: cosa cambia e come agire"
description: "Una panoramica pratica della regolamentazione dell'IA che riguarda la Svizzera — aspettative di vigilanza della FINMA, ISO/IEC 42001 e l'effetto di propagazione del regolamento europeo sull'IA — e come le organizzazioni svizzere possono rispondere con prove."
keywords: ["regolamentazione IA Svizzera", "requisiti FINMA IA", "ISO 42001 Svizzera", "regolamento IA UE Svizzera", "conformità IA Svizzera", "governance IA Svizzera", "diritto svizzero dell'IA", "gestione del rischio IA"]
summary: "La Svizzera non ha un'unica legge sull'IA, ma la vigilanza del settore finanziario, gli standard internazionali di gestione e la portata extraterritoriale del regolamento europeo sull'IA definiscono già ciò che le organizzazioni svizzere devono fare. Il filo conduttore è la prova — e produrla è un problema di misurazione."
---

La Svizzera non dispone di un'unica legge dedicata all'IA. Questa assenza può
essere fraintesa come assenza di obblighi. Non lo è. I sistemi di IA impiegati
da banche, assicuratori, ospedali, aziende farmaceutiche ed enti pubblici
svizzeri rientrano già in una stratificazione di aspettative di vigilanza,
standard internazionali e norme extraterritoriali. Comprendere come questi
elementi si combinano — e cosa hanno in comune — è il primo passo verso una
posizione di conformità difendibile.

Il filo conduttore è la **prova**. Ciascuno dei regimi seguenti pone, nel
proprio linguaggio, la stessa domanda di fondo: *potete dimostrare che il vostro
sistema di IA funziona entro limiti accettabili, in modo costante, nel tempo?*
Per i sistemi non deterministici — tutto ciò che si basa su grandi modelli
linguistici o sull'inferenza di apprendimento automatico — non è una domanda
sì/no. È un problema di misurazione.

## FINMA: vigilanza senza una «legge sull'IA»

L'Autorità federale di vigilanza sui mercati finanziari (FINMA) non ha emanato
una regolamentazione specifica per l'IA, e potrebbe non averne bisogno. Il suo
quadro esistente per il rischio di modello, il rischio operativo e la governance
si applica direttamente ai sistemi di IA utilizzati nel settore bancario e
assicurativo. Gli istituti vigilati sono tenuti a convalidare i modelli prima e
durante l'impiego, a gestire il rischio operativo intorno ai sistemi critici, a
mantenere una chiara responsabilità e a poter spiegare e documentare come un
sistema giunge ai propri risultati.

Per un modello deterministico, la convalida può basarsi su risultati
riproducibili. Per un sistema di IA stocastico, «funzionava quando l'abbiamo
verificato» non è una convalida — l'esecuzione successiva può differire. Ciò che
le aspettative della FINMA implicano, in pratica, è la necessità di
caratterizzare il comportamento in modo statistico: non un singolo controllo
superato, ma un'affermazione quantificata su quanto spesso il sistema rispetta
il proprio contratto.

Si veda la nostra nota dettagliata su [FINMA e IA nel settore bancario](/regulations/finma/).

## ISO/IEC 42001: un sistema di gestione per l'IA

ISO/IEC 42001 è lo standard internazionale per i sistemi di gestione dell'IA —
la controparte per l'IA dell'ISO 27001 per la sicurezza delle informazioni. È
volontario, ma le organizzazioni svizzere lo adottano sempre più perché la
certificazione offre un modo riconosciuto e verificabile di dimostrare che l'IA
è governata in modo responsabile. Lo standard richiede valutazione dei rischi,
controlli definiti, monitoraggio e miglioramento continuo lungo l'intero ciclo
di vita dell'IA.

Monitoraggio e miglioramento continuo presuppongono, anche qui, la misurazione.
Un sistema di gestione che non è in grado di quantificare come i propri sistemi
di IA si comportano effettivamente presenta uno scarto tra i controlli
documentati e la realtà operativa.

Si veda la nostra nota su [ISO/IEC 42001](/regulations/iso-42001/).

## Il regolamento europeo sull'IA: portata extraterritoriale fino in Svizzera

La Svizzera non è membro dell'UE, ma il regolamento europeo sull'IA può
applicarsi alle organizzazioni svizzere i cui sistemi di IA sono immessi sul
mercato dell'UE o i cui risultati sono utilizzati nell'UE. Per i sistemi ad alto
rischio, il regolamento stabilisce requisiti in materia di gestione dei rischi,
governance dei dati, documentazione tecnica, accuratezza, robustezza e
monitoraggio successivo all'immissione sul mercato. Gli esportatori svizzeri, e
le filiali svizzere di gruppi dell'UE, possono rientrare nell'ambito di
applicazione indipendentemente dal diritto nazionale.

I termini «accuratezza» e «robustezza» del regolamento sono, ancora una volta,
una richiesta di prove quantificate nel tempo — non di un certificato una
tantum.

## Il filo conduttore: dall'obbligo alla prova

Attraverso la vigilanza della FINMA, l'ISO/IEC 42001 e il regolamento europeo
sull'IA, la richiesta pratica converge:

- **Caratterizzare il comportamento, non affermarlo.** Un sistema non
  deterministico ha bisogno di una descrizione della propria distribuzione di
  esiti, non di un singolo test superato.
- **Fissare soglie esplicite.** Quale tasso di successo, a quale livello di
  confidenza, è accettabile per questo caso d'uso?
- **Monitorare la deriva.** La prova di ieri non certifica il modello di oggi.
- **Renderlo verificabile.** Prove che un regolatore o un revisore può
  ispezionare valgono più di assicurazioni che deve accettare sulla fiducia.

Queste non sono, in prima istanza, questioni giuridiche. Sono questioni di
misurazione — e sono risolvibili.

## Come agire

La disciplina che risponde a queste domande è il **test probabilistico**:
trattare ogni esecuzione di un sistema di IA come una prova, osservare il tasso
di successo su molte esecuzioni e applicare l'inferenza statistica per decidere
se il tasso reale sottostante raggiunge una soglia definita a un livello di
confidenza indicato. Ciò trasforma «il modello sembra a posto» in
un'affermazione quantificata, ripetibile e verificabile — esattamente la forma
di prova verso cui tendono i regimi sopra descritti.

Gli strumenti open source per farlo si trovano sul nostro sito tecnico gemello,
[mavai.org](https://mavai.org/) — tra cui
[punit](https://mavai.org/projects/punit/) per Java e
[feotest](https://mavai.org/projects/feotest/) per Rust, convalidati rispetto a
un oracolo statistico condiviso. La regolamentazione crea l'obbligo; il test
probabilistico è un modo pratico per produrre la prova.

Per seguire l'evoluzione di questi requisiti, consultate il nostro feed
[Notizie normative](/regulations/) e gli
[Approfondimenti](/posts/) più estesi.

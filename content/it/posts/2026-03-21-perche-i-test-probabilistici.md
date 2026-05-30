---
title: "Perché i test probabilistici sono importanti per la regolamentazione svizzera"
date: 2026-03-21
description: "Un'introduzione al motivo per cui i test tradizionali sono insufficienti per la conformità dell'IA."
summary: "Le autorità di regolamentazione svizzere chiedono evidenze che i sistemi di IA funzionino in modo affidabile. I test tradizionali non possono fornirle. Ecco perché gli approcci statistici sono la risposta."
---

Quando una banca implementa un modello di IA per valutare il rischio di credito, o un'autorità cantonale utilizza il machine learning per il triage delle domande, segue una domanda naturale: come sapete che funziona?

Per il software tradizionale, la risposta è semplice. Si scrivono dei test. Ogni test verifica che un determinato input produca un determinato output. Se tutti i test vengono superati, si ha fiducia nel sistema.

L'IA non funziona così.

## Il problema del non-determinismo

I sistemi di IA — in particolare quelli basati su modelli linguistici di grandi dimensioni o sull'apprendimento statistico — sono intrinsecamente non deterministici. Ponete la stessa domanda due volte e potreste ottenere risposte diverse. Non si tratta di un difetto; è una caratteristica fondamentale del funzionamento di questi sistemi.

Questo crea un problema per la conformità. La FINMA si aspetta la validazione dei modelli. ISO 42001 richiede la misurazione delle prestazioni. I revisori vogliono evidenze. Ma evidenze di *cosa*, esattamente, quando l'output del sistema varia per progettazione?

## Da superato/non superato ai tassi di successo

La risposta sta nel passaggio dai test binari (ha funzionato?) ai test statistici (con quale frequenza funziona, ed è sufficiente?).

Invece di asserire che una funzione restituisce un valore specifico, la si esegue molte volte e si chiede se il tasso di successo osservato soddisfa una soglia definita a un dato livello di confidenza. Questa è la base dei test probabilistici.

Per un dirigente d'azienda, l'analogia è il controllo qualità nella produzione industriale. Non si testa ogni singolo pezzo — si testa un campione e si esprime un giudizio statistico sull'intero lotto. Lo stesso principio si applica all'IA.

## Cosa significa nella pratica

I test probabilistici producono evidenze strutturate e quantitative: tassi di successo, intervalli di confidenza, distribuzioni di latenza, dati di tendenza. Questo è esattamente il tipo di evidenze richiesto dai quadri normativi.

Trasformano la conformità dell'IA da un giudizio soggettivo («pensiamo che funzioni») in un'affermazione misurabile e verificabile («abbiamo dimostrato, con il 95% di confidenza, che il tasso di successo di questo sistema supera il 99,5%»).

Per i dettagli tecnici sull'implementazione, visitate [mavai.org](https://mavai.org).

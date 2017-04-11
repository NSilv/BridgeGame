# Funzionamento #

## server ##

la partita verra' iniziata dal primo giocatore che si connette. il server sara' un multiserver, con un thread per ogni giocatore.
Il server dovra' tenere le seguenti informazioni:

  * Grafo(Mappa) 
      * ogni nodo del grafo sara' "Taggato" in qualche modo
  * _HashMap_/ _ConcurrentHashMap_ / _coppia di array_ (Per le informazioni dei giocatori. Key=Player, Value=Info)
	
All'inizio di ogni turno il server inviera' un segnale di `START{X}` ai client, che avranno `X` secondi per rispondere(in caso non rispondano, mossa nulla). Il tempo sara' salvato sul server con un timer, al termine del quale verra' inviato un messaggio `STOP`. Dopodiche' restera' in ascolto per le mosse dei client. Una volta arrivate, controllera' che siano valide( comparando con HashMap e Grafo) e ritorna un messaggio di `OK` in caso cio' sia vero, un messaggio di `RETRY` in caso contrario. Quando tutti i client hanno inviato una mossa, il server calcola i cambiamenti alla HashMap. Se qualcuno e' arrivato al traguardo, allora la partita finisce, i client vengono tutti disconnessi e il gioco ricomincia da capo.

## client ##

Il client si connette a un server(di cui fornisce ip e porta). Se non ci sono partite in corso, allora imposta la difficolta' e invia al server
il segnale di generare la mappa e iniziare la partita. Se invece una partita' e' gia' iniziata, mi unisco. Appena mi unisco, ottengo dal server i secondi mancanti Faccio la mossa, invio. Se il server
risponde `OK`, allora aspetto per la fine del turno. Se invece ottengo `RETRY`, riprovo. In caso il client ottenga `STOP`, inviera' una mossa nulla e aspettera' il prossimo turno(segnalato da `START{X}`).

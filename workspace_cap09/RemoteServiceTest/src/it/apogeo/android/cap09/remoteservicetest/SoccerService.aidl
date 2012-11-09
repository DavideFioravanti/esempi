package it.apogeo.android.cap09.remoteservicetest;

// Imporiamo il riferimento al tipo Score
import it.apogeo.android.cap09.remoteservicetest.Score;

// Definiamo le operazioni del nostro SoccerService
interface SoccerService{

	// Permette di ottenere il punteggio di una partita tra due team
	Score getScore(String localTeam, String externalTeam);
	
	// Ritorna l'elenco di tutti i punteggi delle partite
	int getAllScores(out List<Score> scores);
	
	// Permette di inserire uno Score
	void registerScore(in Score score);


}
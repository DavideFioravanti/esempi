/**
 * 
 */
package it.apogeo.android.cap09.remoteservicetest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author MASSIMO
 * 
 */
public class SoccerRemoteService extends Service {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "SoccerRemoteService";

	/*
	 * Memorizza l'elenco degli score
	 */
	private final List<Score> listScores = new ArrayList<Score>();

	/*
	 * Implementazione delle operazioni del servizio
	 */
	private final SoccerService.Stub mBinder = new SoccerService.Stub() {

		@Override
		public int getAllScores(List<Score> scores) throws RemoteException {
			Log.i(LOG_TAG, "getAllScores");
			scores.clear();
			scores.addAll(listScores);
			return listScores.size();
		}

		@Override
		public Score getScore(String localTeam, String externalTeam)
				throws RemoteException {
			Log.i(LOG_TAG, "getScore");
			// Creiamo uno Score di esempio
			Score score = new Score();
			score.date = new Date();
			score.localTeam = "Spal";
			score.localScore = 3;
			score.externalTeam = "Inter";
			score.externalScore = 0;
			return score;
		}

		@Override
		public void registerScore(Score score) throws RemoteException {
			Log.i(LOG_TAG, "registerScore");
			// Lo aggiungiamo all'elenco
			listScores.add(score);
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(LOG_TAG, "onBind");
		// Ritorniamo il riferimento alla implementazione creata
		// in precedenza
		return mBinder;
	}

	@Override
	public void onRebind(Intent intent) {
		Log.i(LOG_TAG, "onRebind");
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(LOG_TAG, "onUnbind. Returning true");
		return true;
	}
	
	

}

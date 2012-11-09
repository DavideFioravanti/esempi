package it.apogeo.android.cap09.remoteserviceclient;

import it.apogeo.android.cap09.remoteservicetest.Score;
import it.apogeo.android.cap09.remoteservicetest.SoccerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class RemoteServiceClientActivity extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "RemoteServiceClientActivity";
	/*
	 * Intent associato al servizio
	 */
	private final Intent soccerIntent = new Intent(SoccerService.class
			.getName());
	/*
	 * Riferimento all'implementazione del servizio remoto da invocare
	 */
	private SoccerService soccerService;

	private final ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// Questo metodo viene invocato in corrispondenza della creazione
			// della connessione verso il servizio remoto
			soccerService = SoccerService.Stub.asInterface(service);
			Log.i(LOG_TAG, "onServiceConnected");
			Toast.makeText(RemoteServiceClientActivity.this, "Service Connection Created", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// In questo caso non facciamo nulla se non visualizzare un log
			Log.i(LOG_TAG, "onServiceDisconnected");
		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void bindRemoteService(View button) {
		if (soccerService == null) {
			bindService(soccerIntent, serviceConnection,
					Context.BIND_AUTO_CREATE);
			Log.i(LOG_TAG, "bindRemoteService");
		}
	}

	public void unbindRemoteService(View button) {
		if(soccerService!=null){
			unbindService(serviceConnection);
			Log.i(LOG_TAG, "unbindRemoteService");		
			soccerService = null;		
			Toast.makeText(this, "Service Unbinded", Toast.LENGTH_SHORT).show();
		}
	}

	public void callAllScore(View button) {
		if(soccerService!=null){
			Log.i(LOG_TAG, "callAllScore");		
			ArrayList<Score> scores = new ArrayList<Score>();
			try {
				int size = soccerService.getAllScores(scores);
				for(int i=0;i<size;i++){
					Score sc = scores.get(i);
					Log.i(LOG_TAG, "Score ->"+sc.localTeam+"-"+sc.externalTeam+" - "+sc.localScore+":"+sc.externalScore);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void callGetScore(View button) {
		if(soccerService!=null){
			try {
				Score score = soccerService.getScore("Team 1", "Team 2");
				Toast.makeText(this, score.localTeam+" vs "+ score.externalTeam+" : "+score.localScore+":"+score.externalScore, Toast.LENGTH_SHORT).show();				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.i(LOG_TAG, "callGetScore");			
		}
	}

	public void callRegScore(View button) {
		if(soccerService!=null){
			Random random = new Random();
			Log.i(LOG_TAG, "callRegScore");
			Score score = new Score();
			score.localTeam = "Team 1";
			score.localScore = Math.abs(random.nextInt()%5);
			score.externalTeam = "Team 2";
			score.externalScore= Math.abs(random.nextInt()%5);
			score.date = new Date();
			try {
				soccerService.registerScore(score);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Toast.makeText(this, "Team 1 vs Team 2 : "+score.localScore+":"+score.externalScore, Toast.LENGTH_SHORT).show();
		}
	}

}
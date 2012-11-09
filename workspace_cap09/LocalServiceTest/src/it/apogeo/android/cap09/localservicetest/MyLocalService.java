/**
 * 
 */
package it.apogeo.android.cap09.localservicetest;

import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Esempio di un Service locale che permette di inviare delle notifiche
 * ad intervalli casuali di tempo
 *  
 * @author MASSIMO
 *
 */
public class MyLocalService extends Service {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "MyLocalService";
	
	/*
	 * Numero massimo di notifiche da inviare dopo il quale il servizio muore
	 */
	private final static int MAX_NOTIFICATION_NUMBER = 10;
	
	/*
	 * Identifciatori delle notifiche
	 */
	private final static int SIMPLE_NOTIFICATION_ID = 1;
	/*
	 * Riferimento al NotificationManager
	 */
	private NotificationManager notificationManager;	
	/*
	 * Riferimento al Thread in background
	 */
	private BackgroundThread backgroundThread;
	
	/*
	 * Notifica da inviare piu' volte
	 */
	private Notification notification;
	/*
	 * Intent della Notification
	 */
	private PendingIntent pIntent;
	
	/*
	 * Numero di notifiche inviate
	 */
	private int notificationNumber;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// Facciamo partire il BackgroundThread
		backgroundThread = new BackgroundThread();
		backgroundThread.start();
		// Otteniamo il riferimento al NotificationManager
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Log.i(LOG_TAG, "Service Created");
		// Creiamo la Notification
		notification = new Notification(R.drawable.icon,
				"Simple Notification", System.currentTimeMillis());		
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Simple Notification");
		pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);	
	}
	
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Solo per debugging
		Log.i(LOG_TAG, "Service Started");
		// Inizializziamo il numero di notifiche inviate
		notificationNumber = 0;
		return super.onStartCommand(intent, flags, startId);
	}



	@Override
	public void onDestroy() {
		backgroundThread.running = false;
		super.onDestroy();
		Log.i(LOG_TAG, "Service Destroyed");
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// Ritorniamo null in quanto non si vuole permettere
		// l'accesso al servizio da una applicazione diversa
		return null;
	}
	
	public void sendNotification() {
		// Aggiorniamo il contatore
		notificationNumber++;
		notification.number=notificationNumber;
		notification.setLatestEventInfo(this, "Simple Notification",
				"Simple Notification Extended", pIntent);
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
	}	
	
	private final class BackgroundThread extends Thread {
		/*
		 * Parte fissa del delay
		 */
		private final static long MIN_DELAY = 2000L;		
		/*
		 * Ampiezza massima della parte casuale del delay
		 */
		private final static long MAX_RANDOM_DELAY = 10000L;

		public boolean running= true;

		public void run() {
			Log.i(LOG_TAG, "BackgroundThread Started");			
			Random random = new Random();
			while(running && notificationNumber<MAX_NOTIFICATION_NUMBER){
				long randomDelay = MIN_DELAY + Math.abs(random.nextInt() %MAX_RANDOM_DELAY);
				Log.i(LOG_TAG, "Delay is (ms) "+randomDelay);
				try{Thread.sleep(randomDelay);}catch(InterruptedException ie){}
				sendNotification();
			}
			// Al termine del metodo run terminiamo il servizio
			stopSelf();
		}

	}
	

}

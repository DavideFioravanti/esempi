/**
 * 
 */
package it.apogeo.android.cap12.appwidgettest;

import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * 
 * Specializzazione della classe AppWidgetProvider
 * 
 * @author MASSIMO
 * 
 */
public class TestAppProvider extends AppWidgetProvider {
	/*
	 * Tag del Log
	 */
	private final static String TAG_LOG = "TestAppProvider";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// Facciamo partire il servizio di aggiornamento
		Log.i(TAG_LOG, "Update");
		for(int i=0;i<appWidgetIds.length;i++){
			// Creiamo un intent aggiungendo gli Id degli AppWidget da gestire
			Intent updateIntent = new Intent(context, RandomValueService.class);
			// Aggiungiamo come informazione l'array di ids
			updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			// Lanciamo il servizio
			context.startService(updateIntent);			
		}

	}

	/**
	 * Servizio molto semplice che permette di creare delle RemoteViews quando
	 * invocato notificandole al particolare App Widget
	 * 
	 * @author MASSIMO
	 * 
	 */
	public static class RandomValueService extends Service {

		/*
		 * Generatore valori casuali
		 */
		private final Random random = new Random();

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// Ottengo gli identificatori delle istanze di Widget
			Bundle extras = intent.getExtras();
			int mAppWidgetId = 0;
			if (extras != null) {
				mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
						AppWidgetManager.INVALID_APPWIDGET_ID);
			}		
			// Creiamo un RemoteViews a partire dal layout specificato
			RemoteViews remoteView = new RemoteViews(getPackageName(),
					R.layout.test_appwidget_layout);
			int randomValue = Math.abs(random.nextInt() % 100);
			remoteView.setTextViewText(R.id.widgetOutput, "Value:"+ randomValue);	
			// Otteniamo il riferimento al Manager
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			// Aggiorniamo tutti i corrispondenti valori
			manager.updateAppWidget(mAppWidgetId, remoteView);
			return Service.START_STICKY;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.app.Service#onBind(android.content.Intent)
		 */
		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}

	}

}

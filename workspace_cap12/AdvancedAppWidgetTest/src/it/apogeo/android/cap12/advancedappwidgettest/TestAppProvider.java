/**
 * 
 */
package it.apogeo.android.cap12.advancedappwidgettest;

import java.util.Random;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
	
	

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// Dobbiamo cancellare l'evento di update associato e le relative
		// informazioni nelle preferences per ciascuna istanza cancellata
        for (int i=0;i<appWidgetIds.length;i++) {
            // Cancelliamo il PendingIntent dal servizio di ALERT 
            Intent timerIntent = new Intent();
            timerIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            timerIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            timerIntent.setData(Uri.withAppendedPath(Uri.parse("customappwidget://widget/id/"), String.valueOf(appWidgetIds[i])));
            PendingIntent pendingToDelete = PendingIntent.getBroadcast(context, 0, timerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarms.cancel(pendingToDelete);
            // Cancelliamo le relative preferences
            SharedPreferences config = context.getSharedPreferences(TestAppWidgetConfig.APP_WIDGET_PREFS, 0);
            SharedPreferences.Editor configEditor = config.edit();
            configEditor.remove(String.format(TestAppWidgetConfig.REFRESH_RATE_PATTERN, appWidgetIds[i]));
            configEditor.commit();
        }		
		super.onDeleted(context, appWidgetIds);
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

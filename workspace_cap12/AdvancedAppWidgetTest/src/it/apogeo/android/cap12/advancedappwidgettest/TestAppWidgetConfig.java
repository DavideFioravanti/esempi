/**
 * 
 */
package it.apogeo.android.cap12.advancedappwidgettest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Attività di configurazione
 * 
 * @author MASSIMO
 * 
 */
public class TestAppWidgetConfig extends Activity {
	/*
	 * Nome delle preferenze associate alle istanze del WIdget
	 */
	public final static String APP_WIDGET_PREFS = "APP_WIDGET_PREFS";
	/*
	 * Frequenza di aggiornamento di default
	 */
	private final static int DEFAULT_REFRESH_RATE = 60000; // 1 minuto
	/*
	 * Pattern relativo alla chiave da associare alla frequenza di aggiornamento
	 * dell'istanza di cui si conosce l'identificatore
	 */
	public final static String REFRESH_RATE_PATTERN = "RefreshRate:%d";
	/*
	 * Riferimento all'EditText
	 */
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TestAppWidgetConfig", "SIAMO QUI!");
		setContentView(R.layout.widget_config_layout);
		// Per impedire che venga aggiunto l'App Widget con il back dell'utente
		// impostiamo questo come valore di default
		setResult(RESULT_CANCELED);
		// Leggiamo comunque la frequenza attuale di aggiornamento e la
		// inseriamo nell'EditText
		// Leggiamo il valore inserito nell'EditText
		editText = (EditText) findViewById(R.id.updateRate);
		// Leggiamo dalle Preferences associate a questa applicazione
		final SharedPreferences prefs = getSharedPreferences(APP_WIDGET_PREFS,
				0);
		int instanceId = getWidgetId(getIntent());
		String instanceKey = String.format(REFRESH_RATE_PATTERN, instanceId);
		int refreshRateValue = prefs.getInt(instanceKey, DEFAULT_REFRESH_RATE);
		editText.setText(refreshRateValue + "");
	}

	/**
	 * Metodo invocato in corrispondenza dell'aggiornamento del tempo di refresh
	 * 
	 * @param button
	 *            Riferimento al Button
	 */
	public void updateRefresh(View button) {
		// Prendiamo il valore inserito nella EditText
		int updateRate = Integer.parseInt(editText.getText().toString());
		// Salviamo il corrispondente valore nelle Preferences
		final SharedPreferences prefs = getSharedPreferences(APP_WIDGET_PREFS,
				0);
		Editor editor = prefs.edit();
		int instanceId = getWidgetId(getIntent());
		String instanceKey = String.format(REFRESH_RATE_PATTERN, instanceId);
		editor.putInt(instanceKey, updateRate);
		editor.commit();
		// Prepariamo l'Intent per la notifica dell'aggiornamento la quale
		// avviene
		// attraverso il servizio di ALARM. Prepariamo l'Intent relativo
		// all'aggiornamento
		Intent updateIntent = new Intent();
		updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
				new int[] { instanceId });
		Uri updateUri = Uri.withAppendedPath(Uri
				.parse("customappwidget://widget/id/"), String
				.valueOf(instanceId));
		updateIntent.setData(updateUri);
		// Creiamo il PendingIntent
		PendingIntent newPending = PendingIntent.getBroadcast(
				getApplicationContext(), 0, updateIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		// Registriamo l'intent di aggiornamento al servizio di Alarm
		AlarmManager alarms = (AlarmManager) getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE);
		alarms.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock
				.elapsedRealtime(), updateRate, newPending);
		// Ritorniamo con una operazione di successo dopo aver individuato
		// l'identificatore del particolare AppWidget da aggiornare
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,instanceId);
		setResult(RESULT_OK, resultValue);
		finish();
	}

	/**
	 * Metodo invocato in corrispondenza dell'annullamento dell'operazione d
	 * configurazione
	 * 
	 * @param button
	 *            Riferimento al Button
	 */
	public void cancelRefresh(View button) {
		// Permette di annullare l'azione
		finish();
	}

	/*
	 * Metodo di utilità per la lettura dell'identificatore dell'AppWidget dalle
	 * informazioni nell'Intent
	 */
	private int getWidgetId(Intent intent) {
		Bundle extras = intent.getExtras();
		int mAppWidgetId = 0;
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		return mAppWidgetId;
	}

}

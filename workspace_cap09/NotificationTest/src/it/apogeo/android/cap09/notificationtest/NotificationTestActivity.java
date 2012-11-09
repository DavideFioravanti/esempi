package it.apogeo.android.cap09.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationTestActivity extends Activity {
	/*
	 * Identifciatori delle notifiche
	 */
	private final static int SIMPLE_NOTIFICATION_ID = 1;
	private final static int SOUND_NOTIFICATION_ID = 2;
	private final static int VIBRATE_NOTIFICATION_ID = 3;
	private final static int LIGHT_NOTIFICATION_ID = 4;
	private final static int CUSTOM_NOTIFICATION_ID = 5;
	/*
	 * Riferimento al NotificationManager
	 */
	private NotificationManager notificationManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al NotificationManager
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/*
	 * Metodo di gestione di una notifica semplice
	 */
	public void simpleNotification(View button) {
		// Creiamo la Notification
		Notification notification = new Notification(R.drawable.icon,
				"Simple Notification", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Simple Notification");
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Simple Notification",
				"Simple Notification Extended", pIntent);
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
	}

	/*
	 * Metodo di gestione di una notifica con suono
	 */
	public void soundNotification(View button) {
		// Creiamo la Notification
		Notification notification = new Notification(R.drawable.icon,
				"Sound Notification", System.currentTimeMillis());
		notification.defaults |= Notification.DEFAULT_SOUND;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Sound Notification");
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Sound Notification",
				"Sound Notification Extended", pIntent);
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(SOUND_NOTIFICATION_ID, notification);
	}

	/*
	 * Metodo di gestione di una notifica con vbrazione
	 */
	public void vibrateNotification(View button) {
		// Creiamo la Notification
		Notification notification = new Notification(R.drawable.icon,
				"Vibrate Notification", System.currentTimeMillis());
		long[] vibrateData = { 100, 100, 200, 200, 80, 80 };
		notification.vibrate = vibrateData;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Vibrate Notification");
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Vibrate Notification",
				"Vibrate Notification Extended", pIntent);
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(VIBRATE_NOTIFICATION_ID, notification);
	}

	/*
	 * Metodo di gestione di una notifica con lampeggio
	 */
	public void lightNotification(View button) {
		// Creiamo la Notification
		Notification notification = new Notification(R.drawable.icon,
				"Light Notification", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Light Notification");
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(this, "Light Notification",
				"Vibrate Notification Extended", pIntent);
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(LIGHT_NOTIFICATION_ID, notification);
	}

	/*
	 * Metodo di gestione di una notifica con View espansa custom
	 */
	public void customNotification(View button) {
		// Creiamo la Notification
		Notification notification = new Notification(R.drawable.icon,
				"Custom Notification", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// Impostiamo le altre informazioni tra cui l'Intent
		Intent intent = new Intent(this, NotificationActivity.class);
		intent.putExtra("notificationType", "Custom Notification");
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		// Creiamo ed impostiamo la RemoteViews
		RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.remote_view_layout);
		remoteView.setTextViewText(R.id.firstText, "Primo Testo Custom View");
		remoteView.setTextViewText(R.id.secondText, "Secondo Testo Custom View");
		notification.contentView = remoteView;
		// Impostiamo l'Intent
		notification.contentIntent = pIntent;
		// La lanciamo attraverso il Notification Manager
		notificationManager.notify(CUSTOM_NOTIFICATION_ID, notification);
	}
}
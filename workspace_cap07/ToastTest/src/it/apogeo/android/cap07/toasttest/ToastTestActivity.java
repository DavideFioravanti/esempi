package it.apogeo.android.cap07.toasttest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastTestActivity extends Activity {
	// Tag del Log
	private final static String TAG_LOG = "ToastTestActivity";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * Visualizzazione di un Toast con durata breve
	 * @param view Riferimento al Button selezionato
	 */
	public void showShortToast(View view) {
		// Visualizziamo il Toast con durata Toast.LENGTH_SHORT
		Log.i(TAG_LOG,"showShortToast");
		Toast toast = Toast.makeText(this, R.string.short_label, Toast.LENGTH_SHORT);
		// Visualizziamo il toast
		toast.show();
	}

	/**
	 * Visualizzazione di un Toast con durata lunga
	 * @param view Riferimento al Button selezionato
	 */	
	public void showLongToast(View view) {
		// Visualizziamo il Toast con durata Toast.LENGTH_LONG
		Log.i(TAG_LOG,"showLongToast");
		Toast toast = Toast.makeText(this, R.string.long_label, Toast.LENGTH_LONG);
		// Visualizziamo il toast
		toast.show();		
	}

	/**
	 * Visualizzazione di un Toast con view personalizzata
	 * @param view Riferimento al Button selezionato
	 */	
	public void showCustomToast(View view) {
		// Carichiamo la View custom
		LayoutInflater inflater = LayoutInflater.from(this);
		View toastView = inflater.inflate(R.layout.toast_view, null);
		// Visualizziamo il Toast
		Log.i(TAG_LOG,"showCustomToast");
		Toast toast = Toast.makeText(this, R.string.custom_label, Toast.LENGTH_LONG);
		// Impostiamo la View del Toast
		toast.setView(toastView);
		// Visualizziamo il toast
		toast.show();		
	}
}
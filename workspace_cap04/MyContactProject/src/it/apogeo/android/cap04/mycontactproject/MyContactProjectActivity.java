package it.apogeo.android.cap04.mycontactproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MyContactProjectActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento all'eventuale Intent che ha attivato
		// questa attività
		Intent received = getIntent();
		// Riferimento alla TextView di output
		TextView output = (TextView) findViewById(R.id.output);
		// Otteniamo la URI associata all'Intent
		if (received != null) {
			// Visualizziamo l'evetuale URI associato
			Uri uri = received.getData();
			if (uri != null) {
				output.setText("Received Uri: " + received.getData());
			} else {
				output.setText("No Uri Received");
			}
		} else {
			// Indichiamo che non c'e' Intent
			output.setText("No Intent Received");
		}
	}
}
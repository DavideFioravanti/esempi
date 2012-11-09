package it.apogeo.android.cap04.intentactiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentActionTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Gestione del pulsante di Dial
		Button dialButton = (Button) findViewById(R.id.actionDialButton);
		dialButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Creiamo l'Intent associato alla Action ACTION_DIAL
				Intent dialIntent = new Intent();
				// Assegnamo l'azione
				dialIntent.setAction(Intent.ACTION_DIAL);
				// Lanciamo l'attività
				startActivity(dialIntent);
			}
		});

	}
}
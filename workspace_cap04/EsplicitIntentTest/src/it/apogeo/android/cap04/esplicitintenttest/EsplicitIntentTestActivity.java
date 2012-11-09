package it.apogeo.android.cap04.esplicitintenttest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EsplicitIntentTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Prima modalità di Intent Esplicito
		Button startButton1 = (Button) findViewById(R.id.startButton1);
		startButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo l'Intent
				Intent intent = new Intent(EsplicitIntentTestActivity.this,
						SecondActivity.class);
				// La lanciamo
				startActivity(intent);
			}

		});
		// Seconda modalità di Intent Esplicito
		Button startButton2 = (Button) findViewById(R.id.startButton2);
		startButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo l'Intent
				Intent intent = new Intent();
				/*
				 //Codice per l'attivazione di una attività appartenente ad un'altra
				 //applicazione
				 
				Class<?> destination = null;
				try {
					destination = Class
							.forName("it.apogeo.android.cap03.lifecycleactivitytest.LifecycleActivityTestActivity");
				} catch (Exception e) {
				}
				ComponentName component = new ComponentName(
						EsplicitIntentTestActivity.this, SecondActivity.class);
				*/
				// Creazione di un Component				
				ComponentName component = new ComponentName(
						EsplicitIntentTestActivity.this, SecondActivity.class);
				intent.setComponent(component);
				// La lanciamo
				startActivity(intent);
			}

		});
	}
}
package it.apogeo.android.cap03.resourcelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResourceLayoutActivity extends Activity {
	// COntatore
	private int counter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al Button
		Button pressButton = (Button) findViewById(R.id.pressButton);
		// Otteniamo il riferimento alla TextView
		final TextView outputView = (TextView) findViewById(R.id.output);
		// Ascoltiamo l'evento di pressione associato al pulsante
		pressButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Creazione dinamica della label
				//outputView.setText("Click # " + counter++);
				// Creazione della label con risorsa parametrizzata
				//String value = getResources().getString(R.string.output_label, counter++);
				//outputView.setText(value);
				// Utilizzo dei plurals
				String value = getResources().getQuantityString(R.plurals.conteggio, counter,counter);
				outputView.setText(value);
				counter++;
			}

		});
		
		String[] myArray = getResources().getStringArray(R.array.myArray);
		int[] primi =getResources().getIntArray(R.array.primeArray);

    }
}
package it.apogeo.android.cap06.customviewtest;

import it.apogeo.android.cap06.customviewtest.ColorChooser.OnColorSelectedListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CustomViewTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al ColorChooser
		ColorChooser colorChooser = (ColorChooser)findViewById(R.id.chooser);
		// Otteniamo il riferimento alla View
		final View outputView = findViewById(R.id.outputView);
		// Registriamo un listener per la modifica del colore della view
		colorChooser.setOnColorSelectedListener(new OnColorSelectedListener(){

			@Override
			public void onColorChanged(ColorChooser src, int newColor) {
				outputView.setBackgroundColor(newColor);
			}
			
		});
	}
}
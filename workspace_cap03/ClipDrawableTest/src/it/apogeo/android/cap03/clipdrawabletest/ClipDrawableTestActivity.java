package it.apogeo.android.cap03.clipdrawabletest;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClipDrawableTestActivity extends Activity {

	// Valore minimo per il livello
	private final static int MIN_LEVEL = 0;
	// Massimo Valore per il livello
	private final static int MAX_LEVEL = 10000;
	// Step per l'incremento del livello
	private final static int STEP = 200;

	private int level = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla ClipDrawable
		final ClipDrawable clipDrawable = (ClipDrawable) getResources()
				.getDrawable(R.drawable.clips);
		// Otteniamo il riferimento al Button
		Button clipButton = (Button) findViewById(R.id.clipButton);
		// Impostiamo la clipDrawable come Background del bottone
		clipButton.setBackgroundDrawable(clipDrawable);
		// Implementiamo un listener per il bottone per la gestione
		// del livello
		clipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				level += STEP;
				if (level > MAX_LEVEL) {
					level = MAX_LEVEL;
				} else if (level < MIN_LEVEL) {
					level = MIN_LEVEL;
				}
				Log.i("ClipDrawableTestActivity", "Level: " + level);
				clipDrawable.setLevel(level);
			}

		});
	}

}
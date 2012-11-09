package it.apogeo.android.cap03.levelListdrawabletest;

import android.app.Activity;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelListDrawableTestActivity extends Activity {
	// Valore minimo per il livello
	private final static int MIN_LEVEL = 0;
	// Massimo Valore per il livello
	private final static int MAX_LEVEL = 10000;
	// Step per l'incremento del livello
	private final static int STEP = 500;

	private int level = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla LevelListDrawable
		final LevelListDrawable levelListDrawable = (LevelListDrawable) getResources()
				.getDrawable(R.drawable.level_list);
		// Otteniamo il riferimento al Button
		Button levelButton = (Button) findViewById(R.id.levelButton);
		// Impostiamo la levelListDrawable come Background del bottone
		levelButton.setBackgroundDrawable(levelListDrawable);
		// Implementiamo un listener per il bottone per la gestione
		// del livello
		levelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				level += STEP;
				if (level > MAX_LEVEL) {
					level = MAX_LEVEL;
				} else if (level < MIN_LEVEL) {
					level = MIN_LEVEL;
				}
				Log.i("ClipDrawableTestActivity", "Level: " + level);
				levelListDrawable.setLevel(level);
			}

		});
	}
}
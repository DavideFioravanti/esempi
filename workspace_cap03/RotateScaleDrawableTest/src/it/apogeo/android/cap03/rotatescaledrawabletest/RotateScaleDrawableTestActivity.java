package it.apogeo.android.cap03.rotatescaledrawabletest;

import android.app.Activity;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class RotateScaleDrawableTestActivity extends Activity {
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
		// Otteniamo il riferimento al ScaleDrawable
		final ScaleDrawable scaleDrawable = (ScaleDrawable) getResources()
				.getDrawable(R.drawable.scale);
		// Riferimento al bottone
		Button levelButton = (Button) findViewById(R.id.levelButton);
		// Riferimento al LinearLayout contenitore delle View
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);		
        // Impostiamo lo ScaleDrawable come Background della View 
        linearLayout.setBackgroundDrawable(scaleDrawable);
        // Gestiamo gli eventi di pressione del button aumentando
        // il valore del livello
        levelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				level += STEP;
				if (level > MAX_LEVEL) {
					level = MAX_LEVEL;
				} else if (level < MIN_LEVEL) {
					level = MIN_LEVEL;
				}
				Log.i("RotateScaleDrawableTestActivity", "Level: " + level);
				scaleDrawable.setLevel(level);
			}

		});
	}
}
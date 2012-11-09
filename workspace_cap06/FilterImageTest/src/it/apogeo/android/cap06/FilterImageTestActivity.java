package it.apogeo.android.cap06;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.ImageView;

public class FilterImageTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento all'ImageView
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		// Esempio di utilizzo di un LightingColorFilter
		/*
		 * int multiplyValue = 0xFFFFFFFF; int addValue = 0x000000FF;
		 * LightingColorFilter filter = new
		 * LightingColorFilter(multiplyValue,addValue);
		 * imageView.setColorFilter(filter);
		 */
		// Esempio di utilizzo di un ColorMatrixColorFilter
		ColorMatrix matrix = createColorMatrixFromContrast(0.9f);
		ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
		imageView.setColorFilter(colorFilter);

	}

	/*
	 * Metodo di utilità che crea un ColorMatrix da applicare per la
	 * gestione del contrasto
	 */
	private ColorMatrix createColorMatrixFromContrast(float contrast) {
		ColorMatrix cm = new ColorMatrix();
		float scale = contrast + 1.f;
		float translate = (-.5f * scale + .5f) * 255.f;
		cm.set(new float[] { scale, 0, 0, 0, translate, 0, scale, 0, 0,
				translate, 0, 0, scale, 0, translate, 0, 0, 0, 1, 0 });
		return cm;
	}
}
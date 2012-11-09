/**
 * 
 */
package it.apogeo.android.cap07.customanimationtest;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author MASSIMO
 * 
 */
public class MyAnimation extends Animation {

	private final static float DEFAULT_ROTATION_RATE = 1.0f;

	/*
	 * Definisce la velocità con cui ruotare la View
	 */
	private float rate = DEFAULT_ROTATION_RATE;

	private float pivotX;
	private float pivotY;

	public MyAnimation() {
		super();
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		// Salviamo le dimensioni del pivot centrale rispetto al componente
		// stesso
		pivotX = width / 2;
		pivotY = height / 2;
		// Impostiamo la durata ad 1 secondo
		setDuration(1000L);
		// Facciamo in modo che persista
		setFillAfter(true);
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		// Otteniamo il riferimento alla matrice di trasformazione
		Matrix matrix = t.getMatrix();
		// Applichiamo una trasformazione
		float rotateValue = interpolatedTime * 180f * rate;
		rotateValue = (rotateValue < 180f) ? rotateValue : 180f;
		matrix.setRotate(rotateValue, pivotX, pivotY);
	}

}

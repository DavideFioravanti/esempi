/**
 * 
 */
package it.apogeo.android.cap07.cameraanimationtest;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author MASSIMO
 * 
 */
public class CameraAnimation extends Animation {

	private final static float DEFAULT_ROTATION_RATE = 1.0f;

	/*
	 * Definisce la velocità con cui ruotare la View
	 */
	private float rate = DEFAULT_ROTATION_RATE;

	private float pivotX;
	private float pivotY;

	public CameraAnimation() {
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
		Camera camera = new Camera();
		// Salviamo l'immagine in questo momento
		camera.save();
		// Spostiamo la camera
		camera.rotateX(interpolatedTime*60);
		// Ci facciamo dare la corrispondente matrice mettendola in quella attiva
		camera.getMatrix(matrix);
		matrix.preTranslate(-pivotX,-pivotY);
		matrix.postTranslate(pivotX,pivotY);
		// Resettiamo la camera
		camera.restore();
	}

}

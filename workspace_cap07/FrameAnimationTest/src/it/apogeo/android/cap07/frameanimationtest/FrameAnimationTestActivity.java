package it.apogeo.android.cap07.frameanimationtest;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameAnimationTestActivity extends Activity {
	/*
	 * Riferimento all'animazione
	 */
	private AnimationDrawable animationDrawable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla AnimationDrawable dalla View
		ImageView imageView = (ImageView) findViewById(R.id.animationView);
		animationDrawable = (AnimationDrawable) imageView.getBackground();
	}

	/**
	 * Permette di avviare l'animazione
	 * @param src Riferimento alla View sorgente dell'evento di onClick
	 */
	public void startAnimation(View src) {
		if(!animationDrawable.isRunning()){
			animationDrawable.start();			
		}
	}

	/**
	 * Permette di fermare l'animazione
	 * @param src Riferimento alla View sorgente dell'evento di onClick
	 */	
	public void stopAnimation(View src) {
		if(!animationDrawable.isRunning()){
			animationDrawable.stop();		
		}		
	}
}
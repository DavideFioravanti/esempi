package it.apogeo.android.cap07.heartanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class HeartAnimationActivity extends Activity {
	
	/*
	 * L'animazione
	 */
	private AnimatorSet set;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		// Otteniamo il riferimento all'ImageView
		ImageView heartView = (ImageView)findViewById(R.id.heart_image);
		// Creiamo l'adapter
		ImageViewSizeAdapter adapter = new ImageViewSizeAdapter(heartView);		
		// Carichiamo il riferimento all'animazione
		set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
				R.animator.beating);
		// Impostiamo il target
		set.setTarget(adapter);
		// Avviamo l'animazione
		set.start();		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		// Fermiamo l'animazione
		set.cancel();
	}
}
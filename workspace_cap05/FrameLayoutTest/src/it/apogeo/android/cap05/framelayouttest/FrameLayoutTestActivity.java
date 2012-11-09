package it.apogeo.android.cap05.framelayouttest;

import android.app.Activity;
import android.os.Bundle;

public class FrameLayoutTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		/*
		setContentView(R.layout.main); 
		// Riferimenti alle due View
		final LinearLayout firstFrame = (LinearLayout)findViewById(R.id.firstFrame);
		final LinearLayout secondFrame = (LinearLayout)findViewById(R.id.secondFrame);
		// Gestione primo Frame
		Button firstButton = (Button) findViewById(R.id.toSecond);
		firstButton.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// Nascondiamo il primo frame
				firstFrame.setVisibility(FrameLayout.GONE);
				// Visualizziamo il secondo
				secondFrame.setVisibility(FrameLayout.VISIBLE);
			}
		});
		// Gestione secondo Frame
		Button secondButton = (Button) findViewById(R.id.toFirst);
		secondButton.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// Nascondiamo il secondo frame
				secondFrame.setVisibility(FrameLayout.GONE);
				// Visualizziamo il primo
				firstFrame.setVisibility(FrameLayout.VISIBLE);
			}
		});		
		*/
		
	}
}
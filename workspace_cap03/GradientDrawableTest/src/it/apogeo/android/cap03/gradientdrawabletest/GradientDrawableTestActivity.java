package it.apogeo.android.cap03.gradientdrawabletest;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;

public class GradientDrawableTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		GradientDrawable gradientDrawable = (GradientDrawable) getResources()
				.getDrawable(R.drawable.shapes);
		Button gradientButton = (Button) findViewById(R.id.gradientButton);
		gradientButton.setBackgroundDrawable(gradientDrawable);
	}
}
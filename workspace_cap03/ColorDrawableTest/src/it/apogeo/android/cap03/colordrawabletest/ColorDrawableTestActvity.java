package it.apogeo.android.cap03.colordrawabletest;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class ColorDrawableTestActvity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ColorDrawable cd = (ColorDrawable) getResources().getDrawable(R.drawable.blue_transp_rect);
        View backgroundView = findViewById(R.id.linearLayout);
        backgroundView.setBackgroundDrawable(cd);		
        //android.R.attr.inn
	}
}
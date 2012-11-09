package it.apogeo.android.cap13.statefragmentproject;

import android.app.Activity;
import android.os.Bundle;

public class StateFragmentProjectActivity extends Activity {
	/*
	 * TAG per il LOG
	 */
	protected final String LOG_TAG = "StateFragmentProject"; 	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

}
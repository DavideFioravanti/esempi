package it.apogeo.android.cap06.radiobuttontest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

public class RadioButtonTestActivity extends Activity {
	// Tag per il log
	private final static String RADIO_BUTTON_TEST_TAG= "RadioButtonTestActivity";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al Radiogroup
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		// Registriamo l'ascoltatore
		RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup radiogroup, int checkedId) {
				// A seconda del checkId cambiamo il colore di sfondo del container
				if(checkedId == R.id.redRadio){
					radioGroup.setBackgroundColor(Color.RED);
				}else if(checkedId == R.id.greenRadio){
					radioGroup.setBackgroundColor(Color.GREEN);
				}else if(checkedId == R.id.blueRadio){
					radioGroup.setBackgroundColor(Color.BLUE);
				}else{
					Log.w(RADIO_BUTTON_TEST_TAG, "Source not correct. Check code!");
				}
				
			}

		};
		// Registriamo l'ascoltatore al Radiogroup
		radioGroup.setOnCheckedChangeListener(listener);
	}
}
/**
 * 
 */
package it.apogeo.android.cap07.optionmenuselectiontest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author MASSIMO
 * 
 */
public class TestActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView outputMessage = (TextView) findViewById(R.id.outputMessage);
		outputMessage.setText(R.string.destination_message);
	}

}

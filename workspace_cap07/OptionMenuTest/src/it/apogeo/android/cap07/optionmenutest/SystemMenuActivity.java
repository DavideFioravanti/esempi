/**
 * 
 */
package it.apogeo.android.cap07.optionmenutest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author MASSIMO
 * 
 */
public class SystemMenuActivity extends Activity {

	// Tag del log
	private final static String LOG_TAG = "SystemMenuActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView outputMessage = (TextView) findViewById(R.id.outputMessage);
		outputMessage.setText(R.string.system_menu_label);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		Log.i(LOG_TAG, "onPrepareOptionsMenu");
		return true;
	}

}

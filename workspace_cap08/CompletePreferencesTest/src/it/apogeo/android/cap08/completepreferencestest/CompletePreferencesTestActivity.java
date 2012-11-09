package it.apogeo.android.cap08.completepreferencestest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CompletePreferencesTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alle SharedPreferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// Otteniamo i valori delle preferenze
		String textValue = prefs.getString("textData", "No Text Value");
		boolean checkedValue = prefs.getBoolean("enabledData", false);
		String listValue = prefs.getString("month", "No List value");
		// Lo scriviamo nella TextView
		TextView outputView = (TextView) findViewById(R.id.output);
		outputView.setText(textValue + " checked:" + checkedValue
				+ " listValue:" + listValue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Aggiungiamo il solo MenuItem con l'intent per
		// le opzioni
		MenuItem menuItem = menu.add(Menu.FIRST, Menu.FIRST, 1, "Preferences");
		menuItem.setIntent(new Intent(this, MyPreferenceActivity.class));
		return true;
	}

}
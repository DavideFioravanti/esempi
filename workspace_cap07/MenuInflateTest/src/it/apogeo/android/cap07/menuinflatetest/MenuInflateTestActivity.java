package it.apogeo.android.cap07.menuinflatetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuInflateTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Otteniamo il riferimento all'Inflater del Menu
		MenuInflater inflater = getMenuInflater();
		// Eseguamo l'inflate del file di menu sull'oggetto menu
		inflater.inflate(R.menu.my_menu, menu);
		// Visualizziamo il Menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.isCheckable()) {
			item.setChecked(!item.isChecked());
		}
		return super.onOptionsItemSelected(item);
	}

}
package it.apogeo.android.cap07.usealernativetest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UseAlernativeTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Ordine
		int order = Menu.FIRST;
		// Aggiungiamo una voce dei menu normale
		menu.add(0, order, order++, "Voce1");
		// Aggiungiamo le voci di menu alternative relative all'Intent di
		// gestione del dato indicato
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("content://contacts/people"));
		intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
		menu.addIntentOptions(1, 1, Menu.CATEGORY_ALTERNATIVE, this.getComponentName(), null,
				intent, 0, null);
		// Visualizziamo il menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Aggiungiamo una voce di Menu normale
		return super.onOptionsItemSelected(item);
	}

}
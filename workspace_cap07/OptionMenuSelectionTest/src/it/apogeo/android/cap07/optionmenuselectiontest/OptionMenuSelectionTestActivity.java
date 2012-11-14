package it.apogeo.android.cap07.optionmenuselectiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;

public class OptionMenuSelectionTestActivity extends Activity {
	// Tag del log
	private final static String LOG_TAG = "OptionMenuSelectionTestActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView outputMessage = (TextView) findViewById(R.id.outputMessage);
		outputMessage.setText(R.string.help_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Accediamo agli eventuali MenuItem di sistema
		super.onCreateOptionsMenu(menu);
		// Creiamo un Intent di test
		Intent intent = new Intent(this, TestActivity.class);
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		// Impostiamo l'ordine iniziale
		int order = Menu.FIRST;
		// Creiamo il primo gruppo di MenuItem
		int GROUPA = 0;
		// I primi due passano SOLO per onOptionsItemSelected
		menu.add(GROUPA, order, order++, "ItemA1").setIntent(intent);
		menu.add(GROUPA, order, order++, "ItemA2").setIntent(intent);
		// Esempio di gestione degli eventi con Listener
		//Questi ultimi 2 hanno un onMenuItemClickListener quindi prima si passa
		//Per il OnMenuItemClickListener e poi nell onOptionsItemSelected
		menu.add(GROUPA, order, order++, "ItemA3").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						Log.i(LOG_TAG, "onMenuItemClick "+ menuItem.getTitle());
						// Ritorniamo true
						return true;
					}

				}).setIntent(new Intent());
		menu.add(GROUPA, order, order++, "ItemA4").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						Log.i(LOG_TAG, "onMenuItemClick "+ menuItem.getTitle());
						// Ritorniamo false
						return false;
					}
					//DA errore perchè crea un intent vuoto..
					//per farlo funzionare basta mettere
					//}).setIntent(intent);
				}).setIntent(new Intent());
		// Visualizziamo il Menu
		//MEttendo false si inibisce il click del menù
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		Log.i(LOG_TAG, "onPrepareOptionsMenu");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(LOG_TAG, "onOptionsItemSelected Called!");
		if (item.getItemId() == Menu.FIRST) {
			Log.i(LOG_TAG, "ItemA1 Selected");
			//Significa che l'evento è concluso
			return true;
		} else if (item.getItemId() == Menu.FIRST + 1) {
			Log.i(LOG_TAG, "ItemA2 Selected");
			//Significa che l'evento ancora non è stato consumato e quindi continua
			return false;
		}
		// Importante richiamare l'implementazione padre nel caso
		// di item non gestiti
		return super.onOptionsItemSelected(item);
	}
}
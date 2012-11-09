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
public class SimpleMenuActivity extends Activity {

	// Tag del log
	private final static String LOG_TAG = "SimpleMenuActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView outputMessage = (TextView) findViewById(R.id.outputMessage);
		outputMessage.setText(R.string.simple_menu_label);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Accediamo agli eventuali MenuItem di sistema
		super.onCreateOptionsMenu(menu);
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		// Impostiamo l'ordine iniziale
		int order = Menu.FIRST;
		// Creiamo il primo gruppo di MenuItem
		int GROUPA = 0;
		menu.add(GROUPA, 0, order++, "ItemA1");
		menu.add(GROUPA, 1, order++, "ItemA2");
		// Creiamo il secondo gruppo che � checkable. NOTIAMO COME
		// MenuItem di questo tipo non possano essere checkable e quindi
		// tale impostazione venga ignorata
		int GROUPB = 1;
		menu.add(GROUPB, 2, order++, "ItemB1").setCheckable(true);
		menu.add(GROUPB, 3, order++, "ItemB2").setCheckable(true);
		// Creiamo il terzo gruppo
		int GROUPC = 2;
		menu.add(GROUPC, 4, order++, "ItemC1").setIcon(R.drawable.icon);
		menu.add(GROUPC, 5, order++, "ItemC2");		
		// Visualizziamo il Menu
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		Log.i(LOG_TAG, "onPrepareOptionsMenu");
		return true;
	}

}

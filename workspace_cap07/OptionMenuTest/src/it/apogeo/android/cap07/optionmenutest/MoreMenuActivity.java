/**
 * 
 */
package it.apogeo.android.cap07.optionmenutest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author MASSIMO
 * 
 */
public class MoreMenuActivity extends Activity {

	// Tag del log
	private final static String LOG_TAG = "MoreMenuActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView outputMessage = (TextView) findViewById(R.id.outputMessage);
		outputMessage.setText(R.string.more_menu_label);
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
		// Creiamo il secondo gruppo che è checkable. NOTIAMO COME
		// MenuItem di questo tipo non possano essere checkable e quindi
		// tale impostazione venga ignorata
		int GROUPB = 1;
		menu.add(GROUPB, 2, order++, "ItemB1").setCheckable(true);
		menu.add(GROUPB, 3, order++, "ItemB2").setCheckable(true);
		// Creiamo il terzo gruppo
		int GROUPC = 2;
		menu.add(GROUPC, 4, order++, "ItemC1").setIcon(R.drawable.icon);
		menu.add(GROUPC, 5, order++, "ItemC2");
		int GROUPD = 3;
		menu.add(GROUPD, 6, order++, "ItemD1");
		menu.add(GROUPD, 7, order++, "ItemD2").setIcon(R.drawable.icon);
		int GROUPE = 4;
		menu.add(GROUPE, 8, order++, "ItemD3").setCheckable(true);
		menu.add(GROUPE, 9, order++, "ItemD4").setIcon(R.drawable.icon)
				.setCheckable(true);
		// Impostiamo il Gruppo come checkable
		//menu.setGroupCheckable(GROUPE, true, true);
		// Visualizziamo il Menu
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
		if (item.getItemId() == 8) {
			// Modifichiamo lo stato di selezione dell'item
			item.setChecked(!item.isChecked());
			return true;
		} else if (item.getItemId() == 9) {
			// Modifichiamo lo stato di selezione dell'item
			item.setChecked(!item.isChecked());
			return true;
		}
		// Importante richiamare l'implementazione padre nel caso
		// di item non gestiti
		return super.onOptionsItemSelected(item);
	}	

}

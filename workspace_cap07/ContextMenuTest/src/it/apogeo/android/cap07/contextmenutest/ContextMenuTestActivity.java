package it.apogeo.android.cap07.contextmenutest;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ContextMenuTestActivity extends ListActivity {
	// Tag del log
	private final static String LOG_TAG = "ContextMenuTestActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Riferimento agli elementi
		String[] months = getResources().getStringArray(R.array.month_array);
		// Creiamo l'Adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, months);
		// Impostiamo l'Adapter
		setListAdapter(adapter);
		// Registriamo la ListView per il ContextMenu
		registerForContextMenu(getListView());
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// Sappiamo che le View provengono da una specializzazione di
		// AdapterView per cui eseguiamo il caso della MenuInfo
		AdapterContextMenuInfo adapterMenuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		if (adapterMenuInfo == null) {
			// Se null significa che siamo nel SubMenu le cui voci non sono
			// linkate
			// alla View proveniente dall'Adapter.Visualizziamo quindi il valore
			// associato
			Log.i(LOG_TAG, "Voce selezionata " + item.getTitle());
			// Gestiamo lo stato di selezione
			item.setChecked(!item.isChecked());
		} else {
			// Otteniamo il riferimento alla View selezionata che nel nostro
			// caso
			// è una TextView
			TextView selectedView = (TextView) adapterMenuInfo.targetView;
			CharSequence value = selectedView.getText();
			// Visualizziamo un messaggio di log
			Log.i(LOG_TAG, "Voce selezionata " + value);
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// Sappiamo che le View provengono da una specializzazione di
		// AdapterView per cui eseguiamo il caso della MenuInfo
		AdapterContextMenuInfo adapterMenuInfo = (AdapterContextMenuInfo) menuInfo;
		// Otteniamo le informazioni relative alla View peralimentare il menù
		// contestuale. Nello specifico vediamo se l'indice è pari o dispari
		// Otteniamo il riferimento alla View selezionata che nel nostro caso
		// è una TextView
		TextView selectedView = (TextView) adapterMenuInfo.targetView;
		CharSequence value = selectedView.getText();
		// Identificatore dell'item
		int itemId = Menu.FIRST;
		if ((adapterMenuInfo.id % 2) == 0) {// pari
			// L'icona viene ignorata in un context menu
			menu.add(0, itemId, itemId++, "Aggiorna " + value).setIcon(
					R.drawable.icon);
			// Impostiamo l'header
			menu.setHeaderTitle(R.string.even_title);
		} else { // dispari
			// L'icona viene ignorata in un context menu
			menu.add(0, itemId, itemId++, "Sostituisci " + value).setIcon(
					R.drawable.icon);
			// Impostiamo l'header
			menu.setHeaderTitle(R.string.odd_title);
		}
		// Aggiungiamo un comando comune
		menu.add(0, itemId, itemId++, "Cancella " + value);
		// Testiamo l'aggiunta di un Sottomenu
		SubMenu subMenu = menu.addSubMenu(1, itemId, itemId++, "SottoMenu");
		subMenu.add(1, itemId, itemId++, "SottoMenu 1").setCheckable(true);
		subMenu.add(1, itemId, itemId++, "SottoMenu 2").setCheckable(true);
		subMenu.add(2, itemId, itemId++, "SottoMenu 3");
		subMenu.add(3, itemId, itemId++, "SottoMenu 4");
		subMenu.add(3, itemId, itemId++, "SottoMenu 5");
		subMenu.setGroupCheckable(3, true, true);
		// Impostiamo l'icona dell'intestazione del menu
		menu.setHeaderIcon(R.drawable.icon);
	}

}
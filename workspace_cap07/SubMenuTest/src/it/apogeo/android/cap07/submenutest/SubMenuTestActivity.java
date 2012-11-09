package it.apogeo.android.cap07.submenutest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class SubMenuTestActivity extends Activity {
	// Tag del log
	private final static String LOG_TAG = "SubMenuTestActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Posizione di riferimento
		int order = Menu.FIRST;
		// Definizione del gruppo
		int myGroupId = 0;
		// Aggiungiamo un menu normale
		menu.add(myGroupId, order, order++, "Normal");
		// Aggiungiamo un sottomenu
		int subGroupId = 10;
		SubMenu subMenu = menu
				.addSubMenu(subGroupId, order, order++, "SubMenu");
		subMenu.add(subGroupId + 1, order, order++, "Prima Multipla")
				.setCheckable(true);
		subMenu.add(subGroupId + 1, order, order++, "Seconda Multipla")
				.setCheckable(true);
		subMenu.add(subGroupId + 2, order, order++, "Prima Exclusive");
		subMenu.add(subGroupId + 2, order, order++, "Seconda Exclusive");
		subMenu.setGroupCheckable(subGroupId + 2, true, true);
		// Impostiamo il titolo
		subMenu.setHeaderTitle(R.string.sub_title);
		subMenu.setHeaderIcon(R.drawable.icon);
		subMenu.setIcon(R.drawable.icon);
		// Visualizziamo il menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(LOG_TAG, "onPrepareOptionsMenu " + item.getTitle());
		item.setChecked(!item.isChecked());
		return super.onOptionsItemSelected(item);
	}

}
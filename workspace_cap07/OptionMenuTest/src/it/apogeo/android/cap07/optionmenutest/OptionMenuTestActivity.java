package it.apogeo.android.cap07.optionmenutest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

//Estenda la classe TabActvity
public class OptionMenuTestActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Otteniamo il TabHost per la gestione dei tab
		TabHost tabHost = getTabHost();
		//In questo modo possiamo aggiungere delle activity vere e proprie all'interno dei tab
		// Aggiungiamo il tab relativo ai MenuItem di sistema
		tabHost.addTab(tabHost.newTabSpec("System").setContent(
				new Intent(this, SystemMenuActivity.class)).setIndicator(
				"System"));
		// Aggiungiamo il tab relativo ai MenuItem di un menu semplice
		tabHost.addTab(tabHost.newTabSpec("Simple").setContent(
				new Intent(this, SimpleMenuActivity.class)).setIndicator(
				"Simple"));	
		// Aggiungiamo il tab relativo ai MenuItem di un menu con more
		tabHost.addTab(tabHost.newTabSpec("More").setContent(
				new Intent(this, MoreMenuActivity.class)).setIndicator(
				"More"));			

	}
}
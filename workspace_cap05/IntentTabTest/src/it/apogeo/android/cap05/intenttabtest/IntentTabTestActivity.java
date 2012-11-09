package it.apogeo.android.cap05.intenttabtest;

import java.util.Date;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;

public class IntentTabTestActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Otteniamo il riferimento al TabHost
		TabHost tabHost = getTabHost();
		// Aggiungiamo un secondo tab che utilizza una factory
		tabHost.addTab(tabHost.newTabSpec("Factory").setContent(
				new MyTabContentFactory(this)).setIndicator("Factory"));
		// Aggiungiamo il terzo Tab relativo ad un Intent
		Intent newIntent = new Intent(this,SecondActivity.class);
		tabHost.addTab(tabHost.newTabSpec("Intent").setContent(newIntent).setIndicator("Intent"));		
	}

	/**
	 * Classe statica interna che definisce una specializzazione di
	 * TabContentFactory
	 */
	private static class MyTabContentFactory implements TabContentFactory {

		// Riferimento al Context
		private Context context;

		public MyTabContentFactory(Context context) {
			this.context = context;
		}

		@Override
		public View createTabContent(String tag) {
			final TextView textView = new TextView(context);
			Log.i("MyTabContentFactory", "createTabContent");
			// Impostiamo come testo la data corrente
			textView.setText("Date" + new Date());
			// La ritorniamo
			return textView;
		}
	}
}
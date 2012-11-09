package it.apogeo.android.cap05.completetabtest;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;

public class CompleteTabTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al TabHost
		TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);
		tabHost.setup();
		// Aggiungiamo il Tab associato alla prima view
		tabHost.addTab(tabHost.newTabSpec("Marta").setContent(R.id.martaView)
				.setIndicator("Marta"));
		// Aggiungiamo un secondo tab che utilizza una factory
		tabHost.addTab(tabHost.newTabSpec("Factory").setContent(
				new MyTabContentFactory(this)).setIndicator("Factory"));
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
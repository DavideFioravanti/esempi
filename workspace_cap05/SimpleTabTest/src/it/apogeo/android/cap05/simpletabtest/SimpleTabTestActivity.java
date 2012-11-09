package it.apogeo.android.cap05.simpletabtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class SimpleTabTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		// Otteniamo il riferimento al TabHost
		TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);
		tabHost.setup();		
		// Aggiungiamo il Tab associato alla prima view
		tabHost.addTab(tabHost.newTabSpec("Alice").setContent(R.id.aliceView)
				.setIndicator("Alice"));
		// Aggiungiamo il Tab associato alla seconda view
		tabHost.addTab(tabHost.newTabSpec("Marta").setContent(R.id.martaView)
				.setIndicator("Marta"));
	}
}
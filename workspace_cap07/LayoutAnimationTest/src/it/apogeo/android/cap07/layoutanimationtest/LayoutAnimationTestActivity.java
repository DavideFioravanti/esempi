package it.apogeo.android.cap07.layoutanimationtest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class LayoutAnimationTestActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Otteniamo il TabHost per la gestione dei tab
		TabHost tabHost = getTabHost();
		// Aggiungiamo il tab relativo all'animazione di tipo scale
		tabHost.addTab(tabHost.newTabSpec("Scale").setContent(
				new Intent(this, ScaleTestActivity.class)).setIndicator("Scale"));
		// Aggiungiamo il tab relativo all'animazione di tipo rotate
		tabHost.addTab(tabHost.newTabSpec("Rotate").setContent(
				new Intent(this, RotateTestActivity.class)).setIndicator("Rotate"));
		// Aggiungiamo il tab relativo all'animazione di tipo transate
		tabHost.addTab(tabHost.newTabSpec("Translate").setContent(
				new Intent(this, TranslateTestActivity.class)).setIndicator("Translate"));		
		// Aggiungiamo il tab relativo all'animazione di tipo alpha
		tabHost.addTab(tabHost.newTabSpec("Alpha").setContent(
				new Intent(this, AlphaTestActivity.class)).setIndicator("Alpha"));
		// Aggiungiamo il tab relativo all'animazione di tipo alpha
		tabHost.addTab(tabHost.newTabSpec("Set").setContent(
				new Intent(this, SetTestActivity.class)).setIndicator("Set"));		
	}
}
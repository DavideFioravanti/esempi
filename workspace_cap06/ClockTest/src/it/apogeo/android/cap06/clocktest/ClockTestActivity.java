package it.apogeo.android.cap06.clocktest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class ClockTestActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		getTabHost().addTab(tabHost.newTabSpec("Analog").setContent(new Intent(this,AnalogActivity.class)).setIndicator("Analog"));
		getTabHost().addTab(tabHost.newTabSpec("Digital").setContent(new Intent(this,DigitalActivity.class)).setIndicator("Digital"));
	}
}
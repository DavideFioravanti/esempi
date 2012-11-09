package it.apogeo.android.cap09.broadcastreceivertest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiverTestActivity extends Activity {
	
	private final BroadcastReceiver timeBroadcastReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(BroadcastReceiverTestActivity.this, "BroadCast Intent Receiver", Toast.LENGTH_SHORT).show();
		}
		
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(timeBroadcastReceiver);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(timeBroadcastReceiver,new IntentFilter(Intent.ACTION_TIME_TICK));		
	}
    
    
}
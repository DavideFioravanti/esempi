package it.apogeo.android.cap04.senderproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SenderProjectActivity extends Activity {
	/*
	 * Esempio di azione custom
	 */
	public static final String CUSTOM_ACTION = "it.apogeo.android.cap04.senderproject.intent.action.CUSTOM_ACTION";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button sendButton = (Button)findViewById(R.id.senderButton);
        sendButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// Creiamo l'Intent per la Action standard
				Intent intent = new Intent();
				// Associamo l'azione
				intent.setAction(CUSTOM_ACTION);
				// Lanciamo l'attività
				Log.i("SenderProjectActivity","Launching Intent custom");
				startActivity(intent);
			}
        	
        });
    }
}
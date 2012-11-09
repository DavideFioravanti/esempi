package it.apogeo.android.cap04.intentdatatest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentDataTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Gestiamo il pulsante relativo ai contatti
		Button viewContactButton = (Button) findViewById(R.id.viewContactButton);
		viewContactButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo un Intent di editazione di un contatto
				Intent intent = new Intent(Intent.ACTION_VIEW);
				// Nel caso in cui si volesse accedere al contatto di
				// id pari a 1 si utilizza la seguente istruzione
				// per ottenere il corrispondnete URI
				//Uri contact = Uri.withAppendedPath(android.provider.ContactsContract.Contacts.CONTENT_URI, "1");
				intent.setData(android.provider.ContactsContract.Contacts.CONTENT_URI);
				//intent.setData(contact);
				Log.i("IntentDataTestActivity", "Contacts URI: "
						+ android.provider.ContactsContract.AUTHORITY_URI);
				startActivity(intent);
			}

		});
		// Gestiamo il pulsante relativo al telefono
		Button viewTelButton = (Button) findViewById(R.id.viewTelButton);
		viewTelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo un Intent di visualizzazione telefono
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri telUri = Uri.parse("tel://123456789");
				Log.i("IntentDataTestActivity", "Tel URI: " + telUri);
				intent.setData(telUri);
				startActivity(intent);
			}

		});
	}
}
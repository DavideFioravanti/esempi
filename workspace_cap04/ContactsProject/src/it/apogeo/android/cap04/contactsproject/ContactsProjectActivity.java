package it.apogeo.android.cap04.contactsproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactsProjectActivity extends Activity {
	
	// Identifica la chiamata all'attività di scelta del contatto
	private final static int CHOOSE_CONTACT_CODE = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Gestiamo il pulsante relativo ai contatti
		Button pickContactButton = (Button) findViewById(R.id.pickContactButton);
		pickContactButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo un Intent di editazione di un contatto
				// Caso sbagliato di utilizzo di ACTION_VIEW
				//Intent intent = new Intent(Intent.ACTION_VIEW);
				// Caso corretto di utilizzo di ACTION_PICK
				Intent intent = new Intent(Intent.ACTION_PICK);
				// Impostiamo l'URI relativo alla scelta del contatto
				intent.setData(android.provider.ContactsContract.Contacts.CONTENT_URI);
				Log.i("ContactsProjectActivity", "Contacts URI: "
						+ android.provider.ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, CHOOSE_CONTACT_CODE);
			}

		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode,resultCode,data);
		if(requestCode==CHOOSE_CONTACT_CODE){
			// Riferimento alla TextView
			TextView textView = (TextView)findViewById(R.id.output);
			// Si tratta dell'attività che avevamo invocato. Gestiamo quindi
			// i diversi casi
			if(resultCode == Activity.RESULT_OK){
				// Risultato corretto per cui visualizziamo le informazioni di data nella TextView
				textView.setText("Result is "+data.getData());
				Log.i("ContactsProjectActivity","Result is "+data.getData());
			}else if(resultCode == Activity.RESULT_CANCELED){
				// Operazione cancellata
				textView.setText("RESULT_CANCELED");
				Log.i("ContactsProjectActivity","RESULT_CANCELED");
			}else{
				// Caso custom di valori ch iniziamo dal valore RESULT_FIRST_USER
				textView.setText("CUSTOM RESULT "+resultCode);
				Log.i("ContactsProjectActivity","CUSTOM RESULT "+resultCode);
			}
		}else{
			// E' arrivata una risposta da una attività diversa (impossibile in questo caso!)
			Log.w("ContactsProjectActivity","Result from unknown Activity");
		}
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		Log.w("ContactsProjectActivity","finishActivityFromChild");
		super.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void finishFromChild(Activity child) {
		Log.w("ContactsProjectActivity","finishFromChild");
		super.finishFromChild(child);
	}
	
	
    
    
    
}
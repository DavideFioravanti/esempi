/**
 * 
 */
package it.apogeo.android.cap06.customlinkifytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Utente
 *
 */
public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Leggiamo il valore contenuto nello URI dell'intent
		Intent intent = getIntent();
		// Otteniamo l'URI
		Uri intentUri = intent.getData();
		// Lo visualizziamo nella textView
		TextView output = (TextView)findViewById(R.id.output);
		// Visualizziamo il path senza /
		output.setText(intentUri.getPath().substring(1));
	}
	
	

}

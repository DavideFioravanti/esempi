package it.apogeo.android.cap07.dataintenttest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class DataIntentTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento all'Intent scatenante
		Intent intent = getIntent();
		// Otteniamo il riferimento alla TextView
		TextView outputView = (TextView)findViewById(R.id.output);
		// Se otteniamo il riferimento al Dato corrispondente
		if(intent!=null){
			// Otteniamo il corrispondente campo data
			Uri dataUri = intent.getData();
			if(dataUri!=null){
				// Visualizziamo il campo data
				outputView.setText("Uri:"+dataUri);
			}else{
				// Non è presente il campo data
				outputView.setText("No Data in Intent!");	
			}
		}else{
			outputView.setText("No Intent!");
		}
	}
}
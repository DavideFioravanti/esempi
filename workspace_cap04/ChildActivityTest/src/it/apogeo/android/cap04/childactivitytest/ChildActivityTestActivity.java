package it.apogeo.android.cap04.childactivitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ChildActivityTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento all'Intent di arrivo
		final Intent sourceIntent = getIntent();
		// Visualizziamo i data nella textView
		if(sourceIntent!=null){
			TextView output = (TextView) findViewById(R.id.output);
			String data = ""+sourceIntent.getData();
			output.setText(data);
			Log.i("ChildActivityTestActivity", "Input URI is: "+data);
		}
		// Otteniamo il riferimento al Button di finish
		Button finishButton = (Button) findViewById(R.id.finishButton);
		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Eseguiamo direttamente il finish
				Log.i("ChildActivityTestActivity", "finish");
				finish();
			}

		});
		// Otteniamo il riferimento al Button di finish con risultato OK
		Button okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Impostiamo il risultato
				setResult(Activity.RESULT_OK, sourceIntent);
				// Eseguiamo il finish
				Log.i("ChildActivityTestActivity", "finish OK");
				finish();
			}

		});
		// Otteniamo il riferimento al Button di finishcon risultato canceled
		Button canceledButton = (Button) findViewById(R.id.canceledButton);
		canceledButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Impostiamo il risultato
				setResult(Activity.RESULT_CANCELED, sourceIntent);
				// Eseguiamo il finish
				Log.i("ChildActivityTestActivity", "finish CANCELED");
				finish();
			}

		});
	}
}
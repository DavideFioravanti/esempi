package it.apogeo.android.cap04.newtasktest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewTaskTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView output = (TextView)findViewById(R.id.output);
		output.setText("Task id: "+getTaskId());
		// Prima modalità di Intent Esplicito
		Button startButton1 = (Button) findViewById(R.id.startButton1);
		startButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo l'Intent
				Intent intent = new Intent(NewTaskTestActivity.this,
						SecondActivity.class);
				// La lanciamo
				startActivity(intent);
			}

		});
		// Seconda modalità di Intent Esplicito
		Button startButton2 = (Button) findViewById(R.id.startButton2);
		startButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo l'Intent
				Intent intent = new Intent();
				ComponentName component = new ComponentName(
						NewTaskTestActivity.this, SecondActivity.class);
				intent.setComponent(component);
				// La lanciamo
				startActivity(intent);
			}

		});
		// Utilizzo del flag New_Task
		Button newTaskButton = (Button) findViewById(R.id.newTaskButton);
		newTaskButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Creiamo l'Intent
				Intent intent = new Intent(NewTaskTestActivity.this,
						SecondActivity.class);
				// Impostiamo il flag
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// La lanciamo
				startActivity(intent);
			}

		});		
	}
}
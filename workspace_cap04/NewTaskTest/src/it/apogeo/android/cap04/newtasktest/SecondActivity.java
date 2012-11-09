package it.apogeo.android.cap04.newtasktest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author MASSIMO
 *
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView output = (TextView)findViewById(R.id.output);
		output.setText("Task id: "+getTaskId());		
	}
	
	

}

package it.apogeo.android.cap04.lifecycleactivitytest;


import android.os.Bundle;
import android.widget.TextView;

/**
 * Semplice attivita' che viene lanciata a seguito della selezione del pulsante
 * nella schermata della prima attivita' 
 * 
 * @author Massimo Carli
 *
 */
public class SecondActivity extends LifecycleActivityTestActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        TextView output = (TextView)findViewById(R.id.output);
        String text = getResources().getString(R.string.second_label);
        output.setText(text);
	}
	
    protected String getActivityName(){
    	return "SECOND ACTIVITY";
    }	
	

}

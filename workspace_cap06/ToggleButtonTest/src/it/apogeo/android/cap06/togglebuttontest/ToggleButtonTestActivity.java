package it.apogeo.android.cap06.togglebuttontest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ToggleButtonTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Riferimento al container
        final LinearLayout container = (LinearLayout)findViewById(R.id.container);
        // Otteniamo il riferimento al ToggleButton
        ToggleButton toggle = (ToggleButton)findViewById(R.id.toggleButton);
        // Registriamo un listener per lo stato
        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				if(checked){
					// Colore giallo
					container.setBackgroundColor(Color.YELLOW);
				}else{
					// Colore blue
					container.setBackgroundColor(Color.BLUE);
				}
			}
        	
        });
    }
}
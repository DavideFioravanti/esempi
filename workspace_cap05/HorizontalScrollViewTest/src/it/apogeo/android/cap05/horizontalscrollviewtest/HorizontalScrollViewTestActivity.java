package it.apogeo.android.cap05.horizontalscrollviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class HorizontalScrollViewTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setContentView(R.layout.main);
        // Otteniamo il riferimento al LinearLayout
        ViewGroup layout = (ViewGroup)findViewById(R.id.linearLayout);
        // Aggiungiamo 20 Button
        for(int i =0;i<20;i++){
        	Button button = new Button(this);
        	button.setText("Button_"+i);
        	// Lo aggiungiamo al layout
        	layout.addView(button);
        }        
    }
}
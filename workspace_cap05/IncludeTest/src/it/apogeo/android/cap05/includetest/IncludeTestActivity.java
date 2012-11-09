package it.apogeo.android.cap05.includetest;

import android.app.Activity;
import android.os.Bundle;

public class IncludeTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Utilizzo classico
        //setContentView(R.layout.main);
        // Utilizzo template con include
        setContentView(R.layout.main_with_include);        
    }
}
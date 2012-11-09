package it.apogeo.android.cap05.compoundviewtest;

import android.app.Activity;
import android.os.Bundle;

public class CompoundViewTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Caso di utilizzo del solo XML main.xml
        //setContentView(R.layout.main);
        // Caso di utilizzo di una CompoundView
        setContentView(R.layout.main_compound);
    }
}
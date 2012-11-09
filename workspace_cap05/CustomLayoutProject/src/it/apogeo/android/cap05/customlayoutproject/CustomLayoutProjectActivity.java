package it.apogeo.android.cap05.customlayoutproject;

import android.app.Activity;
import android.os.Bundle;

public class CustomLayoutProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycustomlayout);
    }
}
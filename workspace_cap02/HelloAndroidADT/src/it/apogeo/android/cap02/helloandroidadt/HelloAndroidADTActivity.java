package it.apogeo.android.cap02.helloandroidadt;

import android.app.Activity;
import android.os.Bundle;

public class HelloAndroidADTActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
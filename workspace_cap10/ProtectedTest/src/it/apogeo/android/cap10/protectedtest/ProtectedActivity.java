package it.apogeo.android.cap10.protectedtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProtectedActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Modifichiamo la label
        TextView tw = (TextView)findViewById(R.id.outputText);
        tw.setText(R.string.protected_text);
    }
}
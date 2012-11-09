package it.apogeo.android.cap03.animationtrawabletest;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;

public class AnimationDrawableTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*
        // Otteniamo il riferimento al Button
        Button animatedButton = (Button)findViewById(R.id.animatedButton);
        // Otteniamo il riferimento all'animazione associata al Button
        AnimationDrawable animDrawable  = (AnimationDrawable)animatedButton.getBackground();
        // Avviamo la animazione
        animDrawable .start();
        */
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // Otteniamo il riferimento al Button
        Button animatedButton = (Button)findViewById(R.id.animatedButton);
        // Otteniamo il riferimento all'animazione associata al Button
        AnimationDrawable animDrawable  = (AnimationDrawable)animatedButton.getBackground();
        if (hasFocus) {
        	animDrawable .start();
        } else {
        	animDrawable .stop();
        }
    }
    
}
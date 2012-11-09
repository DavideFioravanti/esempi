package it.apogeo.android.cap03.assetstest;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AssetsTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Per ottenere il riferimento all'Asset utilizziamo l'oggetto
        // di tipo AssetManager a cui accediamo attraverso le Resources 
        AssetManager assetManager = getResources().getAssets();
        // Otteniamo il riferimento all'oggetto relativo ai font
        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/free_font.ttf");
        // Otteniamo il riferimento alla TexView
        TextView textView=(TextView)findViewById(R.id.output); 
        // Assegnamo il font alla textView
        textView.setTypeface(typeface);
    }
}
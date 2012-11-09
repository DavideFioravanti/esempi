package it.apogeo.android.cap03.bitmaptest;

import android.app.Activity;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

public class BitMapTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View backgroundView = findViewById(R.id.layout);
        BitmapDrawable bgImage = (BitmapDrawable) this.getResources().getDrawable(R.drawable.tiles);
        bgImage.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        backgroundView.setBackgroundDrawable(bgImage);
    }
}
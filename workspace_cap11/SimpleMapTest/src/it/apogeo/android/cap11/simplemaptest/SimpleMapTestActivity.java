package it.apogeo.android.cap11.simplemaptest;

import android.os.Bundle;
import android.view.Window;

import com.google.android.maps.MapActivity;

public class SimpleMapTestActivity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mettiamo la View a tutto schermo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Visualizziamo la Map
        setContentView(R.layout.main);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
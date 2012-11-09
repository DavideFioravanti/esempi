package it.apogeo.android.cap07.cameraanimationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class CameraAnimationTestActivity extends Activity {
	// Riferimento all'animazione corrente
	private Animation animation;
	// Riferimento alla GridView
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Impostiamo il layout
		setContentView(R.layout.main);
		// Otteniamo l'insieme di dati da visualizzare
		String[] data = getResources().getStringArray(R.array.months);
		// Creiamo un adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		// Otteniamo il riferimento alla GridView
		gridView = (GridView) findViewById(R.id.animatedView);
		// Impostiamo l'adapter
		gridView.setAdapter(adapter);
		// Otteniamo il riferimento all'animazione
		animation = new CameraAnimation();
	}

	public void startAnimation(View srcView) {
		gridView.startAnimation(animation);
	}
}
package it.apogeo.android.cap06.imageviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;

public class ImageViewTestActivity extends Activity {
	// Tag per il log
	private final static String TAG = "ImageViewTestActivity";
	
	// Valore di default per lo ScaleType
	private final static ScaleType DEFAULT_SCALE_TYPE = ScaleType.FIT_XY;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Riferimento alla ImageView
		final ImageView imageView = (ImageView)findViewById(R.id.imageView);
		// Otteniamo il riferimento all'insieme di valori di scale type
		final String[] scaleLabels = getResources().getStringArray(R.array.scale_types);
		// Creiamo un Adapter da utilizzare per lo spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,scaleLabels);
		// Otteniamo il riferimento allo Spinner
		Spinner spinner = (Spinner)findViewById(R.id.scaleTypeSpinner);
		// Ci registriamo per gestire gli eventi di selezione
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
				// Lo ScaleType è una enum java per cui dal nome si riesce
				// ad ottenere il corrispondente valore
				String scaleTypeLabel = scaleLabels[position];
				// Otteniamo il corrispondente valore
				ScaleType scaleType = ScaleType.valueOf(scaleTypeLabel);
				// Lo impostiamo alla ImageView
				imageView.setScaleType(scaleType);
				Log.i(TAG,"New ScaleType is "+scaleType);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Associamo lo scale di default
				imageView.setScaleType(DEFAULT_SCALE_TYPE);
				Log.i(TAG,"Default ScaleType is "+DEFAULT_SCALE_TYPE);
				
			}
			
		});
		// Lo associamo allo spinner
		spinner.setAdapter(adapter);
		// Assegnamo il tipo di default
		imageView.setScaleType(DEFAULT_SCALE_TYPE);

	}
}
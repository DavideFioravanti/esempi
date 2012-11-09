package it.apogeo.android.cap05.spinnertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerTestActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Definiamo un SimpleAdapter
		ArrayAdapter<String> adapter = createSpinnerAdapter();
		// Associamo il layout del dropdown
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Otteniamo il riferimento allo Spinner
		Spinner spinner = (Spinner) findViewById(R.id.mySpinner);
		// Associamo l'adapter alla Gallery
		spinner.setAdapter(adapter);
	}

	/*
	 * Metodo di utilità che permette di creare un SimpleAdapter
	 */
	private ArrayAdapter<String> createSpinnerAdapter() {
		// Inizializziamo i dati
		String[] data = getResources().getStringArray(R.array.months);
		// Creiamo l'adapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data);
		// Lo ritorniamo
		return arrayAdapter;
	}	
	
	
}
package it.apogeo.android.cap05.arrayadaptertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Impostiamo il Layout
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla ListView
		ListView listView = (ListView) findViewById(R.id.arrayList);
		// Otteniamo il riferimento all'array dei dati
		String[] arrayData = getResources().getStringArray(R.array.array_data); 
		// Creiamo l'ArrayAdapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.rowText,arrayData);
		//ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayData);
		// Lo impostiamo come adapter della listView
		listView.setAdapter(arrayAdapter);
	}
}
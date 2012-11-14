package it.apogeo.android.cap05.simplelistactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SimpleListActivityActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        // Impostiamo il layout principale (commentato nel caso di default)
        //setContentView(R.layout.main);
		//Possiamo non inserire il layout di default perchè c'è setListAdapter
        // Otteniamo il riferimento all'array dei dati
		String[] arrayData = getResources().getStringArray(R.array.array_data); 
		// Creiamo l'ArrayAdapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayData);
		// Lo impostiamo alla lista
		setListAdapter(arrayAdapter);
    }
}
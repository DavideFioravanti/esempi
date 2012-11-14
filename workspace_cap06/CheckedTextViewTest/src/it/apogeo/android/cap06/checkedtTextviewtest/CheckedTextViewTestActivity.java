package it.apogeo.android.cap06.checkedtTextviewtest;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

//Le CheckedBox non lasciano lo stato salvato nelle view. Se io salvo a mano
//lo stato e lo salvo nelle View non va bene... questo è per separare i dati
//dalla grafica

public class CheckedTextViewTestActivity extends ListActivity {
	// Tag per il log
	private final static String LOG_TAG = "CheckedTextViewTest";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Otteniamo il riferimento ai diversi valori da rappresentare
        String[] values = getResources().getStringArray(R.array.months);
        // Creiamo un ListAdapter attraverso un ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,values){

			/* (non-Javadoc)
			 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
			 */
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Mettiamo un semplice log per indicare se si tratta di una 
				// CheckedTextView o meno
				// RIchiamo la superclasse per gestire le View
				View view =super.getView(position, convertView, parent); 
				// Controlla se le View sono checkedView (non se è cliccata o no)
				boolean typeTest = (view instanceof CheckedTextView);
				Log.i(LOG_TAG, "Is a CheckedTextView? "+typeTest);
				return view;
			}
        	
        	
        	
        };
        // Impostiamo la modalità di selezione singola
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Impostiamo la modalità di selezione multipla
        //getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // Impostiamo l'array come Adapter della ListView
        setListAdapter(adapter);
    }
	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		CheckedTextView ctView = (CheckedTextView)v;
		Log.i(LOG_TAG, "Checked: "+ctView.isChecked()+" pos:"+position+" id:"+id);
	}
    
    
}
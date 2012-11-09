package it.apogeo.android.cap05.bettercompounddrawabletest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BetterCompoundDrawableTestActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Otteniamo il riferimento all'array dei dati
		String[] arrayData = getResources().getStringArray(R.array.array_data); 
		// Creiamo l'ArrayAdapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1, arrayData) {
			
			public View getView(int position, View convertView, ViewGroup parent) {
				// Otteniamo la View come fornita dall'Adapter
				TextView adapterView = (TextView)super.getView(position, convertView, parent);
				// Sistemiamo l'immagine a seconda che l'indice sia pari o dispari
				if((position%2)==0){
					// Caso di indice pari
					adapterView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.blue_circle, 0, 0, 0);
				}else{
					// Caso di indice dispari
					adapterView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pink_circle, 0, 0, 0);
				}				
				// Ritorniamo la View
				return adapterView;
			}
			

		};
		// Lo impostiamo alla lista
		setListAdapter(arrayAdapter);           
    }
}
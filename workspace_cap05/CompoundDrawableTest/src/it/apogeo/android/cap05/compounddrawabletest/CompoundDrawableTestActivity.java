package it.apogeo.android.cap05.compounddrawabletest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class CompoundDrawableTestActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Otteniamo il riferimento all'array dei dati
		String[] arrayData = getResources().getStringArray(R.array.array_data); 
		// Creiamo l'ArrayAdapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this, R.layout.simple_row, R.id.labelItem, arrayData) {
			

			public View getView(int position, View convertView, ViewGroup parent) {
				// Otteniamo la View come fornita dall'Adapter
				View adapterView = super.getView(position, convertView, parent);
				// Otteniamo il riferimento all'immagine
				ImageView imageItem = (ImageView)adapterView.findViewById(R.id.imageItem);
				// Sistemiamo l'immagine a seconda che l'indice sia pari o dispari
				if((position%2)==0){
					// Caso di indice pari
					imageItem.setImageResource(R.drawable.blue_circle);
				}else{
					// Caso di indice dispari
					imageItem.setImageResource(R.drawable.pink_circle);
				}
				// Ritorniamo la View
				return adapterView;
			}
	
			

		};
		// Lo impostiamo alla lista
		setListAdapter(arrayAdapter);        
    }
}
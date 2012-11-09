package it.apogeo.android.cap05.gridviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class GridViewTestActivity extends Activity {
	
	private final static String FIRSTNAME_KEY = "firstname";
	private final static String LASTNAME_KEY = "lastname";	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Impostiamo il layout principale
		setContentView(R.layout.main);
		// Inizializziamo la struttura di oggetti
		List<? extends Map<String,?>> listData = createItems();
		// Definiamo le chiavi da rappresentare
		String[] keys = new String[]{FIRSTNAME_KEY,LASTNAME_KEY};
		// Creiamo l'array delle View associate
		int[] views = new int[]{R.id.firstname,R.id.lastname};
		// Creiamo il SImpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listData,R.layout.custom_row,keys,views);
		// Otteniamo il riferimento alla ListView
		GridView gridView = (GridView) findViewById(R.id.gridView);
		// Associamo l'adapter alla View
		gridView.setAdapter(simpleAdapter);
    }
    
	private List<? extends Map<String,?>> createItems() {
		ArrayList<Map<String,String>> lista = new ArrayList<Map<String,String>>();
		for(int i=0;i<19;i++){
			Map<String,String> data = new HashMap<String,String>();
			data.put(FIRSTNAME_KEY,"FN_"+i);
			data.put(LASTNAME_KEY,"LN_"+i);
			lista.add(data);
		}
		return lista;
	}    
}
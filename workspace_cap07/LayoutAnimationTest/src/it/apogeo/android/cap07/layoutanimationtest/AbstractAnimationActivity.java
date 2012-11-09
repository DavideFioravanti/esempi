/**
 * 
 */
package it.apogeo.android.cap07.layoutanimationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * @author MASSIMO
 *
 */
public abstract class AbstractAnimationActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Impostiamo il layout
		setContentView(getLayoutId());
		// Otteniamo l'insieme di dati da visualizzare
		String[] data = getResources().getStringArray(R.array.months);
		// Creiamo un adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		// Otteniamo il riferimento alla GridView
		GridView gridView = (GridView) findViewById(R.id.animatedView);
		// Impostiamo l'adapter
		gridView.setAdapter(adapter);
	}
	
	/**
	 * 
	 * @return L'identificatore del layout da utilizzare
	 */
	public abstract int getLayoutId();
	
}

package it.apogeo.android.cap13.multipleactionfragmentproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Fragment che permette la visualizzazione di un insieme di elementi tra cui
 * successivamente scegliere la visualizzazione del dettaglio
 * 
 * @author Massimo Carli
 *
 */
public class ItemListFragment extends Fragment {
	
	/*
	 * TAG per il Log
	 */
	private final static String LOG_TAG = "MultipleActionFragmentProjectActivity";
	
	/*
	 * Chiavi per le informazioni di inizializzazione/stato nel Fragment
	 */
	private final static String MONTHS_INIT_PARAM = "MONTHS_INIT_PARAM";		
	
	/*
	 * Chiavi per le informazioni di stato nel Fragment
	 */
	private final static String SELECTED_INDEX_PARAM = "SELECTED_INDEX_PARAM";	
	
	/*
	 * Indice dell'elemento selezionato
	 */
	private int selectedIndex;
	
	/*
	 * Riferimento all'Activity a cui questo Fragment e' legato
	 */
	private MultipleActionFragmentProjectActivity myActivity;
	
	
	/**
	 * Metodo statico di factory che permette di ottenere un riferimento ad
	 * un Fragment con le opzioni di visualizzazione
	 *  
	 * @return Il Fragment associato all'elenco di opzioni
	 */
	public static ItemListFragment getInstance(String[] months){
		// Creiamo il Fragment
		ItemListFragment fragment = new ItemListFragment();
		// Salviamo il riferimento all'insieme dei mesi da visualizzare
		Bundle initState = new Bundle();
		initState.putStringArray(MONTHS_INIT_PARAM, months);
		fragment.setArguments(initState);
		// Abilitiamo il menu delle opzioni per l'ActionBar
		fragment.setHasOptionsMenu(true);
		// Ritorniamo il Fragment
		return fragment;
	}	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Salviamo il riferimento alla particolare Activity
		this.myActivity = (MultipleActionFragmentProjectActivity)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreateView");
		// Creiamo l'interfaccia relativa alla ListView che andiamo quindi a definire attraverso
		// un layout
		View fragmentView = inflater.inflate(R.layout.months_list, container, false);
		// Otteniamo il riferimento alla ListView
		ListView listView = (ListView)fragmentView.findViewById(R.id.months_list_view);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// Creiamo l'Adapter con i dati
		String[] months = getArguments().getStringArray(MONTHS_INIT_PARAM);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, months);
		// Impostiamo l'Adapter alla View
		listView.setAdapter(adapter);
		// Gestiamo gli eventi della ListView
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,long id) {
				// Una volta selezionato un elemento mostriamo il corrispondente Fragment nella
				// parte destra del layout identificata dal FrameLayout di id anchor_layout
				myActivity.showSelectedItem(position);
				// Aggiorniamo l'indice selezionato
				selectedIndex = position;
			}
			
		});
		// Gestiamo lo stato dell'elemento selezionato
		if(savedInstanceState!=null){
			selectedIndex = savedInstanceState.getInt(SELECTED_INDEX_PARAM, 0);	
		}
		// In ogni caso visualizziamo il corrispondente elemento sulla parte destra
		myActivity.showSelectedItem(selectedIndex);
		return fragmentView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_INDEX_PARAM, selectedIndex);
	}	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.frag_list_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Gestiamo la selezione delle corrispondenti opzioni
		Toast toast = Toast.makeText(getActivity(), "IN FRAGMENT LIST "+item.getTitle().toString(),
				Toast.LENGTH_SHORT);
		toast.show();
		return true;
	} 	

}

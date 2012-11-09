package it.apogeo.android.cap13.listdfragmentproject;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * ListFragment che permette la visualizzazione di un insieme di elementi tra cui
 * successivamente scegliere la visualizzazione del dettaglio
 * 
 * @author Massimo Carli
 *
 */
public class ItemListFragment extends ListFragment {
	
	/*
	 * TAG per il Log
	 */
	private final static String LOG_TAG = "ListFragmentProject";
	
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
	private ListFragmentProjectActivity myActivity;
	
	
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
		// Ritorniamo il Fragment
		return fragment;
	}	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Salviamo il riferimento alla particolare Activity
		this.myActivity = (ListFragmentProjectActivity)activity;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Impostiamo l'adapter alla lista
		String[] months = getArguments().getStringArray(MONTHS_INIT_PARAM);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, months);
		setListAdapter(adapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// Gestiamo lo stato dell'elemento selezionato
		if(savedInstanceState!=null){
			selectedIndex = savedInstanceState.getInt(SELECTED_INDEX_PARAM, 0);	
		}
		// In ogni caso visualizziamo il corrispondente elemento sulla parte destra
		myActivity.showSelectedItem(selectedIndex);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Una volta selezionato un elemento mostriamo il corrispondente Fragment nella
		// parte destra del layout identificata dal FrameLayout di id anchor_layout
		myActivity.showSelectedItem(position);
		// Aggiorniamo l'indice selezionato
		selectedIndex = position;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_INDEX_PARAM, selectedIndex);
	}	

}

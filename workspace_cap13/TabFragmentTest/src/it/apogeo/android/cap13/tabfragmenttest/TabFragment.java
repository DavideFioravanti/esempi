package it.apogeo.android.cap13.tabfragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * Classe che descrive un Fragment con una valore testuale
 * 
 * @author Massimo Carli
 *
 */
public class TabFragment extends Fragment {
	
	/*
	 * Valore del tag per il log
	 */
	private final static String LOG_TAG = "TabFragment";

	/*
	 * Chiavi per le informazioni di inizializzazione nel Fragment
	 */
	private final static String LABEL_INIT_PARAM = "LABEL_INIT_PARAM";
	

	/**
	 * Metodo statico di factory che permette di ottenere un riferimento ad
	 * un Fragment per la visualizzazione di una Label
	 * 
	 * @param label La label associata
	 * @return Il Fragment associato al mese passato
	 */
	public static TabFragment getInstance(String label){
		// Creiamo il Fragment
		TabFragment fragment = new TabFragment();
		// Salviamo le informazioni di inizializzazione
		Bundle bundle = new Bundle();
		bundle.putString(LABEL_INIT_PARAM, label);
		fragment.setArguments(bundle);
		Log.i(LOG_TAG, "TabFragment created");
		// Ritorniamo il Fragment
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Creiamo una TextView per la visualizzazione della label
		TextView labelTextView = new TextView(getActivity());
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		labelTextView.setLayoutParams(lp);
		// Leggiamo la label e la assegnamo
		labelTextView.setText(getArguments().getString(LABEL_INIT_PARAM));
		Log.i(LOG_TAG, "TabFragment View created");
		// Ritorniamo la View
		return labelTextView;
	}
	
}

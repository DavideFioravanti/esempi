package it.apogeo.android.cap13.multiplefragmentproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Classe che descrive un primo livello di dettaglio
 * 
 * @author Massimo Carli
 *
 */
public class FirstDetailFragment extends Fragment {
	
	/*
	 * Valore del tag per il log
	 */
	private final static String LOG_TAG = "FirstDetailFragment";
	
	/*
	 * Chiavi per le informazioni di inizializzazione/stato nel Fragment
	 */
	private final static String SELECTED_INDEX_PARAM = "SELECTED_INDEX_PARAM";		
	
	
	/*
	 * Chiavi per le informazioni di inizializzazione nel Fragment
	 */
	private final static String MONTH_INIT_PARAM = "MONTH_INIT_PARAM";
	private final static String LABEL_INIT_PARAM = "LABEL_INIT_PARAM";
	
	
	/*
	 * Riferimento all'Activity a cui questo Fragment e' legato
	 */
	private MultipleFragmentProjectActivity myActivity;
	
	/*
	 * Indice dell'elemento selezionato
	 */
	private int selectedIndex;	
	
	/*
	 * La label del Fragment
	 */
	private String label;
	
	/**
	 * Metodo statico di factory che permette di ottenere un riferimento ad
	 * un Fragment con le informazioni di primo dettaglio 
	 * 
	 * @param selectedIndex Indice elemento selezionato
	 * @param month Il mese di riferimento
	 * @param label La label associata
	 * @return Il Fragment associato al mese passato
	 */
	public static FirstDetailFragment getInstance(int selectedIndex, String month, String label){
		// Creiamo il Fragment
		FirstDetailFragment fragment = new FirstDetailFragment();
		// Salviamo le informazioni di inizializzazione
		Bundle bundle = new Bundle();
		bundle.putString(MONTH_INIT_PARAM, month);
		bundle.putString(LABEL_INIT_PARAM, label);
		bundle.putInt(SELECTED_INDEX_PARAM, selectedIndex);
		fragment.setArguments(bundle);
		Log.i(LOG_TAG, "First Fragment created");
		// Ritorniamo il Fragment
		return fragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Salviamo il riferimento alla particolare Activity
		this.myActivity = (MultipleFragmentProjectActivity)activity;
	}	
	
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		TypedArray values = activity.obtainStyledAttributes(attrs, R.styleable.FirstFragment);
		label = values.getString(R.styleable.FirstFragment_first_label);
		values.recycle();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Creiamo la View associata a partire dal documento di layout first.xml
		View firstView = inflater.inflate(R.layout.first, container, false);
		// Otteniamo il riferimento alla TextView per la label
		TextView labelTextView = (TextView)firstView.findViewById(R.id.label_text_view);
		TextView monthTextView = (TextView)firstView.findViewById(R.id.month_text_view);
		// Visualizziamo i corrispondenti valori
		labelTextView.setText(getArguments().getString(LABEL_INIT_PARAM));
		monthTextView.setText(getArguments().getString(MONTH_INIT_PARAM));
		// Otteniamo lo stato relativo all'indice selezionato
		if(savedInstanceState!=null){
			// Se e' stato salvato uno stato prendiamo quel valore
			selectedIndex = savedInstanceState.getInt(SELECTED_INDEX_PARAM,0);
		}else{
			// Altrimenti prendiamo il valore assegnato in fase di inizializzazione
			selectedIndex = getArguments().getInt(SELECTED_INDEX_PARAM,0);
		}
		// Gestiamo la pressione del Button per l'ulteriore dettaglio
		Button button = (Button)firstView.findViewById(R.id.show_detail_button);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View button) {
				myActivity.showNextDetail(selectedIndex);
			}
			
		});
		Log.i(LOG_TAG, "First View created");
		// Ritorniamo la View
		return firstView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_INDEX_PARAM, selectedIndex);
	}

}

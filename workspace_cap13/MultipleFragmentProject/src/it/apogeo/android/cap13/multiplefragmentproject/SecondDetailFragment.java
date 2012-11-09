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
 * Classe che descrive un secondo dettaglio di visualizzazione
 * 
 * @author Massimo Carli
 *
 */
public class SecondDetailFragment extends Fragment {
	
	/*
	 * Valore del tag per il log
	 */
	private final static String LOG_TAG = "SecondDetailFragment";
	
	
	/*
	 * Chiavi per le informazioni di inizializzazione nel Fragment
	 */
	private final static String MONTH_INIT_PARAM = "MONTH_INIT_PARAM";
	private final static String LABEL_INIT_PARAM = "LABEL_INIT_PARAM";
	
	/*
	 * La label del Fragment
	 */
	private String label;	
	
	
	/**
	 * Metodo statico di factory che permette di ottenere un riferimento ad
	 * un Fragment con le informazioni di primo dettaglio 
	 *  
	 * @param month Il mese di riferimento
	 * @param label La label associata
	 * @return Il Fragment associato al mese passato
	 */
	public static SecondDetailFragment getInstance(String month, String label){
		// Creiamo il Fragment
		SecondDetailFragment fragment = new SecondDetailFragment();
		// Salviamo le informazioni di inizializzazione
		Bundle bundle = new Bundle();
		bundle.putString(MONTH_INIT_PARAM, month);
		bundle.putString(LABEL_INIT_PARAM, label);
		fragment.setArguments(bundle);
		Log.i(LOG_TAG, "Second Fragment created");
		// Ritorniamo il Fragment
		return fragment;
	}
	
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		TypedArray values = activity.obtainStyledAttributes(attrs, R.styleable.SecondFragment);
		label = values.getString(R.styleable.SecondFragment_second_label);
		values.recycle();
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Creiamo la View associata a partire dal documento di layout second.xml
		View secondView = inflater.inflate(R.layout.second, container, false);
		// Otteniamo il riferimento alla TextView per la label
		TextView labelTextView = (TextView)secondView.findViewById(R.id.label_text_view);
		TextView monthTextView = (TextView)secondView.findViewById(R.id.month_text_view);
		// Visualizziamo i corrispondenti valori
		labelTextView.setText(getArguments().getString(LABEL_INIT_PARAM));
		monthTextView.setText(getArguments().getString(MONTH_INIT_PARAM));
		Log.i(LOG_TAG, "Second View created");
		// Ritorniamo la View
		return secondView;
	}
	
}

package it.apogeo.android.cap13.firstfragmentproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Creazione di un Fragment che permette di descrivere il ciclo di vita a cui
 * lo stesso viene sottoposto
 *  
 * @author Massimo Carli
 *
 */
public class SecondFragment extends FirstFragment {

	/*
	 * Nome della proprieta' di inizializzazione
	 */
	private final static String SECOND_INIT_PARAM = "SecondFragmentInitParam";
	
	
	/**
	 * Static Factory Method che ci permette di creare un SecondFragment
	 * impostando i parametri di inizializzazione rappresentati dall'intero
	 * passato come parametro
	 * 
	 * @param counter Il contatore che il Fragment visualizzera'
	 * @return Il riferimento al SecondFragment creato
	 */
	public static SecondFragment getFirstFragment(int counter){
		// Creiamo l'istanza di Fragment
		SecondFragment secondFragment = new SecondFragment();
		// Creiamo il Bundle per i valori di inizializzazione che nel 
		// nostro esempio sono rappresentati dal titolo che visualizzeremo
		// nel Fragment stesso
		Bundle initBundle = new Bundle();
		initBundle.putInt(SECOND_INIT_PARAM, counter);
		// Impostiamo il bundle come quello di inizializzazione
		secondFragment.setArguments(initBundle);
		// Ritorniamo l'istanza create
		return secondFragment;
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i(LOG_TAG, getFragmentName()+" onCreateView ");
		// Creiamo la View da visualizzare
		LinearLayout secondView = new LinearLayout(getActivity());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		secondView.setLayoutParams(lp);
		// Creiamo le due TextView
		TextView secondLabel = new TextView(getActivity());
		secondLabel.setLayoutParams(lp);
		secondLabel.setText(R.string.second_fragment);
		TextView secondValue = new TextView(getActivity());
		secondValue.setLayoutParams(lp);
		// Le aggiungiamo
		secondView.addView(secondLabel);
		secondView.addView(secondValue);
		// Se presente nello stato visualizziamo il corrispondente valore
		if(savedInstanceState!=null){
			String value = savedInstanceState.getString(SECOND_INIT_PARAM, "");
			secondValue.setText(value);
		}
		// Ritorniamo la View creata
		return secondView;
	}	
	
	/**
	 * @return Identifica il nome del Fragment
	 */
	protected String getFragmentName(){
		return "SECOND FRAGMENT";
	}
	
}

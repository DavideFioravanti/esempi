package it.apogeo.android.cap13.firstfragmentproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Esempio di implementazione di un Fragment che ci permettera' di visualizzare
 * le chiamate dei metodi di callback 
 * @author Massimo Carli
 *
 */
public class FirstFragment extends Fragment {
	
	/*
	 * TAG per il LOG
	 */
	protected final String LOG_TAG = "FragmentLifeCycle"; 
	
	/*
	 * Nome della proprieta' di inizializzazione
	 */
	private final static String FIRST_INIT_PARAM = "FirstFragmentInitParam";
	

	/**
	 * Static Factory Method che ci permette di creare un FirstFragment
	 * impostando i parametri di inizializzazione rappresentati dalla
	 * String passata come parametro
	 * 
	 * @param title Il titolo che il Fragment visualizzera'
	 * @return Il riferimento al FirstFragment creato
	 */
	public static FirstFragment getFirstFragment(String title){
		// Creiamo l'istanza di Fragment
		FirstFragment firstFragment = new FirstFragment();
		// Creiamo il Bundle per i valori di inizializzazione che nel 
		// nostro esempio sono rappresentati dal titolo che visualizzeremo
		// nel Fragment stesso
		Bundle initBundle = new Bundle();
		initBundle.putString(FIRST_INIT_PARAM, title);
		// Impostiamo il bundle come quello di inizializzazione
		firstFragment.setArguments(initBundle);
		// Ritorniamo l'istanza create
		return firstFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(LOG_TAG, getFragmentName()+" onAttach ");
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, getFragmentName()+" onCreate ");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i(LOG_TAG, getFragmentName()+" onCreateView ");
		// Creiamo la View da visualizzare
		View firstView = inflater.inflate(R.layout.first,container, false);
		// Otteniamo il riferimento alla TextView per l'output
		TextView firstValue = (TextView)firstView.findViewById(R.id.first_value);
		// Se presente nello stato visualizziamo il corrispondente valore
		if(savedInstanceState!=null){
			String value = savedInstanceState.getString(FIRST_INIT_PARAM, "");
			firstValue.setText(value);
		}
		// Ritorniamo la View creata
		return firstView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(LOG_TAG, getFragmentName()+" onActivityCreated ");		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.i(LOG_TAG, getFragmentName()+" onStart ");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i(LOG_TAG, getFragmentName()+" onResume ");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.i(LOG_TAG, getFragmentName()+" onPause ");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.i(LOG_TAG, getFragmentName()+" onStop ");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(LOG_TAG, getFragmentName()+" onDestroyView ");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(LOG_TAG, getFragmentName()+" onDestroy ");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(LOG_TAG, getFragmentName()+" onDetach ");
	}
	
	/**
	 * @return Identifica il nome del Fragment
	 */
	protected String getFragmentName(){
		return "FIRST FRAGMENT";
	}
	

}

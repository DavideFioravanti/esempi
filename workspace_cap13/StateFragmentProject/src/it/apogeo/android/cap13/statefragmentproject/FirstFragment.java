package it.apogeo.android.cap13.statefragmentproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
	protected final String LOG_TAG = "StateFragmentProject"; 
	
	/*
	 * Nome della proprieta' di inizializzazione
	 */
	private final static String FIRST_INIT_PARAM = "FirstFragmentInitParam";
	
	/*
	 * Nome della proprieta' di inizializzazione per il contatore
	 */
	private final static String COUNTER_INIT_PARAM = "CounterInitParam";
	
	/*
	 * Nome della proprieta' di salvataggio per il contatore
	 */
	private final static String COUNTER_STATE_PARAM = "CounterStateParam";	
	
	/*
	 * Riferimento alla TextView di output
	 */
	private TextView outputTextView;
	
	/*
	 * Button per l'incremento
	 */
	private Button addButton;
	
	/*
	 * Contatore associato al Fragment
	 */
	private int count;
	

	/**
	 * Static Factory Method che ci permette di creare un FirstFragment
	 * impostando i parametri di inizializzazione rappresentati dalla
	 * String passata come parametro
	 * 
	 * @param title Il titolo che il Fragment visualizzera'
	 * @param count Il valore del contatore
	 * @return Il riferimento al FirstFragment creato
	 */
	public static FirstFragment getFirstFragment(String title,int count){
		// Creiamo l'istanza di Fragment
		FirstFragment firstFragment = new FirstFragment();
		// Creiamo il Bundle per i valori di inizializzazione che nel 
		// nostro esempio sono rappresentati dal titolo che visualizzeremo
		// nel Fragment stesso
		Bundle initBundle = new Bundle();
		initBundle.putString(FIRST_INIT_PARAM, title);
		initBundle.putInt(COUNTER_INIT_PARAM, count);
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
	public void onInflate(Activity activity, AttributeSet attrs,Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		// Accediamo ai valori custom degli attributi
		TypedArray values = activity.obtainStyledAttributes(attrs, R.styleable.FirstFragment);
		String custom1 = values.getString(R.styleable.FirstFragment_custom1);
		int custom2 = values.getInt(R.styleable.FirstFragment_custom2, 0);
		values.recycle();
		Log.i(LOG_TAG, getFragmentName()+" onInflate custom1:"+custom1+" custom2:"+custom2);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, getFragmentName()+" onCreate ");
		// IL metodo setRetainInstance permette di gestire il fatto che il riavvio dell'Activity
		// contenitore non abbiamo come conseguenza il riavvio del Fragment stesso
		//setRetainInstance(true);
	}
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i(LOG_TAG, getFragmentName()+" onSaveInstanceState");
		// Salviamo il contenuto del contatore
		outState.putInt(COUNTER_STATE_PARAM, count);
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.i(LOG_TAG, getFragmentName()+" onCreateView ");
		// Creiamo la View da visualizzare
		View firstView = inflater.inflate(R.layout.first,container, false);
		// Otteniamo il riferimento alla TextView per l'output
		outputTextView = (TextView)firstView.findViewById(R.id.first_value);
		// Se presente nello stato visualizziamo il corrispondente valore
		if(savedInstanceState!=null){
			// Gestiamo lo stato del contatore
			count = savedInstanceState.getInt(COUNTER_STATE_PARAM,0);
			updateValue();
		}
		// Otteniamo il riferimento al Button
		addButton = (Button)firstView.findViewById(R.id.add_button);
		addButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				count++;
				updateValue();
			}
		});
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
	
	/*
	 * Metodo che aggiorna il contenuto del TextView del risultato in base
	 * al valore di count
	 */
	private void updateValue(){
		String counterString = getActivity().getResources().getString(R.string.first_value_format, count);
		outputTextView.setText(counterString);		
	}

}

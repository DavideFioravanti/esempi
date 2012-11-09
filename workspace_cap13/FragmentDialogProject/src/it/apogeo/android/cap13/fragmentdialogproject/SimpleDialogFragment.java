package it.apogeo.android.cap13.fragmentdialogproject;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * Esempio di implementazione di una DialogFragment per la visualizzazione di un 
 * messaggio 
 * 
 * @author Massimo Carli
 *
 */
public class SimpleDialogFragment extends DialogFragment {
	
	/*
	 * Tag per il log
	 */
	private final static String LOG_TAG = "SimpleDialogFragment";
	
	/*
	 * Chiave da utilizzare per lo stato iniziale
	 */
	private final static String MESSAGE_INIT_PARAM = "MESSAGE_INIT_PARAM";
	
	/*
	 * Riferimento all'Activity associata
	 */
	private FragmentDialogProjectActivity myActivity;	
	
	
	/**
	 * Permette la creazione di un SimpleDialogFragment per la visualizzazione
	 * di un messaggio 
	 * 
	 * @param message Messaggio da visualizzare nella Dialog
	 * @return Il riferimento alla DialogFragment da visualizzare
	 */
	public static SimpleDialogFragment getInstance(String message){
		// Creiamo l'istanza
		SimpleDialogFragment dialog = new SimpleDialogFragment();
		// Salviamo lo stato iniziale 
		Bundle initBundle = new Bundle();
		initBundle.putString(MESSAGE_INIT_PARAM, message);
		dialog.setArguments(initBundle);
		Log.i(LOG_TAG,"SimpleDialogFragment created with message "+message);
		// Ritorniamo l'istanza
		return dialog;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Creiamo una TextView
		TextView messageView = new TextView(getActivity());
		// Impostiamo le informazioni di layout
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		messageView.setLayoutParams(lp);
		// Impostiamo il testo
		String message = getArguments().getString(MESSAGE_INIT_PARAM);
		messageView.setText(message);
		Log.i(LOG_TAG,"onCreateView");
		// Ritorniamo la TextView
		return messageView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myActivity = (FragmentDialogProjectActivity)activity;
	}	
	
	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		myActivity.showToastMessage("Cancelled");
	}

}

package it.apogeo.android.cap13.fragmentdialogproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Esempio di implementazione di una DialogFragment per la visualizzazione di un 
 * messaggio e pulsante OK di conferma 
 * 
 * @author Massimo Carli
 *
 */
public class AlertDialogFragment extends DialogFragment {
	
	/*
	 * Tag per il log
	 */
	private final static String LOG_TAG = "AlertDialogFragment";
	
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
	public static AlertDialogFragment getInstance(String message){
		// Creiamo l'istanza
		AlertDialogFragment dialog = new AlertDialogFragment();
		// Salviamo lo stato iniziale 
		Bundle initBundle = new Bundle();
		initBundle.putString(MESSAGE_INIT_PARAM, message);
		dialog.setArguments(initBundle);
		Log.i(LOG_TAG,"AlertDialogFragment created with message "+message);
		// Ritorniamo l'istanza
		return dialog;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myActivity = (FragmentDialogProjectActivity)activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Otteniamo il riferimento al messaggio da visualizzare
        String message = getArguments().getString(MESSAGE_INIT_PARAM);
        // Creiamo la Dialog attraverso il Builder
        AlertDialog alertDialog =new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)
                .setTitle(message)
                .setPositiveButton(R.string.ok_message,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	// Visualizziamo il messaggio nel Toast
                        	myActivity.showToastMessage("OK");
                        	// Chiudiamo la dialog
                        	dismiss();
                        }
                    }
                )
                .create();
        Log.i(LOG_TAG,"onCreateDialog ");
        // Lo ritorniamo
        return alertDialog;
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		myActivity.showToastMessage("Cancelled");
	}	
	
}

package it.apogeo.android.cap13.fragmentdialogproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FragmentDialogProjectActivity extends Activity {
	
	/*
	 * Tag per il log
	 */
	private final static String LOG_TAG = "FragmentDialogProjectActivity";
	
	/*
	 * Tag per le Dialog
	 */
	private final static String DIALOG_TAG_VALUE = "DIALOG_TAG_VALUE";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /**
     * Questo metodo permette di visualizzare la DialogFragment corrispondente
     * al pulsante premuto
     *  
     * @param button Il riferimento al pulsante premuto
     */
    public void showDialog(View button){
    	// Otteniamo l'id del button
    	int buttonId = button.getId(); 
    	// Otteniamo il riferimento al FragmentManager
    	FragmentManager fm = getFragmentManager();
    	// visualizziamola Dialog corrispondente
    	switch (buttonId) {
		case R.id.simple_dialog_button:{
			// Utilizziamo il FragmentManager per visualizzare la Dialog
			// senza la creazione esplicita di una FragmentTransaction che
			// viene creata automaticamente. In questo caso il DialogFragment
			// non viene aggiunto al back stack
			SimpleDialogFragment simpleDialog = SimpleDialogFragment.getInstance("Dialog Semplice");
			simpleDialog.show(fm, DIALOG_TAG_VALUE);
		}
			break;
		case R.id.simple_dialog_back_button:{
			// In questo caso vogliamo aggiungere la DIalog al back stack per cui
			// abbiamo bisogno di avere la corrispondente FragmentTransaction
			FragmentTransaction ft = fm.beginTransaction();
			ft.addToBackStack(null);
			SimpleDialogFragment simpleDialog = SimpleDialogFragment.getInstance("Dialog Semplice con Back Stack");
			simpleDialog.show(ft, DIALOG_TAG_VALUE);
		}
			break;			
		case R.id.alert_dialog_button:{
			AlertDialogFragment alertDialog = AlertDialogFragment.getInstance("Alert Dialog");
			alertDialog.show(fm, DIALOG_TAG_VALUE);			
		}
			break;
		case R.id.alert_dialog_back_button:{
			FragmentTransaction ft = fm.beginTransaction();
			ft.addToBackStack(null);
			AlertDialogFragment alertDialog = AlertDialogFragment.getInstance("Alert Dialog con Back Stack");
			alertDialog.show(ft, DIALOG_TAG_VALUE);			
		}
			break;			
		default:
			break;
		}
    	
    }
    
    
    /**
     * Questo metodo permette solamente di visualizzare un toast di debug
     * 
     * @param message Messaggio da visualizzare attraverso il Toast
     */
    public void showToastMessage(String message){
    	Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    	toast.show();
    }
    
    
}
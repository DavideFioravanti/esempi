package it.apogeo.android.cap13.firstfragmentproject;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class FirstFragmentProjectActivity extends Activity {
	
	/*
	 * TAG per il LOG
	 */
	protected final String LOG_TAG = "FragmentLifeCycle"; 	
	
	/*
	 * Indica se e' visualizzata il secondo Fragment
	 */
	private boolean isSecondShown;
	
	/*
	 * Indica se dobbiamo aggiungere il precedente Fragment al back stack
	 */
	private boolean addToBackStack;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Inizialmente visualizziamo il primo Fragment
        FirstFragment firstFragment = FirstFragment.getFirstFragment("First title");
        getFragmentManager().beginTransaction().add(R.id.anchor_layout, firstFragment).commit();
    }
    
    
    /**
     * Invocato in corrispondenza della selezione del bottone toggle per l'aggiunta
     * al back stack
     * 
     * @param button Bottone toggle
     */
    public void toggleBackStack(View button){
    	ToggleButton toggleButton = (ToggleButton)button;
    	addToBackStack = toggleButton.isChecked();
    	Log.i(LOG_TAG,"addToBackStack value is "+addToBackStack);
    }
    
    /**
     * Permette di visualizzare il secondo Fragment nel caso non sia gia' visualizzato
     * 
     * @param button Bottone toggle
     */
    public void showNext(View button){
    	if(!isSecondShown){
    		Log.i(LOG_TAG,"go to second Fragment ");
    		SecondFragment secondFragment = SecondFragment.getFirstFragment(0);
    		FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.anchor_layout, secondFragment);
    		if(addToBackStack){
        		transaction.addToBackStack("goToSecondTransaction");	
        		Log.i(LOG_TAG," transaction added to back stack ");
    		}
    		transaction.commit();
    	}else{
    		// In questo caso non facciamo nulla
    		Log.w(LOG_TAG,"Already in the second Fragment. Go back! ");
    	}
    }    
}
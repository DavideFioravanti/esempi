package it.apogeo.android.cap13.actionbartest;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActionBarTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	ActionBar actionBar = getActionBar();
    	// Impostiamo l'opzione DisplayHomeAsUpEnabled
    	//actionBar.setDisplayHomeAsUpEnabled(true);        
    }
    
    /**
     * Invocato in corrispondenza della selezione del ToggleButton
     * @param v Il riferimento al ToggleButton
     */
    public void toggleActionBar(View v){
    	ToggleButton toggleButton = (ToggleButton)v;
    	ActionBar actionBar = getActionBar();
    	// Visualizziamo la ActionBar in base allo stato del Togglebutton
    	if(toggleButton.isChecked()){
    		actionBar.show();
    	}else{
    		actionBar.hide();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Creiamo il menu attraverso il MenuInflater
    	MenuInflater menuInflater = new MenuInflater(this);
    	menuInflater.inflate(R.menu.action_bar_menu, menu);
		SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
		// Utilizzo della serachView
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
    	if(item.getItemId()==android.R.id.home){
    		// Si tratta dell'icona dell'applicazione nell'ActionBar
        	Toast toast = Toast.makeText(this, "SELEZIONATA HOME", Toast.LENGTH_SHORT);
        	toast.show();    		
    	}else{
        	// Gestiamo la selezione delle corrispondenti opzioni
        	Toast toast = Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT);
        	toast.show();    		
    	}
    	return true;
    }    
}
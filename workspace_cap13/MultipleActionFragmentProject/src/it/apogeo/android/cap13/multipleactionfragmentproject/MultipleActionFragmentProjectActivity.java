package it.apogeo.android.cap13.multipleactionfragmentproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MultipleActionFragmentProjectActivity extends Activity {
	
	/*
	 * Valore del tag per il log
	 */
	private final static String LOG_TAG = "MultipleActionFragmentProjectActivity";
	
	/*
	 * Tag che identificano i vari Fragment
	 */
	private final static String LIST_FRAGMENT_TAG = "LIST_FRAGMENT_TAG";
	private final static String FIRST_DETAIL_FRAGMENT_TAG = "FIRST_DETAIL_FRAGMENT_TAG";
	private final static String SECOND_DETAIL_FRAGMENT_TAG = "SECOND_DETAIL_FRAGMENT_TAG";
	
	/*
	 * Nome della transazione nel back stack
	 */
	private final static String DETAIL_TRANSACTION_NAME = "DETAIL_TRANSACTION_NAME";
	
	/*
	 * Contiene il modello degli elementi della lista
	 */
	private String[] months;
	
	
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
		// Leggiamo le informazioni relative ai dati da visualizzare nella lista
		months = getResources().getStringArray(R.array.months);       
       // Otteniamo il riferimento al FragmentManager
       FragmentManager fm = getFragmentManager();		
       // Iniziamo la FragmentTransaction
       FragmentTransaction ft = fm.beginTransaction();
		// Una volta che conosciamo l'elemento selezionato verifichiamo lo stato dei
		// Fragment. Per fare questo verifichiamo la presenza del Fragment relativo all'elenco
		// nella parte sinistra. Se presente stiamo visualizzando il primo livello. In caso 
		// contrario siamo nel secondo dettaglio. Cerchiamo quindi il riferimento al Fragment
		// associato al TAG LIST e ne verifichiamo la visualizzazione
		ItemListFragment listFragment = (ItemListFragment)fm.findFragmentByTag(LIST_FRAGMENT_TAG);
		//Fragment otherFragment = fm.findFragmentById(R.id.anchor_left);
		if(listFragment==null){
			Log.i(LOG_TAG,"ItemListFragment is not present. We create it");
			// Se non presente significa che siamo all'inizio per cui lo dobbiamo aggiungere 
			// nella parte sinistra
			listFragment = ItemListFragment.getInstance(months);
			ft.add(R.id.anchor_left,listFragment,LIST_FRAGMENT_TAG);
			// E nella parte destra il dettaglio di primo livello
			FirstDetailFragment firstDetail = FirstDetailFragment.getInstance(0,months[0], "FIRST DETAIL");
			ft.add(R.id.anchor_layout,firstDetail,FIRST_DETAIL_FRAGMENT_TAG);
		}else{
			Log.i(LOG_TAG,"ItemListFragment already present.");
		}
       // Chiudiamo la transazione
       ft.commit();
   }
   
 
   /**
    * Metodo che permette di gestire la visualizzazione dell'elemento selezionato
    * 
    * @param selectedIndex Indice dell'elemento selezionato
    */
   public void showSelectedItem(int selectedIndex){
   	Log.i(LOG_TAG,"Show FirstDetail for index "+selectedIndex);
   	// Visualizziamo il corrispondente dettaglio
   	FragmentManager fm = getFragmentManager();
   	FragmentTransaction ft = fm.beginTransaction();
   	// Dobbiamo sostituire il Fragment sulla destra con quello relativo all'indice selezionato
		FirstDetailFragment firstDetail = FirstDetailFragment.getInstance(selectedIndex,months[selectedIndex], "FIRST DETAIL");
		ft.replace(R.id.anchor_layout,firstDetail,FIRST_DETAIL_FRAGMENT_TAG);
		// Eseguiamo il commit
		ft.commit();
   }
   
   /**
    * Permette di visualizzare il dettaglio di secondo livello dell'elemento associato all'indice
    * passato come parametro
    * 
    * @param selectedIndex Indice dell'elemento selezionato
    */
   public void showNextDetail(int selectedIndex){
   	Log.i(LOG_TAG,"Show SecondDetail for index "+selectedIndex);
   	FragmentManager fm = getFragmentManager();
   	FragmentTransaction ft = fm.beginTransaction();
   	// Dobbiamo sostituire il Fragment a sinistra con quello che prima era a destra. Non e'
   	// possibile spostare un Fragment da un container ad un altro per cui l'unica possibilita'
   	// e' quella di creane un altro uguale e di rimuovere il precedente
		FirstDetailFragment firstDetail = (FirstDetailFragment)fm.findFragmentByTag(FIRST_DETAIL_FRAGMENT_TAG);
		ft.remove(firstDetail);
		FirstDetailFragment newFirstDetail = FirstDetailFragment.getInstance(selectedIndex, months[selectedIndex], "FIRST DETAIL");
		ft.replace(R.id.anchor_left,newFirstDetail,FIRST_DETAIL_FRAGMENT_TAG);
		// Mettiamo quindi il secondo dettaglio  destra
		SecondDetailFragment secondDetail = SecondDetailFragment.getInstance(months[selectedIndex], "SECOND DETAIL");
		ft.replace(R.id.anchor_layout,secondDetail,SECOND_DETAIL_FRAGMENT_TAG);
		// Prima di eseguire il commit agggiungiamo la transazione allo back stack. 
		ft.addToBackStack(DETAIL_TRANSACTION_NAME);		
		ft.commit();    	
   }
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Creiamo il menu attraverso il MenuInflater
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			// Si tratta dell'icona dell'applicazione nell'ActionBar
			Toast toast = Toast.makeText(this, "SELEZIONATA HOME IN ACTIVITY",
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			// Gestiamo la selezione delle corrispondenti opzioni
			Toast toast = Toast.makeText(this, "IN ACTIVITY "+item.getTitle().toString(),
					Toast.LENGTH_SHORT);
			toast.show();
		}
		// Ritornando false la selezione di una Action viene valutata anche dal corrispondente
		// Fragment. Nel caso di ritorno true l'evento viene valutato solamente da questo
		// metodo dell'Activity
		// return false;
		return true;
	}  
}
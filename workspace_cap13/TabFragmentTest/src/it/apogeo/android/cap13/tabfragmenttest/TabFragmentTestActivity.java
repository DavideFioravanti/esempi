package it.apogeo.android.cap13.tabfragmenttest;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class TabFragmentTestActivity extends Activity {
	
	/*
	 * Chiave per il salvataggio dello stato del Tab
	 */
	private final static String SELECTED_TAG_INDEX_PARAM = "SELECTED_TAG_INDEX_PARAM";
	
	/**
	 * Implementazione di TabListener checi permette di gestire la selezione dei diversi Tab
	 * 
	 * @author Massimo Carli
	 *
	 */
	private class MyTabListener implements TabListener{
		
		/*
		 * Fragment associato ad un Tab
		 */
		private Fragment fragment;
		
		/**
		 * Crea un MyTabListener associato ad un particolare Tab
		 * @param fragment Riferimento al Fragment associato al Tag
		 */
		public MyTabListener(Fragment fragment){
			this.fragment= fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Questo metodo viene invocato nel caso in cui venga selezionato
			// nuovamente un elemento che e' gia' stato selezionato in precedenza
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Questo metodo viene invocato a seguito della selezione di un Tab
			// passato come parametro
			ft.add(R.id.anchor_container, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// Questo metodo viene invocato a seguito della deselezione di un Tab
			// passato come parametro. In questo caso rimuoviamo il Fragment
			ft.remove(fragment);
		}
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento all'ActionBar
        ActionBar actionBar = getActionBar();
        // Impostiamo la modalità di navigazione a tab
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Creiamo i vari Tab da aggiungere
        Tab tab1 = actionBar.newTab().setIcon(R.drawable.icon).setText("First Tab");
        Tab tab2 = actionBar.newTab().setIcon(R.drawable.icon).setText("Second Tab");
        Tab tab3 = actionBar.newTab().setIcon(R.drawable.icon).setText("Third Tab");
        // Creiamo i corrispondenti Fragment
        TabFragment frag1 = TabFragment.getInstance("FIRST TAB BODY");
        TabFragment frag2 = TabFragment.getInstance("SECOND TAB BODY");
        TabFragment frag3 = TabFragment.getInstance("THIRD TAB BODY");
        // Ascoltiamo i vari tab 
        tab1.setTabListener(new MyTabListener(frag1));
        tab2.setTabListener(new MyTabListener(frag2));
        tab3.setTabListener(new MyTabListener(frag3));
        // Aggiungiamo i tab all'ActionBar
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
        // Ripristino dello stato del tab
        int selectedTabIndex = 0;
		if(savedInstanceState!=null){
			selectedTabIndex = savedInstanceState.getInt(SELECTED_TAG_INDEX_PARAM, 0);
		}
        actionBar.setSelectedNavigationItem(selectedTabIndex);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	// Otteniamo il riferimento al tab selezionato
    	int selectedTabIndex = getActionBar().getSelectedNavigationIndex();
    	outState.putInt(SELECTED_TAG_INDEX_PARAM, selectedTabIndex);
    }
}
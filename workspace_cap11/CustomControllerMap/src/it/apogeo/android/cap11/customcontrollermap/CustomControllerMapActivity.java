package it.apogeo.android.cap11.customcontrollermap;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class CustomControllerMapActivity extends MapActivity {
	/*
	 * Tag del log
	 */
	private final static String LOG_TAG = "CustomControllerMapActivity";
	/*
	 * Location di Rovigo come location iniziale
	 */
	private final static GeoPoint ROVIGO_GEO_POINT = new GeoPoint(45066667,11783333);
	/*
	 * Location di Napoli (Università Parthenope) come location iniziale
	 */
	private final static GeoPoint NAPLE_GEO_POINT = new GeoPoint(40837335,14253308);	

	/*
	 * Location di New York
	 */
	private final static GeoPoint NEW_YORK_GEO_POINT = new GeoPoint(40698731,-74039268);	
	/*
	 * Riferimento alla MapView
	 */
	private MapView mapView;
	/*
	 * Riferimento al MapController
	 */
	private MapController mapController;	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mettiamo la View a tutto schermo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Visualizziamo la Map
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla MapView
        mapView = (MapView)findViewById(R.id.mapView);
        // Otteniamo il riferimento al controller
        mapController = mapView.getController();
        Log.i(LOG_TAG, "MapController obtained!");
        // Rendiamo la mappa cliccabile e quindi ne permettiamo
        // il pan
        mapView.setClickable(true);
    }
    
    /*
     * Permette di centrare la Map in una particolare location
     */
    public void centerMap(View Button){
    	Log.i(LOG_TAG, "centerMap");
    	// Otteniamo l'oggetto di tipo GeoPoint
    	mapController.setCenter(NAPLE_GEO_POINT);
    }
    
    /*
     * Permette di eseguire un zoomIn
     */
    public void zoomIn(View Button){
    	Log.i(LOG_TAG, "zoomIn");
    	// Eseguiamo uno zoomIn
    	mapController.zoomIn();
    }
    
    /*
     * Permette di eseguire un zoomout
     */
    public void zoomOut(View Button){
    	Log.i(LOG_TAG, "zoomOut");
    	// Eseguiamo uno zoomOut
    	mapController.zoomOut();
    }    
    
    public void animateToNewYork(View Button){
    	Log.i(LOG_TAG, "animateToLiberty");
    	// Eseguiamo una animazione verso la statua della libertà
    	mapController.animateTo(NEW_YORK_GEO_POINT);
    }     

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Menù di gestione modalità di visualizzazione che, per renderle checkable
		// le inseriamo in un sottomenu
		SubMenu mapSubMenu = menu.addSubMenu("Map Modes");
		mapSubMenu.setIcon(android.R.drawable.ic_menu_mapmode);
		int firstItem = Menu.FIRST;
		MenuItem trafficItem = mapSubMenu.add(1, firstItem, firstItem, "Traffic");
		trafficItem.setCheckable(true);
		trafficItem.setChecked(true);
		MenuItem satelliteItem = mapSubMenu.add(1, firstItem+1, firstItem+1, "Satellite");
		satelliteItem.setCheckable(true);
		MenuItem streetViewItem = mapSubMenu.add(1, firstItem+2, firstItem+2, "Street View");
		streetViewItem.setCheckable(true);
		// Visualizziamo il menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Modifichiamo lo stato di quella selezionata
		item.setChecked(!item.isChecked());
		// Abilitiamo o meno l'opzione relativa
		switch(item.getItemId()){
		case Menu.FIRST:
			mapView.setTraffic(item.isChecked());
			break;
		case Menu.FIRST+1:
			mapView.setSatellite(item.isChecked());
			break;
		case Menu.FIRST+2:
			mapView.setStreetView(item.isChecked());
			break;			
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
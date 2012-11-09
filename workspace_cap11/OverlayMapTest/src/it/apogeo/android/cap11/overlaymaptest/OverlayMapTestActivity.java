package it.apogeo.android.cap11.overlaymaptest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class OverlayMapTestActivity extends MapActivity {
	/*
	 * Tag del log
	 */
	private final static String LOG_TAG = "OverlayMapTestActivity";
	/*
	 * Location di Rovigo come location iniziale
	 */
	private final static GeoPoint ROVIGO_GEO_POINT = new GeoPoint(45066667,
			11783333);
	/*
	 * Riferimento alla MapView
	 */
	private MapView mapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Mettiamo la View a tutto schermo
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Visualizziamo la Map
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla MapView
		mapView = (MapView) findViewById(R.id.mapView);
		Log.i(LOG_TAG, "MapController obtained!");
		// Rendiamo la mappa cliccabile e quindi ne permettiamo
		// il pan
		mapView.setClickable(true);
		// Utiliziamo i controlli predefiniti per la gestione
		// delle operazioni di zoom
		mapView.setBuiltInZoomControls(true);
		// Visualizziamo il punto iniziale
		mapView.getController().setCenter(ROVIGO_GEO_POINT);
		mapView.getController().setZoom(9);
		mapView.setSatellite(true);
		// Gestiamo gli Overlay
		Drawable starImg = getResources().getDrawable(R.drawable.icon);
		VenetoCitiesOverlay overlays = new VenetoCitiesOverlay(starImg);
		mapView.getOverlays().add(overlays);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Menù di gestione modalità di visualizzazione che, per renderle
		// checkable
		// le inseriamo in un sottomenu
		SubMenu mapSubMenu = menu.addSubMenu("Map Modes");
		mapSubMenu.setIcon(android.R.drawable.ic_menu_mapmode);
		int firstItem = Menu.FIRST;
		MenuItem trafficItem = mapSubMenu.add(1, firstItem, firstItem,
				"Traffic");
		trafficItem.setCheckable(true);
		trafficItem.setChecked(true);
		MenuItem satelliteItem = mapSubMenu.add(1, firstItem + 1,
				firstItem + 1, "Satellite");
		satelliteItem.setCheckable(true);
		MenuItem streetViewItem = mapSubMenu.add(1, firstItem + 2,
				firstItem + 2, "Street View");
		streetViewItem.setCheckable(true);
		// Visualizziamo il menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Modifichiamo lo stato di quella selezionata
		item.setChecked(!item.isChecked());
		// Abilitiamo o meno l'opzione relativa
		switch (item.getItemId()) {
		case Menu.FIRST:
			mapView.setTraffic(item.isChecked());
			break;
		case Menu.FIRST + 1:
			mapView.setSatellite(item.isChecked());
			break;
		case Menu.FIRST + 2:
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
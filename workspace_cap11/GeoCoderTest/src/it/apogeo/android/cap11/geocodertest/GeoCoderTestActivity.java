package it.apogeo.android.cap11.geocodertest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class GeoCoderTestActivity extends MapActivity {
	/*
	 * Tag del log
	 */
	private final static String LOG_TAG = "GeoCoderTestActivity";
	/*
	 * Identificatore del messaggio della Map
	 */
	private final static int MAP_MESSAGE_ID = 1;
	/*
	 * Identificatore della ProgressBar
	 */
	private final static int PROGRESS_DIALOG_ID = 1;
	/*
	 * Riferimento alla MapView
	 */
	private MapView mapView;
	/*
	 * Riferimento al GeoCoder
	 */
	private Geocoder geocoder;
	/*
	 * Riferimento alla EditText
	 */
	private EditText inputName;	
	
	/*
	 * Livello di Zoom attuale
	 */
	private int zoomLevel = 6;

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
		mapView.getController().setZoom(5);
		mapView.setSatellite(true);
		// Riferimento al campo di testo
		inputName = (EditText)findViewById(R.id.addressName);
		// Otteniamo il riferimento al geocoder
		geocoder = new Geocoder(this,Locale.ITALY);
	}

	/**
	 * Permette di iniziarela ricerca
	 * 
	 * @param button
	 *            Riferimento al button
	 */
	public void searchPlace(View button) {
		Thread searchThread = new Thread("SerachThread") {

			@Override
			public void run() {
				// Otteniamo il messaggio
				Message message = mapHandler.obtainMessage();
				// Utilizziamo il Geocoder per fare la ricerca
				try {
					List<Address> risultati = geocoder.getFromLocationName(
							inputName.getText().toString(), 1);
					// Se c'e' qualcosa lo notifichiamo
					if (risultati != null && risultati.size() > 0) {
						message.obj = risultati.get(0);
						mapHandler.sendMessage(message);
					}
				} catch (IOException e) {
					// Non facciamo nulla ma arriva il messaggio vuoto
					mapHandler.sendEmptyMessage(MAP_MESSAGE_ID);
				}finally{
					dismissDialog(PROGRESS_DIALOG_ID);
				}
			}

		};
		// Visualizziamo la progressDialog
		showDialog(PROGRESS_DIALOG_ID);
		// Facciamo partire il Thread
		searchThread.start();
	}

	private final Handler mapHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// Si ottengono le informazioni se presenti sulla posizione
			if(msg!=null && msg.obj!=null){
				// Estraiamo le informazioni di posizione
				Address address = (Address)msg.obj;
				GeoPoint pointToGo = new GeoPoint((int)(address.getLatitude()*1000000),(int)(address.getLongitude()*1000000));
				mapView.getController().setZoom(12);
				mapView.getController().setCenter(pointToGo);
			}else{
				Toast.makeText(GeoCoderTestActivity.this, "Found nothing. Retry!", Toast.LENGTH_SHORT).show();
			}
			Log.i("GEOCODER!", "E' arrivato!!!" + msg);
			if (msg != null) {
				Log.i("GEOCODER!", "E' arrivato!!!" + msg.obj);
			}
		}

	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG_ID:
			ProgressDialog progressDialog = new ProgressDialog(this,
					ProgressDialog.STYLE_SPINNER);
			progressDialog.setIndeterminate(true);
			progressDialog.setTitle("GeoCoderTest");
			progressDialog.setMessage("Searching...");
			return progressDialog;
		default:
			return null;
		}

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
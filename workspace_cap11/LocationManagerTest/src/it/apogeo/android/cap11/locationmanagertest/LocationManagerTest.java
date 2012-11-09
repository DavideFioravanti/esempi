package it.apogeo.android.cap11.locationmanagertest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocationManagerTest extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "LocationManagerTest";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al LocationManager
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Log.i(LOG_TAG,"LocationManager created!");
		// Registriamo il LocationListener al provider GPS
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						Log.i(LOG_TAG,"onLocationChanged "+location.getLatitude()+":"+location.getLongitude());
						Toast.makeText(LocationManagerTest.this,
								"onLocationChanged "+location.getLatitude()+":"+location.getLongitude(), Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onProviderDisabled(String provider) {
						Log.i(LOG_TAG,"onProviderDisabled "+provider);
						Toast.makeText(LocationManagerTest.this,
								"onProviderDisabled "+provider, Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onProviderEnabled(String provider) {
						Log.i(LOG_TAG,"onProviderEnabled "+provider);
						Toast.makeText(LocationManagerTest.this,
								"onProviderEnabled "+provider, Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onStatusChanged(String provider, int status,Bundle extras) {
						Log.i(LOG_TAG,"onStatusChanged "+provider+" status: "+status);
						Toast.makeText(LocationManagerTest.this,
								"onStatusChanged "+provider+" status: "+status, Toast.LENGTH_SHORT).show();

					}

				});

	}
}
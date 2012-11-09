package it.apogeo.android.cap09.honeycombloadertest;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class HoneycombLoaderTestActivity extends Activity {
	/*
	 * Tag per il log
	 */
	private final static String LOG_TAG ="LoaderTestActivity";
	
	/*
	 * Chiave di test per i parametri del Loader
	 */
	private final static String TEST_KEY ="test_key";
	
	/*
	 * Identificatore unico del Loader
	 */
	private final static int LOADER_ID = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento al LoaderManager
        LoaderManager loaderManager = getLoaderManager();
        // Creiamo un Bundle di esempio per il passaggio di parametri ad un Loader.
        Bundle loaderParams = new Bundle();
        loaderParams.putString(TEST_KEY, "test_value");
        // Gestiamo quindi il Loader per l'accesso al ContentProvider
        loaderManager.initLoader(LOADER_ID, loaderParams, new LoaderCallbacks<Cursor>(){

			@Override
			public Loader<Cursor> onCreateLoader(int loaderId, Bundle params) {
				// Verifichiamo la presenza dei parametri solo come debug
				if(params!=null){
					Log.i(LOG_TAG, "test_key = "+params.getString(TEST_KEY));
				}else{
					Log.i(LOG_TAG, "Nessun parametro associato ");	
				}
				// Questo metodo viene invocato nel caso in cui il Loader di 
				// 
				return null;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
				
			}

			@Override
			public void onLoaderReset(Loader<Cursor> loader) {
				
			}

        	
        });
        
    }
}
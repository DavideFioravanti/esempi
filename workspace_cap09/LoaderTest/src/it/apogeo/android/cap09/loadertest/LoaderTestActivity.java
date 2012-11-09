package it.apogeo.android.cap09.loadertest;

import it.apogeo.android.cap09.loadertest.cp.TeamProviderMetaData;
import it.apogeo.android.cap09.loadertest.cp.TeamProviderMetaData.TeamTableMetaData;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;

public class LoaderTestActivity extends FragmentActivity {
	
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
	
	// Mapping tra i campi della tabella ed elementi del layout di riga
	private String[] FROMS = new String[] { TeamTableMetaData.NAME,
			TeamTableMetaData.CITY, TeamTableMetaData.COUNTRY, TeamTableMetaData.WEB_SITE };
	private int[] TOS = new int[] { R.id.teamName, R.id.teamCity,
			R.id.teamCountry, R.id.teamWebSite };	
	
	/*
	 * Il CursorAdapter
	 */
	private SimpleCursorAdapter cursorAdapter;
	
	/*
	 * Il Cursore con i dati
	 */
	private Cursor cursor;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Creiamo l'adapter
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.row_layout, cursor, FROMS, TOS, 0);
        // Otteniamo il riferimento alla ListView
        ListView listView = (ListView)findViewById(R.id.team_list_view);
        listView.setAdapter(cursorAdapter);        
        // Utilizzando le Compatibility Library abbiamo a disposizione questo metodo
        // invece che il getLoaderManager() disponibile per versioni successive 
        LoaderManager loaderManager = getSupportLoaderManager();
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
				// Questo metodo viene invocato nel caso in cui si debba create il Loader
				// Per fare questo utilizziamo le costanti per la definizione del ContentProvider
				// che abbiamo aggiunto al progetto
				CursorLoader loader = new CursorLoader(LoaderTestActivity.this, TeamProviderMetaData.TeamTableMetaData.CONTENT_URI, null, null, null, null);
				Log.i(LOG_TAG, "Created Loader  "+loader+" with id: "+loaderId);
				// Lo ritorniamo 
				return loader;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
				// Non dobbiamo chiudere il cursor precedente in quanto la responsabilita'
				// del suo ciclo di vita e' del Loader. Noi dobbiamo semplicemente
				// sostituire il cursore precedente con quello corrente
				cursorAdapter.swapCursor(cursor);
			}

			@Override
			public void onLoaderReset(Loader<Cursor> loader) {
				// Dobbiamo fare in modo che l'adapter perda il riferimento al cursore
				cursorAdapter.swapCursor(null);
			}
        	
        });
        
    }
}
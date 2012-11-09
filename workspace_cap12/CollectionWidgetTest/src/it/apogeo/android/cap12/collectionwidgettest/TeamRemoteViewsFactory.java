package it.apogeo.android.cap12.collectionwidgettest;

import it.apogeo.android.cap12.collectionwidgettest.TeamProviderMetaData.TeamTableMetaData;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import android.widget.SimpleCursorAdapter;

/**
 * Implementazione della RemoteViewsFactory che permette di mappare gli elementi
 * del ContentProvider dei Team in RemoteViews per la visualizzazione in un
 * AppWidget di tipo Collection. Notiamo l'utilizzo di un CursorLoader per l'accesso
 * al 
 * 
 * @author Massimo Carli
 *
 */
public class TeamRemoteViewsFactory implements RemoteViewsFactory {
	
	// Mapping tra i campi della tabella ed elementi del layout di riga
	private String[] FROMS = new String[] { TeamTableMetaData.NAME,
			TeamTableMetaData.CITY, TeamTableMetaData.COUNTRY, TeamTableMetaData.WEB_SITE };
	private int[] TOS = new int[] { R.id.teamName, R.id.teamCity,
			R.id.teamCountry, R.id.teamWebSite };		
	
	// Il Context
	private Context context;
	
	/*
	 * Il riferimento all'id del widget associato
	 */
	private int widgetId;
	
	/*
	 * Il CursorAdapter
	 */
	private SimpleCursorAdapter cursorAdapter;
	
	
	/*
	 * Il Cursore con i dati
	 */
	private Cursor cursor;	
	
	/**
	 * Permette di creare una TeamRemoteViewsFactory passando il riferimento al Context
	 * ed all'Intent che ne ha causato la creazione
	 * 
	 * @param context Riferimento al Context
	 * @param intent Intent lanciato per la visualizzazione 
	 */
	public TeamRemoteViewsFactory(Context context,Intent intent){
		// Salviamo il riferimento al Context
		this.context=context;
		Log.i("APP_WIDGET_COLLECTION", "TeamRemoteViewsFactory created");
		// Otteniamo l'informazione relativa all'id del widget associato
		widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#onCreate()
	 */
	@Override
	public void onCreate() {
		// Questo metodo viene invocato in corrispondenza della creazione della Factory
		// per la prima volta. Nel nostro caso creiamo il Cursor 
		cursor = context.getContentResolver().query(TeamTableMetaData.CONTENT_URI, null, null, null, null);
		// e quindi il corrispondente adapter
        cursorAdapter = new SimpleCursorAdapter(context, R.layout.appwidget_item_layout, cursor, FROMS, TOS, 0);
        Log.i("APP_WIDGET_COLLECTION", "onCreate()");
	}	
	
	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#onDestroy()
	 */
	@Override
	public void onDestroy() {
		Log.i("APP_WIDGET_COLLECTION", "onDestroy");
		// Liberiamo l'adapter dal cursore
		cursorAdapter.swapCursor(null);
		// Eliminiamo il cursore
		cursor.close();
	}	
	
	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#getViewAt(int)
	 */
	@Override
	public RemoteViews getViewAt(int position) {
		Log.i("APP_WIDGET_COLLECTION", "getViewAt@"+position);
		// Questo metodo ha la responsabilita' di ritornare la RemoteViews associata
		// all'elemento di posizione data
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget_item_layout);
		// Ottengo l'item corrente
		Cursor itemCursor = (Cursor)cursorAdapter.getItem(position);
		// Valorizziamo i campi 
		String teamName = itemCursor.getString(itemCursor.getColumnIndex(TeamTableMetaData.NAME));
		String teamCity = itemCursor.getString(itemCursor.getColumnIndex(TeamTableMetaData.CITY));
		remoteViews.setTextViewText(R.id.teamName, teamName);
		remoteViews.setTextViewText(R.id.teamCity, teamCity);
		remoteViews.setTextViewText(R.id.teamCountry, itemCursor.getString(itemCursor.getColumnIndex(TeamTableMetaData.COUNTRY)));
		remoteViews.setTextViewText(R.id.teamWebSite, itemCursor.getString(itemCursor.getColumnIndex(TeamTableMetaData.WEB_SITE)));
		// INIZIO CODICE DI GESTIONE DELLA SELEZIONE
		// Qui quello che ci interessa e' notificare attraverso un Intent il valore dell'extra
		// che poi verra' inviato dall'AppWidgetProvider attraverso il PendingIntent di template
		Intent clickFillIntent = new Intent();
		// Impostiamo le informazioni che ci interessano relativamente all'elemento selezionato. Nel
		// nostro caso inseriamo le informazioni relative ai campi nome e citta'
		clickFillIntent.putExtra(TeamTableMetaData.NAME, teamCity);
		clickFillIntent.putExtra(TeamTableMetaData.CITY, teamCity);
		// Lo impostiamo come Intent di gestione della selezione
		remoteViews.setOnClickFillInIntent(R.id.teamRowLayout, clickFillIntent);
		// FINE CODICE DI GESTIONE DELLA SELEZIONE
		// La ritorniamo al chiamante
		return remoteViews;
	}	
	

	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#getCount()
	 */
	@Override
	public int getCount() {
		// Deleghiamo il numero di elementi all'adapter
		Log.i("APP_WIDGET_COLLECTION", "getCount "+cursorAdapter.getCount());
		return cursorAdapter.getCount();
	}

	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// Deleghiamo il calcolo all'adapter
		return cursorAdapter.getItemId(position);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#getViewTypeCount()
	 */
	@Override
	public int getViewTypeCount() {
		// Supponiamo esista una sola tipologia di elementi
		return 1;
	}	
	
	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#hasStableIds()
	 */
	@Override
	public boolean hasStableIds() {
		// Un valore true indica che un particolare elemento mantiene sempre lo stesso id 
		return true;
	}	

	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#getLoadingView()
	 */
	@Override
	public RemoteViews getLoadingView() {
		// Questo metodo ritorna una RemoteViews da visualizzare nel caso in cui l'esecuzione del metodo
		// getViewAt() impiegasse più tempo. Nel nostro caso non ritorniamo nulla. 
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService.RemoteViewsFactory#onDataSetChanged()
	 */
	@Override
	public void onDataSetChanged() {
		// Questo metodo viene invocato in corrispondenza della invocazione del metodo notifyDataSetChanged() 
		// Nel nostro caso non facciamo nulla. 
	}

}

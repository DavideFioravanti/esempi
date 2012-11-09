package it.apogeo.android.cap12.collectionwidgettest;

import it.apogeo.android.cap12.collectionwidgettest.TeamProviderMetaData.TeamTableMetaData;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * 
 * Specializzazione della classe AppWidgetProvider
 * 
 * @author MASSIMO
 * 
 */
public class CollectionAppProvider extends AppWidgetProvider {
	/*
	 * Tag del Log
	 */
	private final static String TAG_LOG = "TestAppProvider";
	
	/*
	 * Identificatore del tipo di richiesta del PendingIntent 
	 */
	private final static int CLICK_REQUEST_ID = 0;	
	
	/**
	 * Action che ci permette di identificare l'Intent lanciato a seguito della selezione di un
	 * elemento (RemoteViews) dell'AppWidget 
	 */
	public final static String ACTION_LIST_SELECTED = "android.appwidget.AppWidgetManager.action.ACTION_LIST_SELECTED";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		Log.i(TAG_LOG, "Update");
		for(int i=0;i<appWidgetIds.length;i++){
			// Otteniamo l'identificativo del widget che richiede un aggiornamento
			int widgetId = appWidgetIds[i];
			// Creiamo la corrispondente RemoteViews passando il nome del package dell'applicazione
			// ed il layout che nel nostro caso contiene una StackView
			RemoteViews remoteViews= new RemoteViews(context.getPackageName(), R.layout.collection_widget_layout);
			// In questo caso dobbiamo legare il contenuto della RemoteViews alle informazioni ottenute
			// attraverso il servizio. Creiamo quindi l'Intent per l'attivazione del servizio che sara'
			// esplicito e conterra' l'identificatore del widget come extra associato alla costante
			// AppWidgetManager.EXTRA_APPWIDGET_ID
			Intent srvIntent = new Intent(context,CollectionAppProviderService.class);
			srvIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
			// Con le informazioni specificate fino a qui l'Intent non e' univoco in quanto gli Extra
			// non vengono considerati in fase di intent evaluation. Per questo motivo utilizziamo
			// questo meccanismo che permette di mettere come data dell'intent la sua stessa rappresentazione
			// come URI. Si tratta una informazione utilizzata per rendere l'Intent univoco ma che comunque
			// non viene utilizzata per l'Intent Resolution in quanto il servizio viene attivato
			// in modo esplicito.
			srvIntent.setData(Uri.parse(srvIntent.toUri(Intent.URI_INTENT_SCHEME)));
			// Il passo successivo consiste nell'impostare l'Intent come quello che avviera' il servizio
			// in grado di fornire la Factory per ottenere la remoteViews da visualizzare in corrispondenza
			// di questo App Widget di id dato
			remoteViews.setRemoteAdapter(widgetId,R.id.stack_view, srvIntent);
			Log.i("APP_WIDGET_COLLECTION", "srvIntent "+srvIntent);
			// Nel layout abbiamo definito una TextView da visualizzare nel caso in cui non ci fossero
			// informazioni per cui la impostiamo nella RemoteViews
			remoteViews.setEmptyView(R.id.stack_view, R.id.empty_view);
			// INIZIO CODICE DI GESTIONE DELLA SELEZIONE
			// Creiamo la parte generica dell'Intent che verra' lanciato a seguito della selezione
			// di un elemento tra quelli visualizzati nell'AppWidget  Notiamo come questo faccia
			// riferimento a questo stesso componente 
			Intent clickIntent = new Intent(context,CollectionAppProvider.class);
			// Per distinguerlo dagli Intent di update gli impostiamo la action definita in questa
			// stessa classe e legata al tipo di AppWidget. 
			clickIntent.setAction(ACTION_LIST_SELECTED);
			// Aggiungiamo come extra l'informazione relativa all'identificatore della particolare 
			// istanza di AppWidget
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
			// Come fatto in precedenza, anche in questo caso abbiamo la necessita' di rendere
			// questo Intent unico per cui aggiungiamo la versione String dell'Intent come
			// campo data dell'Intent stesso
			clickIntent.setData(Uri.parse(clickIntent.toUri(Intent.URI_INTENT_SCHEME)));
			// Creiamo ora un PendingIntent facendo attenzione al flag FLAG_UPDATE_CURRENT che
			// permette di fare in modo che se viene lanciato un PendingIntent mentre un altro e'
			// "pendente" esso viene riutilizzato ed i valori degli extra del più recente copiati
			PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, CLICK_REQUEST_ID, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT); 
			// L'ultimo passo consiste quindi nell'associare questo intent alla selezione di una RemoteViews
			// dell'AppWidget
			remoteViews.setPendingIntentTemplate(R.id.stack_view, clickPendingIntent);
			// FINE CODICE DI GESTIONE DELLA SELEZIONE
			// Aggiorniamo quindi la RemoteView 
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		super.onUpdate(context,appWidgetManager, appWidgetIds);
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(ACTION_LIST_SELECTED.equals(intent.getAction())){
			// Si tratta di un Intent di gestione della selezione per cui estraiamo le
			// informazioni extra che visualizziamo attraverso un Toast
			String teamName = intent.getStringExtra(TeamTableMetaData.NAME);
			String teamCity = intent.getStringExtra(TeamTableMetaData.CITY);
			Toast toast = Toast.makeText(context, "Selezionata "+teamName+" di "+teamCity, Toast.LENGTH_SHORT);
			toast.show();
		}else{
			// In questo caso gestiamo l'Intent nel modo consueto
			super.onReceive(context, intent);	
		}
	}
	

}

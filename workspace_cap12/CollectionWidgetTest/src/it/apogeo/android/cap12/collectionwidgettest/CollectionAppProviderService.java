package it.apogeo.android.cap12.collectionwidgettest;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

/**
 * Questa classe descrive il servizio responsabile dell'aggiornamento di una RemoteViews
 * associata ad una collection di dati. 
 * 
 * @author Massimo Carli
 *
 */
public class CollectionAppProviderService extends RemoteViewsService {

	/* (non-Javadoc)
	 * @see android.widget.RemoteViewsService#onGetViewFactory(android.content.Intent)
	 */
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// Questo metodo dovra' ritornare il riferimento ad un oggetto di tipo RemoteViewsFactory
		// responsabile della trasformazione delle View ottenute da un Adapter nelle corrispondenti
		// RemoteViews. Nel nostro caso utilizziamo una implementazione di RemoteViewsFactory
		// descritta dalla classe TeamRemoteViewsFactory a cui passiamo il riferimento al
		// contesto ed all'intent ricevuto
		TeamRemoteViewsFactory viewsFactory = new TeamRemoteViewsFactory(getApplicationContext(), intent);
		Log.i("APP_WIDGET_COLLECTION", "TeamRemoteViewsFactory created "+viewsFactory);
		return viewsFactory;
	}

}

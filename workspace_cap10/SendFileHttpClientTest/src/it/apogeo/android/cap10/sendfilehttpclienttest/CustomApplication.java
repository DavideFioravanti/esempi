/**
 * 
 */
package it.apogeo.android.cap10.sendfilehttpclienttest;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.Application;
import android.util.Log;

/**
 * @author MASSIMO
 *
 */
public class CustomApplication extends Application {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "CustomApplication";
	
	/*
	 * Riferimento all'HttpClient
	 */
	private HttpClient httpClient;
	

	@Override
	public void onCreate() {
		super.onCreate();
		// Inizializziamo il riferimento all'HttpClient Thread Safe
		httpClient = createHttpClient();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.i(LOG_TAG,"Low Memory!!");
		// Rilasciamo le risorse del client
		releaseHttpClient();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(LOG_TAG,"Terminate CustomApplication");
		// Rilasciamo le risorse del client
		releaseHttpClient();		
	}
	
	/**
	 * @return Il riferimento all'HttpClient ThreadSafe
	 */
	public HttpClient getThreadSafeHttpClient(){
		return httpClient;
	}
	
	/*
	 * Incapsula la logica di creazione del client
	 */
	private final HttpClient createHttpClient(){
		// Per creare il corrispondente ThreadSafeClientConnManager abbiamo
		// bisogno delle informazioni relative al tipo di protocollo che intendiamo
		// gestire. Nel nostro caso definiamo il supporto per l'HTTP e l'HTTPS attraverso
		// la definizione di alcuni parametri e schemi
		HttpParams httpParams = new BasicHttpParams();
		// Impostiamo la versione del protocollo HTTP alla 1.1
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		// Impostiamo il charset di default
		HttpProtocolParams.setContentCharset(httpParams, HTTP.DEFAULT_CONTENT_CHARSET);
		// Dobbiamo quindi descrivere quelli che sono i protocolli che intendiamo
		// gestire associando a ciascuno uno schema ed una Factory delle connessioni
		// Socket da utilizzare
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		// Gestiamo il protocollo HTTP nella porta 80
		Scheme httpScheme = new Scheme("http",PlainSocketFactory.getSocketFactory(),80);
		schemeRegistry.register(httpScheme);
		// Gestiamo il protocollo HTTPS nella porta 443
		Scheme httpsScheme = new Scheme("https",SSLSocketFactory.getSocketFactory(),443);
		schemeRegistry.register(httpsScheme);		
		// Creiamo il ThreadSafeClientConnManager e lo assegnamo al client
		ClientConnectionManager tsConnManager = new ThreadSafeClientConnManager(httpParams,schemeRegistry);
		HttpClient tmpClient = new DefaultHttpClient(tsConnManager,httpParams);
		// ritorniamo l'oggetto creato
		Log.i(LOG_TAG,"HttpClient Created!");
		return tmpClient;
	}
	
	/*
	 * Questo metodo permette di liberare le risorse occupate dal client ovvero
	 * le diverse connessioni utilizzate. Per questo motivo andiamo a chiudere
	 * le connessioni attraverso il ConnectionManager
	 */
	private final void releaseHttpClient(){
		if(httpClient!=null && httpClient.getConnectionManager()!=null){
			// Se esiste il ConnectionManager del client lo chiudiamo
			httpClient.getConnectionManager().shutdown();
			Log.i(LOG_TAG,"Releasing Connections");
		}
	}
	
	

}

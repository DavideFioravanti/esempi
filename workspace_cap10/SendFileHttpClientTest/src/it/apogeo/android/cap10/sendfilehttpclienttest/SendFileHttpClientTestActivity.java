package it.apogeo.android.cap10.sendfilehttpclienttest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SendFileHttpClientTestActivity extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "ThreadSafeHttpClientTestActivity";
	/*
	 * Identificativo della ProgressDialog di attesa
	 */
	private final static int WAITING_PROGRESS_DIALOG_ID = 1;
	/*
	 * Indirizzo del server a cui accedere
	 */
	private final static String TARGET_URL = "http://10.120.20.91:8080/examples/jsp/snp/snoop.jsp";
	/*
	 * Riferimento all'Handler
	 */
	private Handler myHandler;
	/*
	 * Riferimento alla ProgressDialog
	 */
	private ProgressDialog progressDialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		final TextView outputView = (TextView) findViewById(R.id.outputView);
		myHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// Estrapoliamo il messaggiodi testo dal msg
				outputView.setText(msg.obj.toString());
				Log.i(LOG_TAG, msg.obj.toString());
			}

		};
	}

	/*
	 * Incapsula a logica di invio della richiesta Http
	 */
	public void sendHttpRequest(View button) {
		// Avviamo un Thread con il corrispondente codice di connessione
		Thread workerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// Otteniamo il riferimento al HttpClient attraverso la
					// Application
					CustomApplication ca = (CustomApplication)getApplication();
					HttpClient httpClient = ca.getThreadSafeHttpClient();
					// Creiamo la HttpUriRequest relativamente al metodo POST
					HttpPost request = new HttpPost();
					// Impostiamo il valore dell'URI di destinazione
					URI targetUri = new URI(TARGET_URL);
					request.setURI(targetUri);
					// Otteniamo il riferimento alla risorsa da inviare come
					// array di byte
					InputStream imageIS = getResources().openRawResource(R.drawable.hamburger);
					//byte[] imageBytes = IOUtils.toByteArray(imageIS);
					// Creiamo un oggetto di tipo InpuStreamBody che ci permette di
					// inserire l'array di byte all'interno di un MultiPart
					InputStreamBody imagePart = new InputStreamBody(imageIS,"imageToUpload");
					// Creiamo una Entity di tio MultiPart
					MultipartEntity mpEntity = new MultipartEntity();
					mpEntity.addPart("name", new StringBody("value"));
					mpEntity.addPart("name2", new StringBody("value2"));
					mpEntity.addPart("imageToUpload", imagePart);
					// Impostiamo l'entità nella request
					request.setEntity(mpEntity);
					// A questo punto invochiamo il server
					httpClient.execute(request, myResponseHandler);
				} catch (Exception e) {
					showMessageOnOutput(e.getMessage());
					Log.e(LOG_TAG, e.getMessage());
				} finally {
					dismissDialog(WAITING_PROGRESS_DIALOG_ID);
				}

			}

		});
		// Visualizziamo una Dialog di attesa
		showDialog(WAITING_PROGRESS_DIALOG_ID);			
		// Lo avviamo
		workerThread.start();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case WAITING_PROGRESS_DIALOG_ID:
			progressDialog = new ProgressDialog(this,
					ProgressDialog.STYLE_SPINNER);
			progressDialog.setTitle("HTTP Connection");
			progressDialog.setMessage("Connecting...");
			progressDialog.setIndeterminate(true);
			return progressDialog;
		default:
			return null;
		}
	}

	/**
	 * Invia un messaggio all'Handler relativo al MainThread per la sua
	 * visualizzazione nella TextView di output
	 * 
	 * @param message
	 *            Messaggio da visualizzare
	 */
	private void showMessageOnOutput(String message) {
		Message messageToSend = myHandler.obtainMessage();
		messageToSend.obj = message;
		myHandler.sendMessage(messageToSend);
	}

	private final ResponseHandler<String> myResponseHandler = new ResponseHandler<String>() {

		@Override
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			// Estraiamo il risultato dalla risposta. Otteniamo quindi il content
			// da cui leggere le informazioni
			InputStream content = response.getEntity().getContent();
			byte[] buffer = new byte[1024];
			int numRead = 0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while((numRead=content.read(buffer))!=-1){
				baos.write(buffer, 0, numRead);
			}
			// Chiudiamo lo stream
			content.close();
			// Otteniamo il risultato
			String result = new String(baos.toByteArray());
			showMessageOnOutput(result);
			
			return result;
		}

	};
}
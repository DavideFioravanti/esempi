package it.apogeo.android.cap11.webviewclienttest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebViewClientTestActivity extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "WebViewClientTestActivity";
	/*
	 * Riferimento alla WebView
	 */
	private WebView webView;

	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gestiamo le informazioni relative alle feature della finestra
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla WebView
        webView = (WebView)findViewById(R.id.webView);        
        // Abilitazione delle opzioni di Zoom
        webView.getSettings().setBuiltInZoomControls(true);
        Log.i(LOG_TAG, "WebView obtained!");
        webView.setWebViewClient(new LoggedWebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// Intercettiamo l'URL e lo assegnamo alla webView attraverso
				// il metodo loadUri
				loadUriOnWebView(url);
				// Ritorniamo true per indicare di avergestiro l'URL
				// in modo personalizzato
				return true;
			}
        	
        });
        webView.setWebChromeClient(new WebChromeClient() {
        	/**
        	 * Questo metodo viene invocato passando un indicatore del
        	 * progresso della barra di caricamento della WebView. Si tratta
        	 * di un valore che va da 0 a 100 mentre quello della ProgressBar
        	 * della barra va da 0 a 10000 
        	 */
        	 public void onProgressChanged(WebView view, int progress) {
        	     setProgress(progress * 100);
        	 }
       });
    }

	public void loadUri(View button) {
		Log.i(LOG_TAG, "loadUri");
		// Carichiamo un indirizzo URL
		loadUriOnWebView("http://www.massimocarli.it");
	}

	public void loadData(View button) {
		Log.i(LOG_TAG, "loadData");
		// Carichiamo un contenuto HTML
		StringBuilder htmlData = new StringBuilder("<html>");
		htmlData.append("<head><title>MC Home</title></head>");
		htmlData.append("<body>");
		htmlData
				.append("<a href='http://www.massimocarli.it'>Go to MC Home</a>");
		htmlData.append("</body>");
		htmlData.append("</html>");
		webView.loadData(htmlData.toString(), "text/html", "ISO 8859-1");
	}

	/*
	 * Permette l'invocazione del metodo loadUrl anche da una classe interna
	 */
	private void loadUriOnWebView(String uri) {
		webView.loadUrl(uri);
	}
}
package it.apogeo.android.cap11.simplewebview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class SimpleWebViewActivity extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "SimpleWebViewActivity";
	/*
	 * Riferimento alla WebView
	 */
	private WebView webView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla WebView
        webView = (WebView)findViewById(R.id.webView);
        Log.i(LOG_TAG, "WebView obtained!");
    }
    
    public void loadUri(View button){
    	Log.i(LOG_TAG, "loadUri");
    	// Carichiamo un indirizzo URL
    	webView.loadUrl("http://www.massimocarli.it");
    }
    
    public void loadData(View button){
    	Log.i(LOG_TAG, "loadData");
    	// Carichiamo un contenuto HTML
    	StringBuilder htmlData = new StringBuilder("<html>");
    	htmlData.append("<head><title>MC Home</title></head>");
    	htmlData.append("<body>");
    	htmlData.append("<a href='http://www.massimocarli.it'>Go to MC Home</a>");
    	htmlData.append("</body>");
    	htmlData.append("</html>");
    	webView.loadData(htmlData.toString(), "text/html", "ISO 8859-1");    	
    }    
    
}
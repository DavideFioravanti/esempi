/**
 * 
 */
package it.apogeo.android.cap11.jswebkittest;

import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author MASSIMO
 *
 */
public class LoggedWebViewClient extends WebViewClient {
	/*
	 * Log del tag di default
	 */
	private final static String LOG_TAG = "LoggedWebViewClient";
	
	private final String logTag;

	public LoggedWebViewClient(){
		this(LOG_TAG);
	}	
	
	public LoggedWebViewClient(String tag){
		logTag = tag;
	}

	@Override
	public void doUpdateVisitedHistory(WebView view, String url,
			boolean isReload) {
		Log.i(logTag, "doUpdateVisitedHistory");
		super.doUpdateVisitedHistory(view, url, isReload);
	}

	@Override
	public void onFormResubmission(WebView view, Message dontResend,
			Message resend) {
		Log.i(logTag, "onFormResubmission");
		super.onFormResubmission(view, dontResend, resend);
	}

	@Override
	public void onLoadResource(WebView view, String url) {
		Log.i(logTag, "onLoadResource");
		super.onLoadResource(view, url);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		Log.i(logTag, "onPageFinished");
		super.onPageFinished(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		Log.i(logTag, "onPageStarted");
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		Log.i(logTag, "onReceivedError");
		super.onReceivedError(view, errorCode, description, failingUrl);
	}

	@Override
	public void onReceivedHttpAuthRequest(WebView view,
			HttpAuthHandler handler, String host, String realm) {
		Log.i(logTag, "onReceivedHttpAuthRequest");
		super.onReceivedHttpAuthRequest(view, handler, host, realm);
	}

	@Override
	public void onScaleChanged(WebView view, float oldScale, float newScale) {
		Log.i(logTag, "onScaleChanged");
		super.onScaleChanged(view, oldScale, newScale);
	}

	@Override
	public void onTooManyRedirects(WebView view, Message cancelMsg,
			Message continueMsg) {
		Log.i(logTag, "onTooManyRedirects");
		super.onTooManyRedirects(view, cancelMsg, continueMsg);
	}

	@Override
	public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
		Log.i(logTag, "onUnhandledKeyEvent");
		super.onUnhandledKeyEvent(view, event);
	}

	@Override
	public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
		Log.i(logTag, "shouldOverrideKeyEvent");
		return super.shouldOverrideKeyEvent(view, event);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		Log.i(logTag, "shouldOverrideUrlLoading");
		return super.shouldOverrideUrlLoading(view, url);
	}
	
	

}

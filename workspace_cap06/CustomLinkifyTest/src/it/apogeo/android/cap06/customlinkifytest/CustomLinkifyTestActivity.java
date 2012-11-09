package it.apogeo.android.cap06.customlinkifytest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class CustomLinkifyTestActivity extends Activity {
	/*
	 * Permette di riconoscere i numeri di tre cifre
	 */
	private final static Pattern CUSTOM_PATTERN = Pattern.compile("\\b[0-9]{3}\\b");	
	/*
	 * Schema da pre-pendere alla stringa che matcha
	 */
	private final static String CUSTOM_SCHEMA = "custom://www.massimocarli.it/";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla TextView
        TextView textView = (TextView)findViewById(R.id.output);
        // Creiamo una implementazione di Linkify
        Linkify.TransformFilter filter = new Linkify.TransformFilter(){

        	@Override
			public String transformUrl(Matcher match, String url) {
        		// Semplice implementazione che prende le ultime 2 cifre
				return "number"+url;
			}
        	
        };
        // Applichiamo il transformer
        Linkify.addLinks(textView, CUSTOM_PATTERN, CUSTOM_SCHEMA,null,filter);
    }
}
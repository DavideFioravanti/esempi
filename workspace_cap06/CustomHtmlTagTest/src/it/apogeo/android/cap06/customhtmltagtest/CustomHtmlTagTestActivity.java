package it.apogeo.android.cap06.customhtmltagtest;

import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class CustomHtmlTagTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Creiamo l'implementazione di TagHandler
        Html.TagHandler handler = new Html.TagHandler(){

			@Override
			public void handleTag(boolean opening, String tag, Editable output,
					XMLReader xmlReader) {
				// Verifichiamo che si tratti del tag corretto
				if("tonde".equalsIgnoreCase(tag)){
					// A seconda che il tag sia aperto o chiuso
					// impostiamo la parentesi corrispondente
					String value = (opening)?"(":")";
					// Aggiungiamo il valore all'output
					output.append(value);
				}
			}
        	
        };
        // Otteniamo il riferimento al testo da elaborare
        String srcText = getResources().getString(R.string.sample_text);
        // Lo trasformiamo attraverso i tool Html
        Spanned spannedText = Html.fromHtml(srcText,null,handler);
        // Otteniamo il riferimento alla TextView
        TextView output = (TextView)findViewById(R.id.output);
        // Assegnamo il valore ottenuto
        output.setText(spannedText);
    }
}
package it.apogeo.android.cap03.xmlresourcetest;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

public class XMLResourceTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView textView = (TextView) findViewById(R.id.output);
		// Otteniamo il riferimento al parser associato
		// alla risorsa XML
		XmlResourceParser parser = getResources().getXml(R.xml.books);
		// Creiamo uno StringBuilder per il contenuto
		StringBuilder buffer = new StringBuilder();
		try {
			// Otteniamo il tipo di evento letto dal parser
			int eventType = parser.getEventType();
			// Continuiamo fino alla fine del documento
			String isbn = null;
			String title = null;
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {
					// Se il tag che viene aperto è quello di book
					// ne prendiamo il valore dell'attributo isbn
					String tagName = parser.getName();
					if ("book".equals(tagName)) {
						// Si tratta del tag relativo ad un libro
						// ne prendiamo l'attributo isbn
						isbn = parser.getAttributeValue(0);
					} else {
						// Non e' il tag book
					}
				} else if (eventType == XmlResourceParser.TEXT) {
					// Può solo essere il testo associato al book
					title = parser.getText();
					// Abbiamo concluso un testo per cui appendiamo
					// isbn e titolo
					buffer.append(isbn).append(" : ").append(title).append("\n");
				}
				// Andiamo avanti
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Impostiamo il testo ottenuto come testo della textView
		textView.setText(buffer.toString());
	}
}
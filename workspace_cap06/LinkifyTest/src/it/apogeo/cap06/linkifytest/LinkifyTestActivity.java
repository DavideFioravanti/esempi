package it.apogeo.cap06.linkifytest;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;


public class LinkifyTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla TextView
        TextView textView = (TextView)findViewById(R.id.output);
        // Applichiamo alla TextView i flag relativi alla gestione dei link web
        Linkify.addLinks(textView, Linkify.WEB_URLS|Linkify.EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS);
        // Avremmo potuto abilitare tutti i flag con la seguiente
        // istruzione 
        // Linkify.addLinks(textView, Linkify.ALL);
        // Mettiamo il testo in una unica riga
        //textView.setSingleLine(true);
        // Esempio di scrolling orizzontale del testo
        textView.setHorizontallyScrolling(true);
        // Utilizzo dell Ellipsizing
        //textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //textView.setMarqueeRepeatLimit(-1);
    }
    
}
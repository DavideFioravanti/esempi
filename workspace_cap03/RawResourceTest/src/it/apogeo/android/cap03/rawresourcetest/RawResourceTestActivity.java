package it.apogeo.android.cap03.rawresourcetest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RawResourceTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		// Otteniamo il riferimento alla TextView
		TextView textView = (TextView) findViewById(R.id.output);        
        // Otteniamo il riferimento ad un InputStream per la lettura
        // della risorsa raw
        InputStream is = getResources().openRawResource(R.raw.books);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int numRead = 0;
        try {
			while((numRead = is.read(buffer))!=-1){
				baos.write(buffer, 0, numRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
	        try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        try {
	        	baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
	        
		}
        byte[] bytes = baos.toByteArray();
        String text = new String(bytes);
		// Impostiamo il testo ottenuto come testo della textView
		textView.setText(text);        
    }
}
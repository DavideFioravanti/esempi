package it.apogeo.android.cap06.edittexttest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alla EditText di input
        final EditText inputText = (EditText)findViewById(R.id.inputText);
        // Otteniamo il riferimento alla TextView di output
        final TextView outputText = (TextView)findViewById(R.id.outputText); 
        // Gestiamo il bottone
        Button updateButton = (Button)findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// Leggiamo il valore inserito
				Editable text = inputText.getText();
				// Esempio di aggiunta di testo a quanto ottenuto
				//text.append(" Appended!");
				// Lo inseriamo cone valore della TextView
				outputText.setText(text);
			}});
        
    }
}
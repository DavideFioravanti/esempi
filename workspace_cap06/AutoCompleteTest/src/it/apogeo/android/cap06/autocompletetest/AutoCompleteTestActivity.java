package it.apogeo.android.cap06.autocompletetest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class AutoCompleteTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento all'insieme dei suggerimenti
        String[] tips = getResources().getStringArray(R.array.nani_array);
        // Inizializziamo un Adapter per la gestione dei valori di autocomplete
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tips);
        // Otteniamo il riferimento all'AutoCompleteTextView
        final AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.inputText);
        // Impostiamo l'adapter alla EditView
        acTextView.setAdapter(adapter);
        // Otteniamo il riferimento alla TextView di output
        final TextView outputText = (TextView)findViewById(R.id.outputText); 
        // Gestiamo il bottone
        Button updateButton = (Button)findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// Leggiamo il valore inserito
				Editable text = acTextView.getText();
				// Esempio di aggiunta di testo a quanto ottenuto
				//text.append(" Appended!");
				// Lo inseriamo cone valore della TextView
				outputText.setText(text);
			}}
        );        
    }
}
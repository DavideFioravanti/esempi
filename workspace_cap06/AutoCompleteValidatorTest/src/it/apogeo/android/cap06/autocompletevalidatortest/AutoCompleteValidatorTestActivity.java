package it.apogeo.android.cap06.autocompletevalidatortest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class AutoCompleteValidatorTestActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento all'insieme dei valori di gestire
        final String[] numbers = getResources().getStringArray(R.array.numbers_array);
        // Otteniamo il riferimento all'AutoCompleteTextView
        final AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.inputText);
        // Mettiamo la solglia a 2 per prendere anche il due
        acTextView.setThreshold(2);
        // Assegnamo all'AutoCompleteTextView un Validator
        acTextView.setValidator(new AutoCompleteTextView.Validator(){

			@Override
			public CharSequence fixText(CharSequence invalidText) {
				// Questo Validator prova la sostituzione del testo
				// solamente se relativo ad un numero e quindi se
				// relativo ad uno di quelli precedenti
				boolean toFix = isPresentIgnoreCase(numbers,invalidText);
				if(toFix){
					// Se da sistemare proponiamo la versione 
					// tutta maiuscola
					return invalidText.toString().toUpperCase();
				}
				// In caso contrario ritorniamo 
				return invalidText;
			}

			@Override
			public boolean isValid(CharSequence text) {
				// Il valore inserito è valido se presente nel Set
				// e tutto in uppercase
				boolean isPresent = isPresentIgnoreCase(numbers,text);
				if(isPresent){
					// Se presente è valido nel caso in cui è scritto
					// in maiuscolo
					return text.toString().toUpperCase().equals(text);
				}else{
					// Se non presente non è un valore valido
					return false;	
				}
			}
        	
        });
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
    
    /*
     * Metodo di utilità che permette di verificare se un valore è presente
     * nell'array di String ignorandone il case 
     */
    private final boolean isPresentIgnoreCase(String[] array,CharSequence value){
    	boolean found = false;
    	int index = 0;
    	while(!found && index<array.length){
    		found = array[index++].equalsIgnoreCase(value.toString());
    	}
    	return found;
    }
}
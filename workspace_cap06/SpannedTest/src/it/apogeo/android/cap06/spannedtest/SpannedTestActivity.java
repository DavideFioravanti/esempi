package it.apogeo.android.cap06.spannedtest;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

public class SpannedTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento alle risorse
        final Resources resources = getResources();
        // Il testo normale
        CharSequence normalText = resources.getText(R.string.normal_text);
        TextView normalTextView = (TextView)findViewById(R.id.normalText);
        normalTextView.setText(normalText);
        // Il testo spanned
        Spanned spannedtext = (Spanned)resources.getText(R.string.spanned_text);
        TextView spannedTextView = (TextView)findViewById(R.id.spannedText);
        spannedTextView.setText(spannedtext);
        // Il testo parsed
        String parsedText = resources.getString(R.string.parsed_text);
        // Analoga ostruzione in un passo solo        
        // parsedText = resources.getString(R.string.parsed_text, "Parsed Text");
        // parsedText = String.format(parsedText, "Parsed Text");
        TextView parsedTextView = (TextView)findViewById(R.id.parsedText);
        parsedTextView.setText(parsedText);
    }
}
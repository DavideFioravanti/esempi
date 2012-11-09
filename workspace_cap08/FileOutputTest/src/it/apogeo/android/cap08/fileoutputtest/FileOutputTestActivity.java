package it.apogeo.android.cap08.fileoutputtest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FileOutputTestActivity extends Activity {
	// Colori relativi ai messaggi di successo ed errore
	private final static int SUCCESS_COLOR = Color.GREEN;
	private final static int ERROR_COLOR = Color.RED;
	// Path del file da salvare
	private final static String FILE_PATH = "myFile";
	// Riferimento allo Spinner
	private Spinner spinnerMode;
	// TextView dei messaggi di output
	private TextView output;
	// EditText per l'input
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Valorizziamo lo Spinner relativamente al mode
		String[] modeValues = getResources().getStringArray(R.array.mode_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, modeValues);
		spinnerMode = (Spinner) findViewById(R.id.spinnerMode);
		spinnerMode.setAdapter(adapter);
		// Otteniamo il riferimento alla TextView di output
		output = (TextView) findViewById(R.id.output);
		// Otteniamo il riferimento all'EditText
		editText = (EditText) findViewById(R.id.inputText);
	}

	/**
	 * Metodo di utilità per il salvataggio del testo inserito
	 * 
	 * @param button
	 *            Riferimento al button
	 */
	public void saveFile(View button) {
		// Riferimento al valore inserito
		CharSequence inputData = editText.getText();
		// Otteniamo il valore associato al mode
		long selectedId = spinnerMode.getSelectedItemId();
		int mode = Context.MODE_PRIVATE;
		if (selectedId == 0) {
			mode = Context.MODE_PRIVATE;
		} else if (selectedId == 1) {
			mode = Context.MODE_WORLD_READABLE;
		} else if (selectedId == 2) {
			mode = Context.MODE_WORLD_WRITEABLE;
		} else if (selectedId == 3) {
			mode = Context.MODE_APPEND;
		}
		// Resettiamo il messaggio di output
		output.setText("");
		// Se diverso da null salviamo il contenuto su file
		try {
			FileOutputStream fos = openFileOutput(FILE_PATH, mode);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeUTF(inputData.toString());
			dos.close();
			output.setTextColor(SUCCESS_COLOR);
			output.setText(R.string.ok_message);
		} catch (IOException e) {
			e.printStackTrace();
			output.setTextColor(ERROR_COLOR);
			output.setText(e.getMessage());
		}
	}

	/**
	 * Caricamento del file e visualizzazione nella EditText
	 * 
	 * @param button
	 *            Riferimento al Button
	 */
	public void loadFile(View button) {
		// Leggiamo il file
		try {
			FileInputStream fis = openFileInput(FILE_PATH);
			DataInputStream dis = new DataInputStream(fis);
			String text = dis.readUTF();
			dis.close();
			output.setTextColor(SUCCESS_COLOR);
			editText.setText(text);
			output.setText(R.string.ok_message);
		} catch (IOException e) {
			e.printStackTrace();
			output.setTextColor(ERROR_COLOR);
			output.setText(e.getMessage());
		}
	}

	/**
	 * Cancellazione del file e visualizzazione nella EditText
	 * 
	 * @param button
	 *            Riferimento al Button
	 */
	public void deleteFile(View button) {
		File file = getFileStreamPath(FILE_PATH);
		boolean deleted = false;
		try {
			deleted = file.delete();
			// E' possibile anche invocare il seguente metodo
			// di Context
			// deleteFile(FILE_PATH);
			output.setTextColor(SUCCESS_COLOR);
			output.setText("Deleted:" + deleted);
			editText.setText("");
		} catch (SecurityException se) {
			se.printStackTrace();
			deleted= false;
			output.setTextColor(ERROR_COLOR);
			output.setText(se.getMessage());
		}
	}

}
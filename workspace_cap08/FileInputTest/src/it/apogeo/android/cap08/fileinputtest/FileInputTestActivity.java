package it.apogeo.android.cap08.fileinputtest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FileInputTestActivity extends Activity {
	// Colori relativi ai messaggi di successo ed errore
	private final static int SUCCESS_COLOR = Color.GREEN;
	private final static int ERROR_COLOR = Color.RED;
	// Path del file da salvare
	private final static String FILE_PATH = "/data/data/it.apogeo.android.cap08.fileoutputtest/files/myFile";
	// TextView dei messaggi di output
	private TextView output;
	// EditText per l'input
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		// Resettiamo il messaggio di output
		output.setText("");
		// Se diverso da null salviamo il contenuto su file
		try {
			FileOutputStream fos = new FileOutputStream(new File(FILE_PATH));
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
			FileInputStream fis = new FileInputStream(new File(FILE_PATH));
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
		File file = new File(FILE_PATH);
		boolean deleted = false;
		try {
			deleted = file.delete();
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
package it.apogeo.android.cap08.sdcardtest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SDCardTestActivity extends Activity {
	// Colori relativi ai messaggi di successo ed errore
	private final static int SUCCESS_COLOR = Color.GREEN;
	private final static int ERROR_COLOR = Color.RED;
	// Path del file da salvare
	private final static String FILE_PATH = "mysdfile.txt";
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
			// Otteniamo la root della memoria esterna
			File sdcardDir = Environment.getExternalStorageDirectory();
			// Creiamo il riferimento al nostro file
			File file = new File(sdcardDir,FILE_PATH);			
			if(!file.canWrite()){
				output.setTextColor(ERROR_COLOR);
				output.setText(R.string.cant_write_message);				
				return;
			}
			FileOutputStream fos = new FileOutputStream(file);
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
			// Otteniamo la root della memoria esterna
			File sdcardDir = Environment.getExternalStorageDirectory();
			// Creiamo il riferimento al nostro file
			File file = new File(sdcardDir,FILE_PATH);
			if(!file.canRead()){
				output.setTextColor(ERROR_COLOR);
				output.setText(R.string.cant_read_message);				
				return;
			}				
			FileInputStream fis = new FileInputStream(file);			
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
		// Otteniamo la root della memoria esterna
		File sdcardDir = Environment.getExternalStorageDirectory();
		// Creiamo il riferimento al nostro file
		File file = new File(sdcardDir,FILE_PATH);
		if(!file.canWrite()){
			output.setTextColor(ERROR_COLOR);
			output.setText(R.string.cant_write_message);				
			return;
		}		
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
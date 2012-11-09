package it.apogeo.android.cap12.ttsenginetest;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class TTSEngineTestActivity extends Activity {
	/*
	 * Tag del Log
	 */
	private final static String TAG_LOG = "TTSEngineTestActivity";
	/*
	 * Identificatore della richiesta di disponibilità API
	 */
	private final static int TTS_CHECK_RESULT_CODE = 1;
	/*
	 * Riferimento al motore TextToSpeech
	 */
	private TextToSpeech textToSpeech;
	/*
	 * Riferimento alla EditText di input
	 */
	private EditText inputText;
	/*
	 * Riferimento allo spinner
	 */
	private Spinner languageSpinner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Invio dell'Intent di verifica presenza delle risorse TTS
		Intent checkTTSIntent = new Intent();
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkTTSIntent, TTS_CHECK_RESULT_CODE);
	}

	/**
	 * Metodo invocato a seguito della selezione del pulsante View
	 * 
	 * @param button
	 *            Riferimento al button
	 */
	public void talk(View button) {
		// Otteniamo il valore corrispondente alla lingua
		int languagePosizion = languageSpinner.getSelectedItemPosition();
		switch (languagePosizion) {
		case 0:
			textToSpeech.setLanguage(Locale.ITALIAN);
			break;
		case 1:
			textToSpeech.setLanguage(Locale.FRENCH);
			break;
		case 2:
			textToSpeech.setLanguage(Locale.GERMAN);
			break;
		case 3:
			textToSpeech.setLanguage(Locale.UK);
			break;
		case 4:
			textToSpeech.setLanguage(Locale.US);
			break;
		case 5:
			textToSpeech.setLanguage(new Locale("spa", "ESP"));
			break;
		}
		// Leggiamo il testo da riprodurre
		String textToSay = getResources().getString(R.string.empty_message);
		if (!TextUtils.isEmpty(inputText.getText())) {
			textToSay = inputText.getText().toString();
		}

		/*
		HashMap<String, String> ttsParams = new HashMap<String, String>();
		ttsParams.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MY_UTTERANCE");
		textToSpeech.speak(textToSay, TextToSpeech.QUEUE_FLUSH, null);
		textToSpeech
				.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {

					@Override
					public void onUtteranceCompleted(String utteranceId) {
						Log.i(TAG_LOG, "Utterance " + utteranceId
								+ " completed");

					}

				});
		textToSpeech.speak(textToSay, TextToSpeech.QUEUE_FLUSH, ttsParams);
		*/
		// Lo riproduciamo interrompendo quello che eventualmente era in
		// riproduzione
		textToSpeech.speak(textToSay, TextToSpeech.QUEUE_FLUSH, null);
	}

	/**
	 * Invocato incorrispondenza della ricezione del valore di ritorno dello
	 * sartActivityForResult per la verifica e download del motore TTS
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Verifichiamo che si tratti della richiesta relativa alla
		// disponibilità
		if (requestCode == TTS_CHECK_RESULT_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// Se le risorse sono disponibili creiamo l'istanza del motore
				// di TTS
				textToSpeech = new TextToSpeech(this, new OnInitListener() {

					@Override
					public void onInit(int status) {
						if (status == TextToSpeech.SUCCESS) {
							// In caso si successo andiamo alla View iniziale
							Log.i(TAG_LOG, "TTS Initialized with SUCCESS ");
							initUI();
						} else {
							// In caso di errore terminiamo l'applicazione
							finish();
						}
					}
				});
			} else {
				// Le informazioni non sono disponibili per cui inviamo
				// l'Intent per la loro installazione
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	/*
	 * Metodo di inizializzazione della GUI
	 */
	private final void initUI() {
		// Visulizziamo la View principale dell'applicazione
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla EditText di input
		inputText = (EditText) findViewById(R.id.inputText);
		// Inizializziamo lo Spinner
		languageSpinner = (Spinner) findViewById(R.id.languageSpinner);
		String[] spinnerValues = getResources().getStringArray(
				R.array.spinner_values);
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerValues);
		languageSpinner.setAdapter(spinnerAdapter);
	}

}
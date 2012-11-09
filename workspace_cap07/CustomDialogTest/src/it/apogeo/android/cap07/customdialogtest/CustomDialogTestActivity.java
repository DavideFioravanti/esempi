package it.apogeo.android.cap07.customdialogtest;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

public class CustomDialogTestActivity extends Activity {
	// ID della Dialog Custom
	private final static int CUSTOM_DIALOG_ID = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void manageCustomDialog(View view) {
		showDialog(CUSTOM_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CUSTOM_DIALOG_ID:
			return createCustomDialog();
		default:
			return null;
		}
	}

	/*
	 * Creazione di una finestra di dialogo custom
	 */
	private Dialog createCustomDialog() {
		Dialog customDialog = new Dialog(this);
		// Impostiamo il titolo
		customDialog.setTitle(R.string.custom_label);
		// Impostiamo la View della finestra di dialogo
		customDialog.setContentView(R.layout.my_dialog);
		// La ritorniamo
		return customDialog;
	}

}
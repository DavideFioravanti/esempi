package it.apogeo.android.cap07.alertdialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AlertDialogTestActivity extends Activity {

	private final static int YES_NO_DIALOG = 0;
	private final static int SINGLE_CHOICE_DIALOG = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void manageYesNoDialog(View view) {
		// Visualizziamo la corrispondente Dialog
		showDialog(YES_NO_DIALOG);
	}

	public void manageSingleChoiceDialog(View view) {
		// Visualizziamo la corrispondente Dialog
		showDialog(SINGLE_CHOICE_DIALOG);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case YES_NO_DIALOG:
			return createYesNoDialog();
		case SINGLE_CHOICE_DIALOG:
			return createSigleChoiceDialog();
		default:
			return null;
		}
	}

	private final Dialog createYesNoDialog() {
		// Otteniamo il riferimento al Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Impostiamo il titolo, il messaggio ed una icona in chaining
		builder.setTitle(R.string.yes_no_alert_label);
		builder.setIcon(R.drawable.icon);
		builder.setMessage(R.string.yes_no_alert_message);
		// Impostiamo il pulsante di Yes con il relativo listener
		builder.setPositiveButton(R.string.yes_label,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(AlertDialogTestActivity.this,
								R.string.yes_label, Toast.LENGTH_SHORT).show();
					}

				});
		// Impostiamo il pulsante di No con il relativo listener
		builder.setNegativeButton(R.string.no_label,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(AlertDialogTestActivity.this,
								R.string.no_label, Toast.LENGTH_SHORT).show();
					}

				});
		// Ritorniamo l'Alert creato
		return builder.create();
	}

	private final Dialog createSigleChoiceDialog() {
		// Otteniamo il riferimento al Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Impostiamo il titolo, il messaggio ed una icona in chaining
		builder.setTitle(R.string.list_alert_label).setIcon(R.drawable.icon);
		// Impostiamo le scelte singole
		final String[] months = getResources().getStringArray(
				R.array.months_array);
		builder.setSingleChoiceItems(months, -1,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AlertDialogTestActivity.this,
								"Selected: " + months[which],
								Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}

				});
		// Ritorniamo l'Alert creato
		return builder.create();
	}

}
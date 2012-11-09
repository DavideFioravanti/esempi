package it.apogeo.android.cap08.teamlivefolder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.LiveFolders;

public class TeamLiveFolderActivity extends Activity {
	/*
	 * Uri relativo all'elenco di risorse
	 */
	private final static Uri LIST_TEAM_URI = Uri
			.parse("content://it.apogeo.android.cap08.teamlivefolder/team");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento all'Intent che ha causato l'esecuzione
		// dell'attività
		final Intent intent = getIntent();
		// Otteniamo il riferimento all'azione per verificare che si tratta di
		// quella di
		// attivazione del live folder
		final String action = intent.getAction();
		if (LiveFolders.ACTION_CREATE_LIVE_FOLDER.equals(action)) {
			// Creiamo l'Intent per l'attivazione del folder
			final Intent folderIntent = new Intent();
			// Determiniamo il nome del folder
			String folderName = getResources().getString(R.string.folder_name);
			folderIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_NAME, folderName);
			// Impostiamo l'icona da utilizzare
			folderIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_ICON,
					Intent.ShortcutIconResource.fromContext(this, R.drawable.icon));			
			// Impostiamo l'Uri peri dati da visualizzare
			folderIntent.setData(LIST_TEAM_URI);
			// Impostiamo la modalità
			folderIntent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_DISPLAY_MODE,
					LiveFolders.DISPLAY_MODE_LIST);					
			// Ritorniamo il riferimento all'Intent invocato
			setResult(RESULT_OK, folderIntent);
		} else {
			// L'attività non può essere eseguita in modo diverso da quello
			// relativo al live folder
			setResult(RESULT_CANCELED);
		}
		// Terminiamo 
		finish();
	}


}
package it.apogeo.android.cap04.launchertest;

import android.app.LauncherActivity;
import android.content.Intent;

public class LauncherTestActivity extends LauncherActivity {

	@Override
	protected Intent getTargetIntent() {
		// Forniamo l'Intent da considerare per l'elenco delle attività
		// da visualizzare nel Launcher
		Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
		launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		return launcherIntent;
	}
	
	

}
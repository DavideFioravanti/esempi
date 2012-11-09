package it.apogeo.android.cap04.categorytest;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class CategoryTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al LinearLayout
		final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.containerView);
		// Creiamo l'Intent di azione MAIN
		Intent intent = new Intent(Intent.ACTION_MAIN);
		// Aggiungiamo alle categorie quella della LAUNCHER
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// Invochiamo il PackageManager per ottenere l'elenco delle
		// attività della home
		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> infoList = packageManager.queryIntentActivities(
				intent, 0);
		Iterator<ResolveInfo> infoIterator = infoList.iterator();
		while (infoIterator.hasNext()) {
			final ResolveInfo info = infoIterator.next();
			CharSequence activityLabel = info.loadLabel(packageManager);
			Log.i("CategoryTestActivity", "Activity: " + activityLabel);
			// Aggiungiamo un Button di label data
			Button activityButton = new Button(this);
			activityButton.setText(activityLabel);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			activityButton.setLayoutParams(layoutParams);
			activityButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Creiamo il corrispondente Intent
					Intent launchIntent = new Intent();
					// Impostiamo il ComponentName
					String activityPackage = info.activityInfo.packageName;
					String activityName = info.activityInfo.name;
					ComponentName componentName = new ComponentName(activityPackage,activityName);
					launchIntent.setComponent(componentName);
					// Impostiamo il launchMode
					launchIntent.setFlags(info.activityInfo.flags);
					// Lo lanciamo
					startActivity(launchIntent);
				}

			});
			// Aggiungiamo il Button al layout
			linearLayout.addView(activityButton);
		}

	}
}
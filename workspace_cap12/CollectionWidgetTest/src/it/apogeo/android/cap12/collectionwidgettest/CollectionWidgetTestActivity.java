package it.apogeo.android.cap12.collectionwidgettest;

import it.apogeo.android.cap12.collectionwidgettest.TeamProviderMetaData.TeamTableMetaData;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class CollectionWidgetTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /**
     * Permette di inserire dei dati pseudocasuali nel ContentProvider
     * 
     * @param view Il Button premuto
     */
    public void insertData(View view){
    	ArrayList<ContentProviderOperation> batchOperations = new ArrayList<ContentProviderOperation>();
    	Random random = new Random();
    	for(int i=0;i<50;i++){
    		ContentValues values = new ContentValues();
    		values.put(TeamTableMetaData.NAME, "Name_"+random.nextInt());
    		values.put(TeamTableMetaData.CITY, "City_"+random.nextInt());
    		values.put(TeamTableMetaData.COUNTRY, "Country"+random.nextInt());
    		values.put(TeamTableMetaData.WEB_SITE, "Web_site"+random.nextInt());
    		ContentProviderOperation.Builder cpo = ContentProviderOperation.newInsert(TeamTableMetaData.CONTENT_URI).withValues(values);
    		if((i%10)==0){
    			cpo.withYieldAllowed(true);
    		}
    		Log.i("APP_WIDGET_COLLECTION", "COontentValues inserted"+values);
    		batchOperations.add(cpo.build());
    	}
    	// Eseguiamo le operazioni in batch
    	try {
			getContentResolver().applyBatch(TeamProviderMetaData.AUTHORITY, batchOperations);
			Toast okToast = Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT);
			okToast.show();
		} catch (Exception e) {
			Log.i("APP_WIDGET_COLLECTION", "ERRORE "+e);
			e.printStackTrace();
			Toast koToast = Toast.makeText(this, "Error inserting data", Toast.LENGTH_SHORT);
			koToast.show();			
		}
    }
}
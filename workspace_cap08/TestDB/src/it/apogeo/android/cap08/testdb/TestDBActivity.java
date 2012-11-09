package it.apogeo.android.cap08.testdb;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;

public class TestDBActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Creiamo il DataBase
       	SQLiteDatabase db = openOrCreateDatabase("MY_DATABASE", Context.MODE_PRIVATE,null);
       	// Verifichiamo se è stato appena creato eseguendo una query su una tabella
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder ();
        builder.setTables("team");
        Map<String,String> columnMap = new HashMap<String,String>();
        columnMap.put("id","_id");
        columnMap.put("nome","name");
        builder.setProjectionMap(columnMap);
        // Proviamo ad eseguire la query
        try{
        Cursor cursor = builder.query(db, null, null, null, null, null, null);
        while(cursor.moveToNext()){
        	int index = cursor.getColumnIndex("name");
        	String value = cursor.getString(index);
        	Log.i("TestDBActivity","Value:"+value);
        }
        cursor.close();
        }catch(SQLException sqle){
        	Log.i("TestDBActivity","DB DA CREARE");
        	StringBuilder createQuery = new StringBuilder();
        	createQuery.append("CREATE TABLE \"TEAM\" (");
        	createQuery.append("	    \"_id\" INTEGER PRIMARY KEY AUTOINCREMENT,");
        	createQuery.append("	    \"name\" TEXT NOT NULL,");
        	createQuery.append("	    \"city\" TEXT NOT NULL,");
        	createQuery.append("	    \"country\" TEXT NOT NULL,");
        	createQuery.append("	    \"web_site\" TEXT");
        	createQuery.append(")");
        	db.execSQL(createQuery.toString());
        }

    }
}
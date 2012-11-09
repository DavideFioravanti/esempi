package it.apogeo.android.cap08.teammanager;

import it.apogeo.android.cap08.teammanager.Team.TeamMetaData;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class TeamManagerActivity extends ListActivity {
	// Tag del Log
	private final static String TAG_LOG = "TeamManagerActivity";
	// Identificatore della versioe
	private final static int DB_VERSION = 1;
	// Identificatore del risultato di creazione
	private final static int CREATE_ACTIVITY_RESULT = 1;
	// Identificatore del risultato di update
	private final static int UPDATE_ACTIVITY_RESULT = 2;
	// Identificatore delle voci del Menu Contestuale
	private final static int DELETE_MENU_OPTION = 1;
	private final static int UPDATE_MENU_OPTION = 2;
	// Mapping tra i campi della tabella ed elementi del layout di riga
	private String[] FROMS = new String[] { TeamMetaData.NAME,
			TeamMetaData.CITY, TeamMetaData.COUNTRY, TeamMetaData.WEB_SITE };
	private int[] TOS = new int[] { R.id.teamName, R.id.teamCity,
			R.id.teamCountry, R.id.teamWebSite };
	/*
	 * Riferimento al DB
	 */
	private SQLiteDatabase db;
	/*
	 * Cursore dei risultati
	 */
	private Cursor cursor;
	/*
	 * Riferimento all'Adpater
	 */
	private CursorAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento al DB in lettura e scrittura
		db = dbHelper.getWritableDatabase();
		// Eseguiamo la query per estrarre tutte le informazioni dalla
		// tabella
		cursor = db.query(TeamMetaData.TABLE_NAME, TeamMetaData.COLUMNS, null,
				null, null, null, null);
		// Creiamo un Adapter con il cursore
		adapter = new SimpleCursorAdapter(this, R.layout.row_layout, cursor,
				FROMS, TOS);
		// Lo assegnamo alla ListView
		getListView().setAdapter(adapter);
		// Registriamo il Menu Contesuale
		registerForContextMenu(getListView());
	}

	@Override
	protected void onStart() {
		super.onStart();
		updateListView();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		// Liberiamo le risorse del cursor
		cursor.deactivate();
	}	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Chiudiamo il cursore
		cursor.close();
		// Chiudiamo il DB
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// L'unico possibile menu e' quello di creazione per cui non serve
		// fare controlli
		menu.add(Menu.FIRST, Menu.FIRST, Menu.FIRST, R.string.create_option);
		// Lo visualizziamo
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Dobbiamo gestire l'Intent in questo modo perchè lo dobbiamo
		// eseguire con o startActivityForResult e non semplicemente come
		// Intent
		Intent createIntent = new Intent(this, EditTeamActivity.class);
		startActivityForResult(createIntent, CREATE_ACTIVITY_RESULT);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// Creiamo il Menu da associare all'item
		int group = Menu.FIRST;
		menu.add(group, DELETE_MENU_OPTION, Menu.FIRST, R.string.delete_option);
		menu.add(group, UPDATE_MENU_OPTION, Menu.FIRST + 1,
				R.string.update_option);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		long teamId = info.id;
		switch (item.getItemId()) {
		case DELETE_MENU_OPTION:
			// Lo cancelliamo
			db = dbHelper.getWritableDatabase();
			db.delete("TEAM", "_id=" + teamId, null);
			// Aggiorniamo
			updateListView();
			return true;
		case UPDATE_MENU_OPTION:
			// Dobbiamo inviarel'Intent con il Team alla Activity per
			// l'editazione
			Cursor tmpCursor = db.query(TeamMetaData.TABLE_NAME,
					TeamMetaData.COLUMNS, "_id=" + teamId, null, null, null,
					null);
			if (tmpCursor.moveToNext()) {
				Intent updateIntent = new Intent(this, EditTeamActivity.class);
				Bundle teamBundle = new Bundle();
				Team team = new Team();
				team.id = teamId;
				team.name = tmpCursor.getString(tmpCursor
						.getColumnIndex(TeamMetaData.NAME));
				team.city = tmpCursor.getString(tmpCursor
						.getColumnIndex(TeamMetaData.CITY));
				team.country = tmpCursor.getString(tmpCursor
						.getColumnIndex(TeamMetaData.COUNTRY));
				team.webSite = tmpCursor.getString(tmpCursor
						.getColumnIndex(TeamMetaData.WEB_SITE));
				teamBundle.putParcelable("team", team);
				updateIntent.putExtra("team", teamBundle);
				startActivityForResult(updateIntent, UPDATE_ACTIVITY_RESULT);
			}
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case Activity.RESULT_OK:
			// Salviamo il risultato nel DB
			Bundle extra = data.getBundleExtra("team");
			Team team = (Team) extra.getParcelable("team");
			// Li inseriamo nel DB
			ContentValues values = new ContentValues();
			values.put(TeamMetaData.NAME, team.name);
			values.put(TeamMetaData.CITY, team.city);
			values.put(TeamMetaData.COUNTRY, team.country);
			values.put(TeamMetaData.WEB_SITE, team.webSite);
			db = dbHelper.getWritableDatabase();
			// A seconda del tipo di risposta facciamo un inserimento o un
			// update
			switch (requestCode) {
			case CREATE_ACTIVITY_RESULT:
				db.insert("TEAM", "web_site", values);
				break;
			case UPDATE_ACTIVITY_RESULT:
				db.update("TEAM", values, "_id="+team.id, null);
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

	/*
	 * Metodo di utilità che permettedi aggiornare il contenuto della ListView
	 */
	private void updateListView() {
		// Diciamo al Cursor di rieseguire la query
		cursor.requery();
		// Notifichiamo le View associte agli adapter di fare il refresh
		adapter.notifyDataSetChanged();
	}

	private final SQLiteOpenHelper dbHelper = new SQLiteOpenHelper(this,
			"TEAM_DB", null, DB_VERSION) {

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG_LOG, "Inizio Creazione DB");
			StringBuilder createQuery = new StringBuilder();
			createQuery.append("CREATE TABLE \"TEAM\" (");
			createQuery
					.append("	    \"_id\" INTEGER PRIMARY KEY AUTOINCREMENT,");
			createQuery.append("	    \"name\" TEXT NOT NULL,");
			createQuery.append("	    \"city\" TEXT NOT NULL,");
			createQuery.append("	    \"country\" TEXT NOT NULL,");
			createQuery.append("	    \"web_site\" TEXT");
			createQuery.append(")");
			db.execSQL(createQuery.toString());
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Nope
			Log.i(TAG_LOG, "Aggiornamento non implementato");
		}

	};

}

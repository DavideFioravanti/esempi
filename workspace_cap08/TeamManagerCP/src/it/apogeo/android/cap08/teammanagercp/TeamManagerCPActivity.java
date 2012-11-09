package it.apogeo.android.cap08.teammanagercp;

import it.apogeo.android.cap08.teammanagercp.Team.TeamMetaData;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class TeamManagerCPActivity extends ListActivity {
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
	 * Uri relativo all'elenco di risorse
	 */
	private final static Uri LIST_TEAM_URI = Uri
			.parse("content://it.apogeo.android.cap08.teamcontentprovider.TeamContentProvider/team");
	/*
	 * Riferimento all'Adpater
	 */
	private CursorAdapter adapter;
	/*
	 * Cursore dei risultati
	 */
	private Cursor cursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
			// Lo cancelliamo attraverso il ContentProvider
			Uri uriToDelete = Uri.withAppendedPath(LIST_TEAM_URI, "" + teamId);
			getContentResolver().delete(uriToDelete, null, null);
			// Aggiorniamo
			updateListView();
			return true;
		case UPDATE_MENU_OPTION:
			// Dobbiamo inviarel'Intent con il Team alla Activity per
			// l'editazione
			Uri uriToUpdate = Uri.withAppendedPath(LIST_TEAM_URI, "" + teamId);
			// Creiamo l'Intent
			Intent updateIntent = new Intent(this, EditTeamActivity.class);
			updateIntent.setData(uriToUpdate);
			startActivityForResult(updateIntent, UPDATE_ACTIVITY_RESULT);
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
			values.put("name", team.name);
			values.put("city", team.city);
			values.put("country", team.country);
			values.put("web_site", team.webSite);
			// A seconda del tipo di risposta facciamo un inserimento o un
			// update
			switch (requestCode) {
			case CREATE_ACTIVITY_RESULT:
				getContentResolver().insert(LIST_TEAM_URI, values);
				break;
			case UPDATE_ACTIVITY_RESULT:
				Uri uriToUpdate = Uri.withAppendedPath(LIST_TEAM_URI, ""
						+ team.id);
				getContentResolver().update(uriToUpdate, values, null, null);
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
		// Eseguiamo la query per estrarre tutte le informazioni dalla
		// tabella
		cursor = getContentResolver().query(LIST_TEAM_URI, null, null, null,
				null);
		// Creiamo un Adapter con il cursore
		adapter = new SimpleCursorAdapter(this, R.layout.row_layout, cursor,
				FROMS, TOS);
		// Lo assegnamo alla ListView
		getListView().setAdapter(adapter);
		// Notifichiamo le View associte agli adapter di fare il refresh
		adapter.notifyDataSetChanged();
	}
}
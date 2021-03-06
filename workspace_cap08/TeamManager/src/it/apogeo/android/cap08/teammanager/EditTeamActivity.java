/**
 * 
 */
package it.apogeo.android.cap08.teammanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * @author MASSIMO
 * 
 */
public class EditTeamActivity extends Activity {
	/*
	 * Identificatore del menu di salva
	 */
	private final static int SAVE_MENU_OPTION = 0;
	/*
	 * Identificatore del menu di annulla
	 */
	private final static int CANCEL_MENU_OPTION = 1;

	// Riferimento ai diversi campi di editazione
	private EditText nameEdit;
	private EditText cityEdit;
	private EditText countryEdit;
	private EditText websiteEdit;

	// Oggetto da creare o editare
	private Team team;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_layout);
		// Riferimento ai campi di editazione
		nameEdit = (EditText) findViewById(R.id.nameEdit);
		cityEdit = (EditText) findViewById(R.id.cityEdit);
		countryEdit = (EditText) findViewById(R.id.countryEdit);
		websiteEdit = (EditText) findViewById(R.id.webSiteEdit);
		// Verifichiamo la presenza di un dato associato all'intent di partenza
		Bundle teamBundle = getIntent().getBundleExtra("team");
		// Se diverso da null valorizziamo i vari campi
		if (teamBundle != null) {
			team = (Team) teamBundle.getParcelable("team");
			nameEdit.setText(team.name);
			cityEdit.setText(team.city);
			countryEdit.setText(team.country);
			websiteEdit.setText(team.webSite);
		} else {
			// Ne creiamo uno nuovo
			team = new Team();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Creiamo le due voci di salva ed annulla
		menu
				.add(Menu.FIRST, SAVE_MENU_OPTION, Menu.FIRST,
						R.string.save_option);
		menu.add(Menu.FIRST + 1, CANCEL_MENU_OPTION, Menu.FIRST + 1,
				R.string.cancel_option);
		// Lo visualizziamo
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// A seconda dell'id dell'item selezionato eseguiamo
		// le corrispondenti operazioni
		int itemId = item.getItemId();
		if (itemId == SAVE_MENU_OPTION) {
			// Dobbiamo passare le informazioni alla attivit� chiamante
			Intent data = new Intent();
			Bundle teamBundle = new Bundle();
			team.name = nameEdit.getText().toString();
			team.city = cityEdit.getText().toString();
			team.country = countryEdit.getText().toString();
			team.webSite = websiteEdit.getText().toString();
			teamBundle.putParcelable("team", team);
			data.putExtra("team", teamBundle);
			setResult(Activity.RESULT_OK, data);
			finish();
			// La gestione della selezione dell'item e' finita
			return true;
		} else if (itemId == CANCEL_MENU_OPTION) {
			// Cancelliamo l'operazione chiamando finish
			finish();
			// La gestione della selezione dell'item e' finita
			return true;
		} else {
			// Rimandiamo alla gestione classica
			return super.onOptionsItemSelected(item);
		}
	}

}

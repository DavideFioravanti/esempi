package it.apogeo.android.cap05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ExpandableListAdapterTestActivity extends Activity {

	// Elenco delle costanti per ciascun campo dei modelli
	private final static String FIRSTNAME_KEY = "firstname";
	private final static String LASTNAME_KEY = "lastname";
	private final static String ADDRESS_KEY = "address";
	private final static String ZIP_KEY = "zip";
	private final static String CELL_KEY = "cell";
	private final static String EMAIL_KEY = "email";

	// Array relativo alla definizione del layout dei gruppi
	private final static String[] GROUP_KEYS = { FIRSTNAME_KEY, LASTNAME_KEY };
	private final static int[] GROUP_VIEW_IDS = { R.id.firstname, R.id.lastname };

	// Array relativo alla definizione del layout dei gruppi
	private final static String[] CHILD_KEYS = { ADDRESS_KEY, ZIP_KEY,
			CELL_KEY, EMAIL_KEY };
	private final static int[] CHILD_VIEW_IDS = { R.id.address, R.id.zip,
			R.id.cell, R.id.email };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Creiamo il SimpleExpandableAdapter
		ExpandableListAdapter expAdapter =
		new SimpleExpandableListAdapter(this, createGroupMap(), R.layout.expanded_layout,
				R.layout.collapsed_layout, GROUP_KEYS, GROUP_VIEW_IDS, createDataMap(),
				R.layout.child_layout, R.layout.last_child_layout, CHILD_KEYS,
				CHILD_VIEW_IDS);
		// Otteniamo il riferimento alla ExpandableListView
		ExpandableListView expListView = (ExpandableListView)findViewById(R.id.expandableListView);
		// Assegnamo l'adapter
		expListView.setAdapter(expAdapter);
	}

	public List<? extends Map<String, String>> createGroupMap() {
		List<Map<String, String>> groupMap = new ArrayList<Map<String, String>>();
			for(int i = 0;i<10;i++){
				Map<String,String> groupData = new HashMap<String,String>();
				groupData.put(FIRSTNAME_KEY, "First_"+i);
				groupData.put(LASTNAME_KEY, "Last_"+i);
				groupMap.add(groupData);
			}
		return groupMap;

	}
	
	public List<? extends List<? extends Map<String, String>>> createDataMap() {
		List<List<Map<String, String>>> dataMap = new ArrayList<List<Map<String, String>>>();
		for(int i = 0;i<10;i++){
			List<Map<String, String>> itemMap = new ArrayList<Map<String, String>>();
			for(int j = 0;j<5;j++){
				Map<String,String> groupData = new HashMap<String,String>();
				groupData.put(ADDRESS_KEY, "Address_"+i+"_"+j);
				groupData.put(ZIP_KEY, "Cap_"+i+"_"+j);
				groupData.put(EMAIL_KEY, "Email_"+i+"_"+j);
				groupData.put(CELL_KEY, "Cell_"+i+"_"+j);
				itemMap.add(groupData);
			}			
			dataMap.add(itemMap);			
		}
		return dataMap;
	}	
}
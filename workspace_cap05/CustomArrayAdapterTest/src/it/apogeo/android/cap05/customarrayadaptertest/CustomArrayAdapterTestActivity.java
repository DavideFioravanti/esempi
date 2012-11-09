package it.apogeo.android.cap05.customarrayadaptertest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomArrayAdapterTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Impostiamo il Layout
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla ListView
		ListView listView = (ListView) findViewById(R.id.arrayList);
		// Creiamo l'ArrayAdapter
		ArrayAdapter<CustomItem> arrayAdapter = new ArrayAdapter<CustomItem>(
				this, R.layout.custom_row, R.id.firstnameLabel, createItems()) {
			
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent){	
				// Caso brutale
				//return getViewNoOptimized(position,convertView,parent);
				// Caso lazy
				//return getViewLazy(position,convertView,parent);
				// Caso di utilizzo dell'Holdedr
				return getViewHolder(position,convertView,parent);
			}

			public View getViewNoOptimized(int position, View convertView, ViewGroup parent) {
				// Otteniamo l'elemento i-esimo
				CustomItem item = getItem(position);
				// Otteniamo il riferimento alla View da parserizzare
				LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowView = inflater.inflate(R.layout.custom_row, null);
				// Otteniamo i campi da riempire
				TextView firstnameView = (TextView)rowView.findViewById(R.id.firstname);
				TextView lastnameView = (TextView)rowView.findViewById(R.id.lastname);
				// Assegnamo i valori
				firstnameView.setText(item.firstname);
				lastnameView.setText(item.lastname);
				// Ritorniamo la View
				return rowView;
			}
			
			public View getViewLazy(int position, View convertView, ViewGroup parent) {
				// Se non presente una istanza della View la creiamo attraverso
				// inflating. Se già presente la riutilizziamo
				if(convertView==null){
					// Otteniamo il riferimento alla View da parserizzare
					LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = inflater.inflate(R.layout.custom_row, null);					
				}
				// Otteniamo l'elemento i-esimo
				CustomItem item = getItem(position);
				// Otteniamo i campi da riempire
				TextView firstnameView = (TextView)convertView.findViewById(R.id.firstname);
				TextView lastnameView = (TextView)convertView.findViewById(R.id.lastname);
				// Assegnamo i valori
				firstnameView.setText(item.firstname);
				lastnameView.setText(item.lastname);
				// Ritorniamo la View
				return convertView;
			}	
			
			public View getViewHolder(int position, View convertView, ViewGroup parent) {
				// Creiamo il riferimento all'holder
				ViewHolder viewHolder = null;
				// Se non presente una istanza della View la creiamo attraverso
				// inflating. Se già presente la riutilizziamo
				if(convertView==null){
					// Otteniamo il riferimento alla View da parserizzare
					LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = inflater.inflate(R.layout.custom_row, null);
					// Creiamo un Holder associato
					viewHolder = new ViewHolder();
					// Assegnamo i riferimenti alle textView
					viewHolder.firstnameView = (TextView)convertView.findViewById(R.id.firstname);
					viewHolder.lastnameView = (TextView)convertView.findViewById(R.id.lastname);
					// Lo assegnamo come Tag della View
					convertView.setTag(viewHolder);
				}else{
					// La View esiste già per cui possiede già l'holder
					viewHolder = (ViewHolder)convertView.getTag();
				}
				// Otteniamo l'elemento i-esimo
				CustomItem item = getItem(position);
				// Assegnamo i valori
				viewHolder.firstnameView.setText(item.firstname);
				viewHolder.lastnameView.setText(item.lastname);
				// Ritorniamo la View
				return convertView;
			}			

		};
		// Lo impostiamo come adapter della listView
		listView.setAdapter(arrayAdapter);
	}

	private static class CustomItem {
		public String firstname;
		public String lastname;
	}

	private CustomItem[] createItems() {
		CustomItem[] items = new CustomItem[20];
		for (int i = 0; i < items.length; i++) {
			items[i] = new CustomItem();
			items[i].firstname = "Firstname_" + i;
			items[i].lastname = "Lastname_" + i;
		}
		return items;
	}
	
	private static class ViewHolder{
		public TextView firstnameView;
		public TextView lastnameView;
	}
}
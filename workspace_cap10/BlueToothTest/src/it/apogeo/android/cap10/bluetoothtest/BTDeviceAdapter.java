/**
 * 
 */
package it.apogeo.android.cap10.bluetoothtest;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author MASSIMO
 *
 */
public class BTDeviceAdapter extends BaseAdapter {
	
	private final List<BluetoothDevice> deviceList;
	
	private Context context;
	

	public BTDeviceAdapter(Context context,Set<BluetoothDevice> devices){
		deviceList = new LinkedList<BluetoothDevice>(devices);
		this.context=context;
	}
	

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		// Ritorniamo il numero di elementi
		return deviceList.size();
	}


	public Object getItem(int position) {
		return deviceList.get(position);
	}


	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		// Verifichiamo la presenza della View associata alla posizione
		if(convertView==null){
			// Se non presente facciamo l'inflate del corrispondente elemento
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView = layoutInflater.inflate(R.layout.bt_list, null);
		}
		// Gestiamo il corrispondente Holder
		BTHolder holder = (BTHolder)convertView.getTag();
		if(holder==null){
			// Se non esistente creiamo l'holder
			holder = new BTHolder();
			holder.btAddress = (TextView)convertView.findViewById(R.id.btAddress);
			// Lo assegnamo come tag
			convertView.setTag(holder);
		}
		// Usiamo l'holder per assegnare i valori
		BluetoothDevice device = deviceList.get(position);
		holder.btAddress.setText(device.getAddress());
		return convertView;
	}
	
	private final static class BTHolder{
		public TextView btAddress;
	}	


}

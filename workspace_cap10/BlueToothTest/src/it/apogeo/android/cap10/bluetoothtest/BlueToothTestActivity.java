package it.apogeo.android.cap10.bluetoothtest;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BlueToothTestActivity extends ListActivity {
	/*
	 * Tag del Log
	 */
	private final static String LOG_TAG = "BlueToothTestActivity";
	/*
	 * Riferimento al servizio BlueTooth
	 */
	private BluetoothAdapter btAdapter;
	/*
	 * Riferimento all'Adapter
	 */
	private BTDeviceAdapter btDeviceAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Otteniamo il riferimento al BluetoothAdapter
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if(btAdapter==null){
        	Toast.makeText(this, "BlueTooth not supported", Toast.LENGTH_LONG).show();
        	return;
        }
        // Otteniamo il riferimento al servizio di BlueTooth che mettiamo a disposizione
        // attraverso un adapter
        btDeviceAdapter = new BTDeviceAdapter(this, btAdapter.getBondedDevices());
        Log.i(LOG_TAG, "Custom Adapter Created");
        // Lo impostiamo come adapter della lista
        getListView().setAdapter(btDeviceAdapter);
    }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	

    
	
	   
    
}
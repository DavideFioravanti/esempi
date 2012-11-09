package it.apogeo.android.cap10.custompermissiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomPermissionTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /**
     * Metodo invocato in corrispondenza della pressione del pulsante
     * @param button Riferimneto al Button premuto
     */
    public void goToProtectedActivity(View button){
    	Intent intent = new Intent("it.apogeo.android.cap10.action.PROTECTED_ACTION");
    	startActivity(intent);
    }
}
/**
 * 
 */
package it.apogeo.android.cap08.completepreferencestest;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author MASSIMO
 *
 */
public class MyPreferenceActivity extends PreferenceActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource (R.xml.my_preferences);
    }	

}

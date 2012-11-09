package it.apogeo.android.cap06.intercepteventtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class InterceptEventTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Impostiamo il layout principale
        setContentView(R.layout.main);
        // Otteniamo il riferimento al linearLayout
        LinearLayout container = (LinearLayout)findViewById(R.id.container);
        // Creiamo una customizzazione del Button
        View customView =new View(this){

			/* (non-Javadoc)
			 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
			 */
			@Override
			public boolean onTouchEvent(MotionEvent event) {
				// Ritorniamo false ovvero non consumiamo
				Log.i("InterceptTest","View onTouchEvent  "+event);
				return false;
			}
        	
        };
        container.addView(customView);
        // Aggiungiamo il container
        setContentView(container);
    }
}
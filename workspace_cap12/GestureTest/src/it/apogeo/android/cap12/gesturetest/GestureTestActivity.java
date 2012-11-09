package it.apogeo.android.cap12.gesturetest;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.Toast;

public class GestureTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Carichiamo la libreria relative alla Gesture
        final GestureLibrary gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLibrary.load()) {
        	// Se non vengono caricate usciamo dall'applicazione
            finish();
        }        
        // In caso di caricamento avvenuto con successo otteniamo il riferimento 
        // alla GestureOverlayView 
        GestureOverlayView gestureView = (GestureOverlayView)findViewById(R.id.gestureView);
        // Registriamo un listener per il riconoscimento delle gesture
        gestureView.addOnGesturePerformedListener(new OnGesturePerformedListener(){

			@Override
			public void onGesturePerformed(GestureOverlayView gestView, Gesture gesture) {
				// Una volta ottenuta una gesture ne chiediamo il riconoscimento
				// alla GestureLibrary la quale ci ritorna un elenco di Prediction
				ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
				// Se la gesture è stata riconosciuta possono esistere diverse opzioni
				// ciasuna caratterizzata da un punteggio in base a quanto l'azione
				// dell'utente si avvicina
				if(predictions!=null && predictions.size()>0){
					// In questo caso prendiamo quella con score più alto che è nella
					// posizione 0. 
					Prediction bestPrediction = predictions.get(0);
					// Ne otteniamo il nome e lo score e la visualizziamo
					Toast.makeText(GestureTestActivity.this, "Captured "+bestPrediction.name+" with score "+bestPrediction.score, Toast.LENGTH_SHORT).show();
				}else{
					// Non e' stata riconosciuta alcuna Gesture
					Toast.makeText(GestureTestActivity.this, "Unknown gesture", Toast.LENGTH_SHORT).show();
				}
			}
        	
        });
        
        
    }
}
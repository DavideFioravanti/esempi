package it.apogeo.android.cap06.viewlistenertest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class ViewListenerTestActivity extends Activity {
	/*
	 * Tag per il Log
	 */
	private final static String DEBUG_TAG = "EVENT_LISTENER_TEST";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Otteniamo il riferimento alla prima TextView
		final View source1 = findViewById(R.id.source1);
		// Otteniamo il riferimento ad un Button
		final View source2 = findViewById(R.id.source2);
		// Otteniamo il riferimento ad una TextView
		final View source3= findViewById(R.id.source2);		
		// Associamo un tag alle sorgenti per poterle riconoscere
		source1.setTag("Source1");
		source2.setTag("Source2");
		source3.setTag("Source3");
		// Gestiamo l'evento di click
		OnClickListener clickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// Visualizziamo il messaggio di log
				Log.i(DEBUG_TAG, "onClick "+v.getTag());
			}
			
		};
		source1.setOnClickListener(clickListener);
		source2.setOnClickListener(clickListener);
		source3.setOnClickListener(clickListener);
		// Gestiamo l'evento di long click
		OnLongClickListener longClickListener = new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				// Visualizziamo il messaggio di log
				Log.i(DEBUG_TAG, "onLongClick "+v.getTag());
				// Ritorniamo false in quanto non abbiamo consumato
				// l'evento che  viene quindi propagato				
				return false;
			}
			
		};
		source1.setOnLongClickListener(longClickListener);
		source2.setOnLongClickListener(longClickListener);
		source3.setOnLongClickListener(longClickListener);	
		// Gestiamo l'evento di focus change
		OnFocusChangeListener focusChangeListener = new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Visualizziamo messaggio di log
				Log.i(DEBUG_TAG, "onFocusChange "+v.getTag()+" hasFocus:"+hasFocus);
			}
			
		};
		source1.setOnFocusChangeListener(focusChangeListener);
		source2.setOnFocusChangeListener(focusChangeListener);
		source3.setOnFocusChangeListener(focusChangeListener);		
		// Gestiamo l'evento di key
		OnKeyListener keyListener = new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.i(DEBUG_TAG, "onKey "+v.getTag()+" keyCode:"+keyCode+" unicode:"+event.getUnicodeChar());
				// Non consumiamo l'evento
				return false;
			}
			
		};
		source1.setOnKeyListener(keyListener);
		source2.setOnKeyListener(keyListener);
		source3.setOnKeyListener(keyListener);
		// Gestiamo l'evento di touch
		OnTouchListener touchListener = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Azione associata
				int action = event.getAction();
				// Identificativo del tocco
				int touchId = action&MotionEvent.ACTION_POINTER_ID_MASK;
				Log.i(DEBUG_TAG, "onTouch "+v.getTag()+" touchId:"+touchId);				
				// Non consumiamo l'evento
				return false;
			}
			
		};
		source1.setOnTouchListener(touchListener);
		source2.setOnTouchListener(touchListener);
		source3.setOnTouchListener(touchListener);
	}

}
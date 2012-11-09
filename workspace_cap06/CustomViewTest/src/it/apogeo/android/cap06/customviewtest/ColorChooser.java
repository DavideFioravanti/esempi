/**
 * 
 */
package it.apogeo.android.cap06.customviewtest;

import it.apogeo.android.cap06.customviewtest.IntegerInputTouch.OnInputTouchValueListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;



/**
 * Classe che descrive la creazione di una CompoundView che permette la scelta
 * delle componenti RGB di un colore
 * 
 * @author Utente
 * 
 */
public class ColorChooser extends LinearLayout implements
		OnInputTouchValueListener {

	// Tag del log
	private final static String LOG_TAG = "ColorChooser";

	/*
	 * Colore dei dafault (bianco)
	 */
	private final static int DEFAULT_COLOR = 0xFFFFFFFF;

	/**
	 * Interfaccia che dovrà essere implementata dagli ascoltatori del
	 * ColorChooser
	 * 
	 */
	public interface OnColorSelectedListener {

		/**
		 * Invocato al momento della modifica di colore con il ColorChooser
		 * 
		 * @param src
		 *            Riferimento al ColorChooser sorgente dell'evento
		 * @param newColor
		 *            Colore selezionato
		 */
		public void onColorChanged(ColorChooser src, int newColor);
	}

	/*
	 * Riferimento all'eventuale listener
	 */
	private OnColorSelectedListener listener;

	/*
	 * Componenti del colore
	 */
	private int red;
	private int green;
	private int blue;

	/**
	 * @param context
	 */
	public ColorChooser(Context context) {
		super(context);
		// Otteniamo il layout facendo un Inflate
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater
				.inflate(R.layout.chooser_layout, this, false);
		addView(view);
		// Ci registriamo come ascoltatori delle componenti
		registerComponentListener(view);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ColorChooser(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Leggiamo il valore dell'attributo relativo al colore
		// iniziale
		TypedArray attrValues = context.obtainStyledAttributes(attrs,
				R.styleable.ColorChooser);
		int startColor = attrValues.getColor(
				R.styleable.ColorChooser_startColor, DEFAULT_COLOR);
		red = Color.red(startColor);
		green = Color.green(startColor);
		blue = Color.blue(startColor);
		attrValues.recycle();
		setSelectedColor(Color.rgb(red, green, blue));
		// Otteniamo il layout facendo un Inflate
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater
				.inflate(R.layout.chooser_layout, this, false);
		addView(view);
		// Ci registriamo come ascoltatori delle componenti
		registerComponentListener(view);
	}

	/**
	 * @return the selectedColor
	 */
	public int getSelectedColor() {
		return Color.rgb(red, green, blue);
	}

	/**
	 * @param selectedColor
	 *            the selectedColor to set
	 */
	public void setSelectedColor(int selectedColor) {
		// Otteniamo le componenti a partire dal colore
		red = Color.red(selectedColor);
		green = Color.green(selectedColor);
		blue = Color.blue(selectedColor);
		invalidate();
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setOnColorSelectedListener(OnColorSelectedListener listener) {
		this.listener = listener;
	}

	public void dispatchOnInputTouchEvent() {
		if (listener != null) {
			listener.onColorChanged(this, getSelectedColor());
		}
	}

	@Override
	public void onValueChanged(IntegerInputTouch src, int value) {
		int sourceId = src.getId();
		if (sourceId == R.id.redBar) {
			red = value;
		} else if (sourceId == R.id.greenBar) {
			green = value;
		} else if (sourceId == R.id.blueBar) {
			blue = value;
		} else {
			Log.w(LOG_TAG, "Source not known. Check layout or configuration");
		}
		dispatchOnInputTouchEvent();
		invalidate();
	}

	/*
	 * Metodo di utilità per la creazione del layout del ColorChooser dal
	 * documento XML
	 */
	private void registerComponentListener(View view) {
		IntegerInputTouch redInput = (IntegerInputTouch)( view
				.findViewById(R.id.redBar));
		redInput.setOnInputTouchValueListener(this);
		IntegerInputTouch greenInput = (IntegerInputTouch)( view
				.findViewById(R.id.greenBar));
		greenInput.setOnInputTouchValueListener(this);
		IntegerInputTouch blueInput = (IntegerInputTouch)( view
				.findViewById(R.id.blueBar));
		blueInput.setOnInputTouchValueListener(this);
	}

}

package it.apogeo.android.cap06.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;




/**
 * Classe che descrive una view che permette di selezionare un valore in un
 * range attraverso un evento di touch. Gli attributi di questo componente
 * saranno quelli che permettono di specificare il valore minimo e massimo del
 * range. Il valore corrente verrà visualizzato sul compomente stesso
 * 
 * @author Utente
 * 
 */
public class IntegerInputTouch extends View {

	/**
	 * Interfaccia degli ascoltatori dell'oggetto IntegerInputTouch
	 * 
	 * @author Utente
	 * 
	 */
	public interface OnInputTouchValueListener {

		/**
		 * Viene invocato in corrispondenza della variazione del valore
		 * selezionato
		 * 
		 * @param src
		 *            Sorgente dell'evento
		 * @param value
		 *            Nuovo valore selezionato
		 */
		public void onValueChanged(IntegerInputTouch src, int value);

	}

	/*
	 * Tag per il log
	 */
	private final static String LOG_TAG = "IntegerInputTouch";

	/*
	 * Altezza di default del componente
	 */
	public static final int DEFAULT_MEASURE_HEIGHT = 40;

	/*
	 * Colore di sfondo di Default
	 */
	public static final int DEFAULT_BG_COLOR = 0x00DDDDDD;

	/*
	 * Colore del testo di Default
	 */
	public static final int DEFAULT_TEXT_COLOR = 0xFF000000;

	/*
	 * Valore da utilizzare come padding
	 */
	public static final int PADDING_SIZE = 4;

	/*
	 * Valore di default
	 */
	public static final int MIN_DEFAULT_VALUE = 0;
	public static final int MAX_DEFAULT_VALUE = 100;
	/*
	 * Valore minimo tra cui scegliere
	 */
	private int minValue;

	/*
	 * Valore massimo tra cui scegliere
	 */
	private int maxValue;

	/*
	 * Drawable da utilizzare per la barra
	 */
	private ClipDrawable barDrawable;

	/*
	 * Valore correntemente selezionato
	 */
	private int selectedValue;
	
	/*
	 * Colore del testo
	 */
	private int textColor;

	/*
	 * Riferimento all'eventiale ascoltatore
	 */
	private OnInputTouchValueListener listener;

	public IntegerInputTouch(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// Leggiamo gli attributi
		readAttributes(context, attrs);
	}

	public IntegerInputTouch(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Leggiamo gli attributi
		readAttributes(context, attrs);
	}

	public IntegerInputTouch(Context context) {
		super(context);
		minValue = MIN_DEFAULT_VALUE;
		maxValue = MAX_DEFAULT_VALUE;
	}

	/**
	 * @return Valore selezionato
	 */
	public int getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(int selectedValue) {
		if (selectedValue >= minValue && selectedValue <= maxValue) {
			this.selectedValue = selectedValue;
			// Invalidiamo la View
			invalidate();
		} else {
			Log.w(LOG_TAG, selectedValue + " out of range[" + minValue + "-"
					+ maxValue + "]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Otteniamo il valore relativo alla x del click
		float xValue = event.getX();
		// Gestiamo la visualizzazione della barra
		manageXValue(xValue);
		// Calcoliamo il valore da visualizzare in corrispondenza
		invalidate();
		// Consumiamo l'evento
		return true;
	}

	/**
	 * Permette di registrare un ascoltatore dell'elemento
	 * 
	 * @param listener
	 *            Riferimento all'ascoltatore
	 */
	public void setOnInputTouchValueListener(OnInputTouchValueListener listener) {
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// Gestione della larghezza.
		int widthValue = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int measuredWidth = widthValue;
		if (widthMode != MeasureSpec.EXACTLY) {
			measuredWidth = maxValue - minValue;
		}
		// Per l'altezza invece abbiamo una dimensione definita
		int heightValue = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int measureHeight = heightValue;
		if (heightMode != MeasureSpec.EXACTLY) {
			measureHeight = DEFAULT_MEASURE_HEIGHT;
		}
		// La ritorniamo come misura preferita
		setMeasuredDimension(measuredWidth, measureHeight);
	}
	
	

	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Stampiamo il valore del testo
		Paint paint = new Paint();
		paint.setColor(textColor);
		// Posizione del testo 
		float y = (paint.getFontMetrics().bottom+getHeight())/2;
		// Visualizziamo il testo in posizione centrale
		canvas.drawText(""+getSelectedValue(), getWidth()/2, y, paint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	public void dispatchOnInputTouchEvent() {
		if (listener != null) {
			listener.onValueChanged(this, selectedValue);
		}
	}

	/*
	 * Metodo di utilità che permette la lettura degli attributi
	 */
	private final void readAttributes(Context context, AttributeSet attrs) {
		// Leggiamo i valori dagli attributi
		TypedArray values = context.obtainStyledAttributes(attrs,
				R.styleable.IntegerInputTouch);
		// Leggiamo i valori
		minValue = values.getInt(R.styleable.IntegerInputTouch_minValue,
				MIN_DEFAULT_VALUE);
		// Al massimo aggiungiamo 1 per comprenderlo tra i possibili valori
		maxValue = values.getInt(R.styleable.IntegerInputTouch_maxValue,
				MAX_DEFAULT_VALUE) + 1;
		// Otteniamo il riferimento al Drawable da usare come barra
		Drawable drawable = values
				.getDrawable(R.styleable.IntegerInputTouch_barDrawable);
		barDrawable = new ClipDrawable(drawable, Gravity.LEFT,
				ClipDrawable.HORIZONTAL);
		if (barDrawable == null) {
			Log.e(LOG_TAG,
					"A Drawable must be present as barDrawable. Check layout");
			throw new IllegalStateException(
					"A Drawable must be present as barDrawable. Check layout");
		}
		setBackgroundDrawable(barDrawable);
		// Colore del testo da utilizzare
		textColor = values.getColor(R.styleable.IntegerInputTouch_textColor,
				DEFAULT_TEXT_COLOR);
		// Rendiamo i valori disponibili per successive letture
		values.recycle();
	}

	/*
	 * Permette di visualizzare il contenuto della barra data la x selezionata
	 */
	private void manageXValue(float xValue) {
		// Facciamo la proporzione per calcolare il livello associato
		int level = (int) (10000 * xValue) / getWidth();
		barDrawable.setLevel(level);
		// Calcoliamo il nuovo valore selezionato
		int oldValue = selectedValue;
		selectedValue = (int) ((maxValue - minValue) * xValue) / getWidth();
		// Notifichiamo l'evento se il valore è cambiato
		if (selectedValue != oldValue) {
			dispatchOnInputTouchEvent();
		}
	}

}

package it.apogeo.android.cap05.customlayoutproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author MASSIMO CARLI
 * 
 */
public class FlowLayout extends ViewGroup {
	
	/*
	 * Larghezza di default
	 */
	private static final int DEFAULT_LAYOUT_WIDTH = 100;
	
	/*
	 * Altezza di default
	 */
	private static final int DEFAULT_LAYOUT_HEIGHT = 100;
	
	/*
	 * Larghezza di default delle View contenute 
	 */
	private int defaultWidth = DEFAULT_LAYOUT_WIDTH;
	
	/*
	 * Altezza di default delle View contenute 
	 */
	private int defaultHeight = DEFAULT_LAYOUT_HEIGHT;	

	/**
	 * Classe interna di gestione dei parametri di layout che una View contenuta
	 * potrà avere
	 * 
	 */
	public class LayoutParams extends ViewGroup.LayoutParams {

		/**
		 * Se a true il corrispondente elemento viene possizionato nella riga
		 * successiva
		 */
		public boolean isBreak = false;

		/**
		 * Larghezza massima della View
		 */
		public int maxWidth = 0;

		/**
		 * Altezza massima della View
		 */
		public int maxHeight = 0;
		
		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			// Leggiamo i valori dei parametri definiti come attrs
			// nel corrispondente file XML. Otteniamo il riferimento
			// all'insieme dei valori attraverso il Context
			TypedArray attrValues = context.obtainStyledAttributes(attrs,
					R.styleable.FlowLayout);
			// Il primo riguarda l'andata a capo o meno
			isBreak = attrValues.getBoolean(R.styleable.FlowLayout_isBreak, false);
			// Larghezza massima
			maxWidth = attrValues.getInt(R.styleable.FlowLayout_maxWidth, -1);
			// Altezza massima
			maxHeight = attrValues.getInt(R.styleable.FlowLayout_maxHeight, -1);
			// Facciamo in modo che le informazioni possano essere
			Log.i("FlowLayout", "attrValues "+attrValues);
			// riutilizzate
			attrValues.recycle();
		}
		
		public LayoutParams(LayoutParams layoutParams) {
			super(layoutParams);
			// Il primo riguarda l'andata a capo o meno
			isBreak = layoutParams.isBreak;
			// Larghezza massima
			maxWidth = layoutParams.maxWidth;
			// Altezza massima
			maxHeight = layoutParams.maxHeight;			
		}

		public LayoutParams(int width, int height) {
			super(width, height);
			// Impostiamo i valori di default
			isBreak = false;
			// Larghezza massima
			maxWidth = DEFAULT_LAYOUT_WIDTH;
			// Altezza massima
			maxHeight = DEFAULT_LAYOUT_HEIGHT;
		}

	

	}

	public FlowLayout(Context context) {
		super(context);
		// Gestione della preview
		//managePreview();		
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray attrValues = context.obtainStyledAttributes(attrs,
				R.styleable.FlowLayout);
		// Larghezza di default
		defaultWidth = attrValues.getInt(R.styleable.FlowLayout_defaultWidth, DEFAULT_LAYOUT_WIDTH);
		// Altezza di default
		defaultHeight = attrValues.getInt(R.styleable.FlowLayout_defaultHeight, DEFAULT_LAYOUT_HEIGHT);
		// Facciamo in modo che le informazioni possano essere
		// riutilizzate
		attrValues.recycle();	
		// Gestione della preview
		//managePreview();		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// In base alle dimensioni a disposizione disponiamo i vari
		// figli. 
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			// Verifichiamo se questa View ha definito delle informazioni
			// relativamente al layout
			LayoutParams viewLayoutParams = (LayoutParams)child.getLayoutParams();
			// Se è specificata una larghezza massia utilizziamo l'opzione AT_MOST mentre
			// in caso contrario, utilizziamo la UNSPECIFIED
			int childWidthSpec = 0;
			if(viewLayoutParams.maxWidth<0){
				// Caso di default
				childWidthSpec = MeasureSpec.makeMeasureSpec(defaultWidth, MeasureSpec.UNSPECIFIED);
			}else{
				// Caso con vincolo
				childWidthSpec = MeasureSpec.makeMeasureSpec(viewLayoutParams.maxWidth, MeasureSpec.AT_MOST);
			}
			// Stessa cosa per l'altezza
			int childHeightSpec = 0;
			if(viewLayoutParams.maxHeight<0){
				// Caso di default
				childHeightSpec = MeasureSpec.makeMeasureSpec(defaultHeight, MeasureSpec.UNSPECIFIED);
			}else{
				// Caso con vincolo
				childHeightSpec = MeasureSpec.makeMeasureSpec(viewLayoutParams.maxHeight, MeasureSpec.AT_MOST);
			}
			child.measure(childWidthSpec, childHeightSpec);
		}		
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// In base alle dimensioni delle View creiamo il FlowLayout
		int currentX = 0;
		int currentY = 0;
		int maxHeight = 0;
		int totalWidth = MeasureSpec.getSize(getMeasuredWidth());
		for (int i = 0; i < getChildCount(); i++) {
			// Riferimento al figlio i-esimo
			View child = getChildAt(i);
			// Otteniamo le informazioni di Layout
			LayoutParams viewLayoutParams = (LayoutParams)child.getLayoutParams();
			// Otteniamo le dimensioni
			int measuredWidth =  child.getMeasuredWidth();
			int measuredHeight =  child.getMeasuredHeight();
			if(currentX+measuredWidth>totalWidth || viewLayoutParams.isBreak){
				// In questo caso andiamo alla riga sottostante
				currentX = 0;
				currentY+=maxHeight;
				maxHeight = 0;
				child.layout(currentX, currentY, currentX+measuredWidth, currentY+measuredHeight);
			}else{
				// Altrimenti mettiamo il pulsante nella successiva
				// posizione
				child.layout(currentX, currentY, currentX+measuredWidth, currentY+measuredHeight);
				maxHeight = (maxHeight<measuredHeight)?measuredHeight:maxHeight;
			}
			currentX+=measuredWidth;
		}
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(),attrs);
	}

	@Override
	protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
		return new LayoutParams(p.height,p.width);
	}

	/*
	 * Metodo di utilita' che permette di gestire una eventuale Preview nell'editor di layout
	 */
	private void managePreview(){
		if(isInEditMode()){
			// In questo caso inseriamo alcuni componenti per verificarne il funzionamento
			FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,FlowLayout.LayoutParams.WRAP_CONTENT);
			FlowLayout.LayoutParams lpBreak = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,FlowLayout.LayoutParams.WRAP_CONTENT);
			lpBreak.isBreak = true;
			for(int i=0;i<10;i++){
				Button tmp = new Button(getContext());
				tmp.setText("Text Button "+i);
				tmp.setLayoutParams(lp);
				// Impostiamo il isBreak a vero ogni 4 (esempio)
				if((i%4)==0){
					tmp.setLayoutParams(lpBreak);
				}else{
					tmp.setLayoutParams(lp);
				}
				// Li aggiungiamo al container
				addView(tmp);
			}
			invalidate();
		}else{
			// In questo caso non facciamo nulla
		}
	}
	
}

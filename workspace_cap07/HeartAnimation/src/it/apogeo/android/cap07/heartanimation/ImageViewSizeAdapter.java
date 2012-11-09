package it.apogeo.android.cap07.heartanimation;

import it.massimocarli.andlib.utility.LogUtil;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * Questa classe permette di utilizzare una proprieta' di tipo intero come
 * dimensione di una View passata come parametro
 *  
 * 
 * @author Massimo Carli
 *
 */
public class ImageViewSizeAdapter {
	
	/*
	 * Riferimento alla View wrappata
	 */
	private View adaptee;
	
	/**
	 * Crea un ImageViewRectAdapter a partire da una View
	 * @param adaptee La View da gestire
	 */
	public ImageViewSizeAdapter(View adaptee){
		this.adaptee = adaptee;
	}
	
	/**
	 * Impostiamo la dimensione della View
	 * 
	 * @param size La dimensione
	 */
	public void setSize(int size){
		// Determiniamo la size corrente della View
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
		layoutParams.gravity = Gravity.CENTER;
		adaptee.setLayoutParams(layoutParams);
		LogUtil.i(this, "Upate with size"+size);
	}


}

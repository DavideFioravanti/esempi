/**
 * 
 */
package it.apogeo.android.cap05.gallerytest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * @author Utente
 * 
 */
public class ImageSpinnerAdapter extends AbstractSpinnerAdapter<Integer> {

	/*
	 * Riferimento agli interi id delle immagini
	 */
	private Integer[] imageIds;

	/*
	 * Riferimento all'eventuale background
	 */
	private int backgroundResource;

	/**
	 * Crea un ImageSpinnerAdapter a partire da un array di
	 * 
	 * @param context
	 */
	public ImageSpinnerAdapter(Context context, Integer[] imageIds) {
		super(context);
		// Salviamo il riferimento agli id
		this.imageIds = imageIds;
		// Verifichiamo se esiste un attributo relativo alla risorsa
		// da usare come background delle View ritornate
		TypedArray attrs = context.obtainStyledAttributes(R.styleable.GalleryTest);
		// Leggiamo il valore corrispondente usando quello specificato come default
		backgroundResource = attrs.getResourceId(R.styleable.GalleryTest_android_galleryItemBackground, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.apogeo.android.cap05.gallerytest.AbstractSpinnerAdapter#getDataAsArray
	 * ()
	 */
	@Override
	public Integer[] getDataAsArray() {
		return imageIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView currentView = null;
		// Riutilizziamo l'eventuale convertView.
		if (convertView == null) {
			currentView = new ImageView(context);
		} else {
			currentView = (ImageView) convertView;
		}
		// Impostiamo come sorgente quella individuata dalla risorsa i-esima
		currentView.setImageResource(imageIds[position]);
		// Adattiamo l'immagine al relativo contenitore
		currentView.setScaleType(ImageView.ScaleType.FIT_XY);
		// Impostiamo le dimensioni delle View contenute
		currentView.setLayoutParams(new Gallery.LayoutParams(140, 90));
		// Impostiamo il valore di background
		if (backgroundResource > 0) {
			currentView.setBackgroundResource(backgroundResource);
		}
		// ritorniamo la View
		return currentView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// Deleghiamo alla getView() modificandone le dimensioni
		View alternativeView = getView(position, convertView, parent);
		alternativeView.setLayoutParams(new Gallery.LayoutParams(70, 45));
		Log.i("ImageSpinnerAdapter", "getDropDownView");
		// Lo ritorniamo
		return alternativeView;
	}
	
	

}

package it.apogeo.android.cap05.gallerytest;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * Implementazione di uno SpinnerAdapter che eprmette di gestire un elenco di
 * immagini da visualizzare all'interno di una Gallery
 * 
 * @author Massimo Carli
 * 
 */
public abstract class AbstractSpinnerAdapter<T> extends BaseAdapter {
	
	/*
	 * Riferimento al Context
	 */
	protected Context context;
	
	/**
	 * Crea un AbstractSpinnerAdapter attraverso il contesto
	 * 
	 * @param context Riferimento al context
	 */
	public AbstractSpinnerAdapter(Context context){
		// Salviamo il riferimento al Context
		this.context = context;
	}
	
	/**
	 * Metodo che dovrà essere implementato dalla specializzazione
	 * ritornando l'insieme di risorse come un array di oggetti
	 * associati alla relativa risorsa
	 * 
	 * @return
	 */
	public abstract T[] getDataAsArray();
	

	@Override
	public int getCount() {
		return getDataAsArray().length;
	}

	@Override
	public T getItem(int position) {
		return getDataAsArray()[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}

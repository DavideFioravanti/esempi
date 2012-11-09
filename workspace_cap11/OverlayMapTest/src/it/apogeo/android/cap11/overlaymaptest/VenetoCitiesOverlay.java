/**
 * 
 */
package it.apogeo.android.cap11.overlaymaptest;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * @author MASSIMO
 *
 */
public class VenetoCitiesOverlay extends ItemizedOverlay<OverlayItem> {
	
	/*
	 * Insieme degli item da visualizzare
	 */
	private List<OverlayItem> province = new LinkedList<OverlayItem>();
	/*
	 * Riferimento all'icona da utilizzare come Marker
	 */
	private Drawable defaultMarker;

	/**
	 * @param defaultMarker
	 */
	public VenetoCitiesOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		this.defaultMarker=defaultMarker;
		// Aggiungiamo ciascuna provincia come OverlayItem
		province.add(new OverlayItem(new GeoPoint(45066667,11783333),"Rovigo","RO"));
		province.add(new OverlayItem(new GeoPoint(46140833,12215556),"Belluno","BL"));
		province.add(new OverlayItem(new GeoPoint(45406389,11877778),"Padova","PD"));
		province.add(new OverlayItem(new GeoPoint(45666667,12250000),"Treviso","TV"));
		province.add(new OverlayItem(new GeoPoint(45437500,12335833),"Venezia","VE"));
		province.add(new OverlayItem(new GeoPoint(45438158,10993742),"Verona","VR"));
		province.add(new OverlayItem(new GeoPoint(45550000,11550000),"Vicenza","VI"));
		populate();
	}
	
	

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(defaultMarker);		
	}



	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int i) {
		// Ritorniamo il riferimento all'i-esimo item
		return province.get(i);
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		// Numero di elementi
		return province.size();
	}

}

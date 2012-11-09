package it.massimocarli.andlib.utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Classe che contiene alcuni metodi statici di utilita' per la gestione
 * dell'orientation del dispositivo 
 * 
 * @author Massimo Carli
 *
 */
public class OrientationUtils {
	
	private OrientationUtils(){
		throw new AssertionError("Non create istante di classi di utilita'!!");
	}
	
	/**
	 * Metodo di utilita' che permette di sapere se il dispositivo ha un
	 * orientamento landscape sia da un alto che dall'altro 
	 * @param act Activity di riferimento 
	 * @return true se in landscape (entrambe le direzioni) e false altrimenti
	 */
	public static  boolean isLandscape(Activity act){
		// Verifichiamo la presenza del FrameLayout. Questa soluzione e' legata comunque
		// al layout utilizzato.
		//View exampleDetail = act.findViewById(R.id.example_detail);
		//boolean isLandscape = (exampleDetail!=null);
		// Utilizziamo le API per la conoscenza del dispositivo
		//int orientation = act.getWindowManager().getDefaultDisplay().getRotation();
		//boolean isLandscape = (orientation==Surface.ROTATION_90||orientation==Surface.ROTATION_270);
		// Il precedente metodo funziona solo dalla 2.2 in poi per cui, per i vecchi dispositivi
		// utilizziamo quest'altro metodo
		int orientation = act.getResources().getConfiguration().orientation;
		boolean isLandscape = (Configuration.ORIENTATION_LANDSCAPE==orientation);
		return isLandscape;
	}	
	
	public static boolean isXLarge(Context ctx){
		int isXLarge = ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_XLARGE;
		return isXLarge!=0;
	}

}

package it.massimocarli.andlib.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.util.Log;

/**
 * Classe di utilita' relativamente alla gestione delle risorse 
 * 
 * @author carli
 *
 */
public final class ResourceUtils {
	
	// Tag per il Log 
	private final static String LOG_CAT = "it.massimocarli.android.util.ResourceUtils";
	
	// Dimensione del buffer di lettura
	private final static int BUFFER_DIMENSION = 128;
	
	// Costruttore privato
	private ResourceUtils(){
		throw new AssertionError("Never call this!!!");
	}
	
	/**
	 * Permette di ottenere il contenuto di una risorsa raw come String 
	 * 
	 * @param context Riferimento al Context
	 * @param resId Identificatore della risorsa raw
	 * @return COntenuto della risorsa di tipo String
	 */
	public static String getRawAsString(Context context, int resId){
		// Risultato
		String result = null;
		// Otteniamo il riferimento all'inputStream
		InputStream is = context.getResources().openRawResource(resId);
		if(is!=null){
			// Leggiamo i byte
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_DIMENSION];
			int numRead = 0;
			try {
				while((numRead = is.read(buffer))>=0){
					baos.write(buffer, 0, numRead);
				}
				// Alla fine otteniamo la corrispondente String
				result = new String(baos.toByteArray());
			} catch (IOException e) {
				Log.e(LOG_CAT, "Error reading resource from stream");
				e.printStackTrace();
			}finally{
				if(baos!=null){
					try {
						baos.close();
					} catch (IOException e) {
						Log.e(LOG_CAT, "Error closing ByteArrayOutputStream reading from resources stream");
					}
				}
			}
		}
		// Ritorniamo il risultato
		return result;
	};
	
	
	/**
	 * Permette di ottenere il riferimento ad una String da una risorsa di raw utilizzandola poi
	 * come template per una operazione di format
	 * 
	 * @param context Riferimento al Context
	 * @param resId Id della risorsa
	 * @param args Elenco degli argomenti per il format
	 * @return String risultato della operazione di format sulla String ottenuta dalla risorsa 
	 */
	public static String getRawAsEvaluatedFormat(Context context, int resId,Object... args){
		String strFormat = getRawAsString(context, resId);
		if(strFormat!=null){
			return String.format(strFormat, args);
		}else{
			Log.w(LOG_CAT, "Error evaluating format");
			return null;
		}
	}	
	

}

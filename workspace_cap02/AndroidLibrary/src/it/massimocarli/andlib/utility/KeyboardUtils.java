package it.massimocarli.andlib.utility;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * Classe di utilita' che contiene alcuni metodi di utilita' per la gestione
 * della tastiera soft dei dispositivi Android
 * 
 * @author Massimo Carli
 * 
 */
public class KeyboardUtils {

	private KeyboardUtils() {
		throw new AssertionError("Mai creare istanze delle classi di utilita'");
	}

	/**
	 * Metodo di utilita' che permette di nascondere la keyboard virtuale
	 * @param ctx Riferimento al Context
	 * @param itemToken Riferimento al IBinder per la Window corrispondente
	 */
	public static void hideKeyboard(Context ctx, IBinder itemToken) {
		InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(itemToken, InputMethodManager.HIDE_NOT_ALWAYS);
	}

}

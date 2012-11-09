package it.massimocarli.andlib.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Classe che contiene alcuni metodi di utilita' per la gestione dei Toast
 * 
 * @author Massimo Carli
 * 
 */
public class ToastUtil {

	private ToastUtil() {
		throw new AssertionError("Never create me!");
	}

	/**
	 * Visualizza un Toast di breve durata con il messaggio passato come
	 * parametro
	 * 
	 * @param msg
	 *            Messaggio da visualizzare
	 * @param ctx
	 *            Riferimento al contesto
	 */
	public static void showShort(Context ctx, String msg) {
		Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Visualizza un Toast di lunga durata con il messaggio passato come
	 * parametro
	 * 
	 * @param msg
	 *            Messaggio da visualizzare
	 * @param ctx
	 *            Riferimento al contesto
	 */
	public static void showLong(Context ctx, String msg) {
		Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
		toast.show();
	}

	/**
	 * Visualizza un Toast di breve durata con il messaggio passato come
	 * parametro
	 * 
	 * @param msg
	 *            Id del messaggio da visualizzare
	 * @param ctx
	 *            Riferimento al contesto
	 */
	public static void showShort(Context ctx, int msg) {
		Toast toast = Toast.makeText(ctx, ctx.getResources().getString(msg),
				Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Visualizza un Toast di lunga durata con il messaggio passato come
	 * parametro
	 * 
	 * @param msg
	 *            Id del messaggio da visualizzare
	 * @param ctx
	 *            Riferimento al contesto
	 */
	public static void showLong(Context ctx, int msg) {
		Toast toast = Toast.makeText(ctx, ctx.getResources().getString(msg),
				Toast.LENGTH_LONG);
		toast.show();
	}

}

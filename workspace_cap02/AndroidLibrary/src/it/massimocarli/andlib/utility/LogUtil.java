package it.massimocarli.andlib.utility;

import android.util.Log;

/**
 * Classe che contiene un insieme di metodi statici di utilità per la gestione
 * dei messaggi di Log che contengano informazioni relative al nome della classe
 * che li contiene
 * 
 * @author Massimo Carli
 * 
 */
public class LogUtil {

	/*
	 * I vari livelli di log
	 */
	public static final int HIDE = 0;
	public static final int WARN = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final int VERBOSE = 4;
	public static final int ERROR = 5;

	/*
	 * Livello di Log abilitato
	 */
	public static int LOG_LEVEL = VERBOSE;

	/*
	 * Rappresenta la stringa da ritornare nel caso in cui non sia possibile
	 * ottenere il tag associato ad un oggetto
	 */
	private final static String UNKNOWN_TAG = "UNKNOWN_TAG";

	/**
	 * Le classi di utilita' con soli metodi statici non devono essere
	 * instanziare
	 */
	private LogUtil() {
		throw new AssertionError(
				"Non instanziare classi con soli metodi statici!");
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo alla classe che
	 * lo ha eseguito con livello di debug
	 * 
	 * @param instance
	 *            Riferimento all'istanza della classe che lo ha eseguito
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void d(Object instance, String message) {
		if (LOG_LEVEL >= DEBUG) {
			Log.d(getTag(instance), message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo ad un tag
	 * 
	 * @param tag
	 *            Tag del log
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void d(String tag, String message) {
		if (LOG_LEVEL >= DEBUG) {
			Log.d(tag, message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo alla classe che
	 * lo ha eseguito con livello di warning
	 * 
	 * @param instance
	 *            Riferimento all'istanza della classe che lo ha eseguito
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void w(Object instance, String message) {
		if (LOG_LEVEL >= WARN) {
			Log.w(getTag(instance), message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo ad un tag
	 * 
	 * @param tag
	 *            Tag del log
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void w(String tag, String message) {
		if (LOG_LEVEL >= WARN) {
			Log.w(tag, message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo alla classe che
	 * lo ha eseguito con livello di info
	 * 
	 * @param instance
	 *            Riferimento all'istanza della classe che lo ha eseguito
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void i(Object instance, String message) {
		if (LOG_LEVEL >= INFO) {
			Log.i(getTag(instance), message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo ad un tag
	 * 
	 * @param tag
	 *            Tag del log
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void i(String tag, String message) {
		if (LOG_LEVEL >= INFO) {
			Log.i(tag, message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo alla classe che
	 * lo ha eseguito con livello di error
	 * 
	 * @param instance
	 *            Riferimento all'istanza della classe che lo ha eseguito
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void e(Object instance, String message) {
		if (LOG_LEVEL >= ERROR) {
			Log.e(getTag(instance), message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo ad un tag
	 * 
	 * @param tag
	 *            Tag del log
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void e(String tag, String message) {
		if (LOG_LEVEL >= ERROR) {
			Log.e(tag, message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo alla classe che
	 * lo ha eseguito con livello di verbose
	 * 
	 * @param instance
	 *            Riferimento all'istanza della classe che lo ha eseguito
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void v(Object instance, String message) {
		if (LOG_LEVEL >= VERBOSE) {
			Log.v(getTag(instance), message);
		}
	}

	/**
	 * Permette di visualizzare un messaggio di log associandolo ad un tag
	 * 
	 * @param tag
	 *            Tag del log
	 * @param message
	 *            Messaggio di log da visualizzare
	 */
	public static final void v(String tag, String message) {
		if (LOG_LEVEL >= VERBOSE) {
			Log.v(tag, message);
		}
	}

	/*
	 * Metodo di utilita' che permette di ottenere il nome della classe
	 * dell'oggetto passato come riferimento per il suo utilizzo come tag
	 */
	private final static String getTag(Object o) {
		if (o != null) {
			return o.getClass().getName();
		} else {
			return UNKNOWN_TAG;
		}
	}

}

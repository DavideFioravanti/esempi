/**
 * 
 */
package it.apogeo.android.cap12.collectionwidgettest;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Classe che contiene le costanti utili nella gestione del Content Provider
 * 
 * @author MASSIMO
 * 
 */
public class TeamProviderMetaData {
	/**
	 * Costante che contiene il nome dell'authority relativa al COntent Provider
	 * che gestisce le entità di tipo Team. Notiamo che il nome è legato al
	 * package dell'applicazione
	 */
	public final static String AUTHORITY = "it.apogeo.android.cap08.teamcontentprovider.TeamContentProvider";

	/**
	 * Nome del file associato al database
	 */
	public final static String DATABASE_FILENAME = "TEAM_DB.db";
	/**
	 * Versione del DB
	 */
	public final static int DATABASE_VERSION = 1;
	/**
	 * Nome della tabella associata ai Team
	 */
	public final static String TEAM_TABLE_NAME = "TEAM";

	/*
	 * Costruttore provato per impedire la creazione di istanze e quindi
	 * permettendone l'utilizzo solo attraversole costanti statiche
	 */
	private TeamProviderMetaData() {
	}

	/**
	 * Classe interna statica che descrive le costanti utili alla gestione della
	 * sola tabella Team
	 * 
	 */
	public static final class TeamTableMetaData implements BaseColumns {
		/*
		 * Costruttore privato per impedire la creazione di istanze di questa
		 * classe
		 */
		private TeamTableMetaData() {
		}

		/**
		 * Nome della tabella associata ai Team
		 */
		public final static String TABLE_NAME = "TEAM";

		/**
		 * Uri di riferimento per l'accesso all'insieme dei dati contenuti
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/team");

		/**
		 * Content Type dell'insieme delle informazioni dei Team
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.teamprovider.team";

		/**
		 * Content Type per il singolo Team
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.teamprovider.team";

		public static String NAME = "name";
		public static String CITY = "city";
		public static String COUNTRY = "country";
		public static String WEB_SITE = "web_site";

	}

}

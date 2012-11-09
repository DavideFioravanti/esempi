/**
 * 
 */
package it.apogeo.android.cap08.teamcontentprovider;

import it.apogeo.android.cap08.teamcontentprovider.TeamProviderMetaData.TeamTableMetaData;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @author MASSIMO
 * 
 */
public class TeamContentProvider extends ContentProvider {

	/*
	 * Inizializzazione dello UriMatcher per il riconoscimento delle Uri
	 */
	private final static UriMatcher uriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	/*
	 * Valore associato all'UriMatcher nel caso di collection di Team
	 */
	private final static int TEAM_COLLECTION_URI_INDICATOR = 1;
	/*
	 * Valore associato all'UriMatcher nel caso di singolo Team
	 */
	private final static int TEAM_URI_INDICATOR = 2;
	/*
	 * Inizializzatore statico per l'inizializzazione dei possibili valori di
	 * ritorno dello UriMatcher
	 */
	static {
		// Associamo il valore alla collection di team
		uriMatcher.addURI(TeamProviderMetaData.AUTHORITY, "team",
				TEAM_COLLECTION_URI_INDICATOR);
		// Associamo il valore al singolo team
		uriMatcher.addURI(TeamProviderMetaData.AUTHORITY, "team/#",
				TEAM_URI_INDICATOR);
	}

	/*
	 * Oggetto che contiene i campi di default di Projection
	 */
	private final static Map<String, String> PROJECTION_MAP = new HashMap<String, String>();
	static {
		PROJECTION_MAP.put(TeamTableMetaData._ID, TeamTableMetaData._ID);
		PROJECTION_MAP.put(TeamTableMetaData.NAME, TeamTableMetaData.NAME);
		PROJECTION_MAP.put(TeamTableMetaData.CITY, TeamTableMetaData.CITY);
		PROJECTION_MAP
				.put(TeamTableMetaData.COUNTRY, TeamTableMetaData.COUNTRY);
		PROJECTION_MAP.put(TeamTableMetaData.WEB_SITE,
				TeamTableMetaData.WEB_SITE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#delete(android.net.Uri,
	 * java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Otteniamo il riferimento al DB in scrittura
		SQLiteDatabase db = teamOpenHelper.getWritableDatabase();
		// Valore di ritorno
		int updatedNumber = 0;
		// Anche in questo caso dobbiamo gestire le diverse tipologie di Uri che
		// questo metodo
		// riceve in ingresso.
		switch (uriMatcher.match(uri)) {
		case TEAM_URI_INDICATOR:
			// In questo caso dobbiamo aggiornare solamente l'elemento
			// identificato dall'Uri
			// passato come parametro aggiungendo quindi le eventuali selection
			// come
			// ulteriore filtro. Otteniamo quindi l'id contenuto nell'Uri
			String idValue = uri.getPathSegments().get(1);
			// Componiamo la clausola where se presente
			StringBuilder whereClause = new StringBuilder();
			// Parte relativa all'ID
			whereClause.append(TeamTableMetaData._ID).append("=").append(
					idValue);
			// Utilizziamo la selection se presente
			if (!TextUtils.isEmpty(selection)) {
				whereClause.append(" AND (").append(selection).append(" )");
			}
			// Usiamo il Db per la delete delle informazioni
			updatedNumber = db.delete(TeamTableMetaData.TABLE_NAME,
					whereClause.toString(), selectionArgs);
			break;
		case TEAM_COLLECTION_URI_INDICATOR:
			// In questo caso dobbiamo semplicemente richiamare il metodo di
			// delete con
			// gli stessi parametri ottenuti in ingresso
			db.delete(TeamTableMetaData.TABLE_NAME,  selection,
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Uri " + uri + " is notvalid");
		}
		// Notifichiamo della modifica
		getContext().getContentResolver().notifyChange(uri, null);
		// Ritorniamo il valore ottenuto dall'operazione di update su DB
		return updatedNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case TEAM_URI_INDICATOR:
			return TeamTableMetaData.CONTENT_ITEM_TYPE;
		case TEAM_COLLECTION_URI_INDICATOR:
			return TeamTableMetaData.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Uri " + uri + " is notvalid");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#insert(android.net.Uri,
	 * android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Verifichiamo che l'Uri sia relativo ad un elenco di Team
		if (uriMatcher.match(uri) != TEAM_COLLECTION_URI_INDICATOR) {
			// Si tratta di un Uri non valido
			throw new IllegalArgumentException("Uri not Valid " + uri);
		}
		// Verifichiamo la presenza delle informazioni obbligatorie
		// lanciando delle eccezioni nel caso non vi fossero
		if (!values.containsKey(TeamTableMetaData.NAME)) {
			throw new IllegalArgumentException(TeamTableMetaData.NAME
					+ " is mandatory");
		}
		if (!values.containsKey(TeamTableMetaData.CITY)) {
			throw new IllegalArgumentException(TeamTableMetaData.CITY
					+ " is mandatory");
		}
		if (!values.containsKey(TeamTableMetaData.COUNTRY)) {
			throw new IllegalArgumentException(TeamTableMetaData.COUNTRY
					+ " is mandatory");
		}
		// Otteniamo il riferimento al Db in scrittura
		SQLiteDatabase db = teamOpenHelper.getWritableDatabase();
		// Eseguiamo un inserimento ottenendo il riferimento al nuovo id
		long newTeamId = db.insert(TeamTableMetaData.TABLE_NAME,
				TeamTableMetaData.WEB_SITE, values);
		if (newTeamId > 0) {
			// Se il valore ha senso componiamo il corrispondente Uri
			Uri newTeamUri = ContentUris.withAppendedId(
					TeamTableMetaData.CONTENT_URI, newTeamId);
			// Notifichiamo il ContentResolver della presenza di questo nuovo
			// elemento
			getContext().getContentResolver().notifyChange(newTeamUri, null);
			// Ritorniamo il nuovo Uri
			return newTeamUri;
		}
		// In questo caso l'inserimento non e' andato a buon fine per cui
		// generiamo una
		// eccezione
		throw new IllegalStateException("Insert failed! ");
	}

	/*
	 * Riferimento all'OpenHelper
	 */
	private TeamProviderSQLiteOpenHelper teamOpenHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		// Creiamo il SQLiteOpenHelper
		teamOpenHelper = new TeamProviderSQLiteOpenHelper(getContext());
		// Ritorniamo true
		return true;
	}

	/**
	 * Definizione del particolare SQLiteOpenHelper per l'inizializzazione e
	 * gestione del DB per la memorizzazione delle informazioni associate al
	 * Content Provider
	 */
	private static class TeamProviderSQLiteOpenHelper extends SQLiteOpenHelper {

		TeamProviderSQLiteOpenHelper(Context context) {
			super(context, TeamProviderMetaData.DATABASE_FILENAME, null,
					TeamProviderMetaData.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			StringBuilder createQuery = new StringBuilder();
			createQuery.append("CREATE TABLE ");
			createQuery.append(TeamTableMetaData.TABLE_NAME);
			createQuery.append(" (").append(TeamTableMetaData._ID);
			createQuery.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
			createQuery.append(TeamTableMetaData.NAME);
			createQuery.append(" TEXT NOT NULL,");
			createQuery.append(TeamTableMetaData.CITY);
			createQuery.append(" TEXT NOT NULL,");
			createQuery.append(TeamTableMetaData.COUNTRY);
			createQuery.append(" TEXT NOT NULL,");
			createQuery.append(TeamTableMetaData.WEB_SITE);
			createQuery.append(" TEXT)");
			db.execSQL(createQuery.toString());
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Nel caso di aggiornamento cancelliamo la tabella precedente e
			// ricreiamo la nuova
			StringBuilder createQuery = new StringBuilder();
			createQuery.append("DROP TABLE IF EXISTS ");
			createQuery.append(TeamTableMetaData.TABLE_NAME);
			db.execSQL(createQuery.toString());
			// Ricostruiamo lo schema richiamando il metodo sopra
			onCreate(db);
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#query(android.net.Uri,
	 * java.lang.String[], java.lang.String, java.lang.String[],
	 * java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// Creiamo il riferimento al SQLiteQueryBuilder
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		// In entrambi i casi la tabella è sempre la stessa
		builder.setTables(TeamTableMetaData.TABLE_NAME);
		// Impostiamo il valore di default del ProjectMap
		builder.setProjectionMap(PROJECTION_MAP);
		// Gestiamo le diverse Uri
		switch (uriMatcher.match(uri)) {
		case TEAM_URI_INDICATOR:
			// Otteniamo l'id dall'Uri
			String idValue = uri.getPathSegments().get(1);
			// Lo utilizziamo per aggiungere una clausola where al builder
			builder.appendWhere(TeamTableMetaData._ID + "=" + idValue);
		case TEAM_COLLECTION_URI_INDICATOR:
			// Non facciamo nulla di piu'
			break;
		default:
			throw new IllegalArgumentException("Uri " + uri + " is notvalid");
		}
		// Gestiamo l'ordinamento
		String orderBy = null;
		if (sortOrder != null) {
			orderBy = sortOrder;
		}
		// Accediamo al Db in lettura per la select
		SQLiteDatabase db = teamOpenHelper.getReadableDatabase();
		// Eseguiamo la query
		Cursor cursor = builder.query(db, projection, selection, selectionArgs,
				null, null, orderBy);
		// Permette al ContentResolver di essere notificato di eventuali
		// modifiche
		// all'uri passato come parametro
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		// Ritorniamo ilriferimento al cursor
		return cursor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#update(android.net.Uri,
	 * android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// Otteniamo il riferimento al DB in scrittura
		SQLiteDatabase db = teamOpenHelper.getWritableDatabase();
		// Valore di ritorno
		int updatedNumber = 0;
		// Anche in questo caso dobbiamo gestire le diverse tipologie di Uri che
		// questo metodo
		// riceve in ingresso.
		switch (uriMatcher.match(uri)) {
		case TEAM_URI_INDICATOR:
			// In questo caso dobbiamo aggiornare solamente l'elemento
			// identificato dall'Uri
			// passato come parametro aggiungendo quindi le eventuali selection
			// come
			// ulteriore filtro. Otteniamo quindi l'id contenuto nell'Uri
			String idValue = uri.getPathSegments().get(1);
			// Componiamo la clausola where se presente
			StringBuilder whereClause = new StringBuilder();
			// Parte relativa all'ID
			whereClause.append(TeamTableMetaData._ID).append("=").append(
					idValue);
			// Utilizziamo la selection se presente
			if (!TextUtils.isEmpty(selection)) {
				whereClause.append(" AND (").append(selection).append(" )");
			}
			// Usiamo il Db per l'update delle informazioni
			updatedNumber = db.update(TeamTableMetaData.TABLE_NAME, values,
					whereClause.toString(), selectionArgs);
			break;
		case TEAM_COLLECTION_URI_INDICATOR:
			// In questo caso dobbiamo semplicemente richiamare il metodo di
			// update con
			// gli stessi parametri ottenuti in ingresso
			db.update(TeamTableMetaData.TABLE_NAME, values, selection,
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Uri " + uri + " is notvalid");
		}
		// Notifichiamo della modifica
		getContext().getContentResolver().notifyChange(uri, null);
		// Ritorniamo il valore ottenuto dall'operazione di update su DB
		return updatedNumber;
	}

}

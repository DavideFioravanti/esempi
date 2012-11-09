/**
 * 
 */
package it.apogeo.android.cap08.teamlivefolder;

import it.apogeo.android.cap08.teamlivefolder.Team.TeamMetaData;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.LiveFolders;

/**
 * @author MASSIMO
 * 
 */
public class TeamFolderProvider extends ContentProvider {

	/**
	 * Costante che contiene il nome dell'authority relativa al COntent Provider
	 * che gestisce le entità di tipo Team per il Live Folder.
	 */
	public final static String AUTHORITY = "it.apogeo.android.cap08.teamlivefolder";

	/**
	 * Uri del ContentProvider relativo ai Team
	 */
	public final static Uri TEAM_CONTENT_URI = Uri
			.parse("content://it.apogeo.android.cap08.teamcontentprovider.TeamContentProvider/team");

	/**
	 * Content Type dell'insieme delle informazioni dei Team
	 */
	public static final String TEAM_COLLECTION_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.teamprovider.team";

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
	 * Inizializzatore statico per l'inizializzazione dei possibili valori di
	 * ritorno dello UriMatcher
	 */
	static {
		// Associamo il valore alla collection di team
		uriMatcher.addURI(AUTHORITY, "team", TEAM_COLLECTION_URI_INDICATOR);
	}

	/**
	 * Colonne che ci si aspetta da un ContentProvider che gestisce un Live
	 * Folder
	 */
	private static final String[] LIVE_FOLDER_COLUMNS = new String[] {
			BaseColumns._ID, LiveFolders.NAME, LiveFolders.DESCRIPTION,
			LiveFolders.INTENT, LiveFolders.ICON_PACKAGE,
			LiveFolders.ICON_RESOURCE };

	@Override
	public String getType(Uri uri) {
		// Ritorniamo il Content.Type degli elementi del Content Provider
		// relativi ai Team
		return TEAM_COLLECTION_CONTENT_TYPE;
	}

	@Override
	public boolean onCreate() {
		// Non dobbiamo inizializzare nulla per cui indichiamo che
		// la creazione è avvenuta con successo ritornando true
		return true;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		return 0; // nothing to insert
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// Controlliamo che l'Uri sia relativo alle risorse associate
		// a questo Content Provider
		int uriIndicator = uriMatcher.match(uri);
		if (uriIndicator == UriMatcher.NO_MATCH) {
			throw new IllegalArgumentException(
					"Uri not valid for this ContentProvider " + uri);
		}
		// Creiamo un cursore che incapsula le informazioni necessaria al
		// LiveFolder
		MatrixCursor newCursor = loadNewData(this);
		newCursor.setNotificationUri(getContext().getContentResolver(),
				TEAM_CONTENT_URI);
		// Lo wrappiamo all'interno di un UpdateableCrossCursor
		UpdateableCrossCursor cursorWrapper = new UpdateableCrossCursor(newCursor,this);
		// Lo ritorniamo
		return cursorWrapper;		
	}

	public static MatrixCursor loadNewData(ContentProvider contentProvider) {
		// Creiamo il cursore da ritornare
		MatrixCursor matrixCursor = new MatrixCursor(LIVE_FOLDER_COLUMNS);
		// Leggiamo tutti i record relativi al Content Provider dei Team
		// wrappando le informazioni al'interno del nuovo cursore
		Cursor teamCursor = null;
		try {
			teamCursor = contentProvider.getContext().getContentResolver().query(
					TEAM_CONTENT_URI, TeamMetaData.COLUMNS, null, // row filter
					null, TeamMetaData.NAME); // Ordinati per nome
			// Li wrappiamo all'interno del MatrixCursor secondo l'ordine dei
			// campi di LIVE_FOLDER_COLUMNS
			while (teamCursor.moveToNext()) {
				Long itemId =  teamCursor.getLong(0); // ID
				String name = teamCursor.getString(1);
				String city = teamCursor.getString(2);
				String country = teamCursor.getString(3);
				Uri intentUri = Uri.withAppendedPath(TEAM_CONTENT_URI,""+itemId);
				// L'oggetto da inserire nel cursore
				Object[] rowToInsert = {
						itemId,	// id
						name,	// name
						city+" ("+country+")", // description
						intentUri, // intent
						contentProvider.getContext().getPackageName(), //package
						R.drawable.icon // Icona
				};
				// Aggiungiamola riga al MatrixCursor
				matrixCursor.addRow(rowToInsert);
			}
		} finally {
			// In ogni caso lo chiudiamo
			teamCursor.close();
		}
		// Lo ritorniamo
		return matrixCursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		throw new UnsupportedOperationException(
				"No Update Operation for this ContentProvider");
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException(
				"No Delete Operation for this ContentProvider");
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException(
				"No Insert Operation for this ContentProvider");
	}

}

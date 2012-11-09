/**
 * 
 */
package it.apogeo.android.cap08.teamlivefolder;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.CrossProcessCursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;

/**
 * Questa classe descrive una implementazione di un CrossProcessCursor che
 * delega l'esecuzione delle varie operazioni ad un MatrixCursor passato come
 * parametro e che è possibile aggiornare attraverso l'invocazione del metodo
 * requery.
 * 
 * @author MASSIMO
 * 
 */
public class UpdateableCrossCursor implements CrossProcessCursor {

	/*
	 * MatrixCursor a cui delegare l'esecuzione delle operazioni
	 */
	private MatrixCursor delegateCursor;

	/*
	 * COntentProvider per l'aggiornamento delle informazioni
	 */
	private ContentProvider contentProvider;

	public UpdateableCrossCursor(MatrixCursor dalegateCursor,
			ContentProvider contentProvider) {
		this.delegateCursor = dalegateCursor;
		this.contentProvider = contentProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.CrossProcessCursor#fillWindow(int,
	 * android.database.CursorWindow)
	 */
	@Override
	public void fillWindow(int position, CursorWindow window) {
		delegateCursor.fillWindow(position, window);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.CrossProcessCursor#getWindow()
	 */
	@Override
	public CursorWindow getWindow() {
		return delegateCursor.getWindow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.CrossProcessCursor#onMove(int, int)
	 */
	@Override
	public boolean onMove(int oldPosition, int newPosition) {
		return delegateCursor.onMove(oldPosition, newPosition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#close()
	 */
	@Override
	public void close() {
		delegateCursor.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#copyStringToBuffer(int,
	 * android.database.CharArrayBuffer)
	 */
	@Override
	public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
		delegateCursor.copyStringToBuffer(columnIndex, buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#deactivate()
	 */
	@Override
	public void deactivate() {
		delegateCursor.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getBlob(int)
	 */
	@Override
	public byte[] getBlob(int column) {
		return delegateCursor.getBlob(column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return delegateCursor.getColumnCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getColumnIndex(java.lang.String)
	 */
	@Override
	public int getColumnIndex(String columnName) {
		return delegateCursor.getColumnIndex(columnName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getColumnIndexOrThrow(java.lang.String)
	 */
	@Override
	public int getColumnIndexOrThrow(String columnName)
			throws IllegalArgumentException {
		return delegateCursor.getColumnIndexOrThrow(columnName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return delegateCursor.getColumnName(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getColumnNames()
	 */
	@Override
	public String[] getColumnNames() {
		return delegateCursor.getColumnNames();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getCount()
	 */
	@Override
	public int getCount() {
		return delegateCursor.getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getDouble(int)
	 */
	@Override
	public double getDouble(int columnIndex) {
		return delegateCursor.getDouble(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getExtras()
	 */
	@Override
	public Bundle getExtras() {
		return delegateCursor.getExtras();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getFloat(int)
	 */
	@Override
	public float getFloat(int columnIndex) {
		return delegateCursor.getFloat(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getInt(int)
	 */
	@Override
	public int getInt(int columnIndex) {
		return delegateCursor.getInt(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getLong(int)
	 */
	@Override
	public long getLong(int columnIndex) {
		return delegateCursor.getLong(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getPosition()
	 */
	@Override
	public int getPosition() {
		return delegateCursor.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getShort(int)
	 */
	@Override
	public short getShort(int columnIndex) {
		return delegateCursor.getShort(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getString(int)
	 */
	@Override
	public String getString(int columnIndex) {
		return delegateCursor.getString(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#getWantsAllOnMoveCalls()
	 */
	@Override
	public boolean getWantsAllOnMoveCalls() {
		return delegateCursor.getWantsAllOnMoveCalls();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isAfterLast()
	 */
	@Override
	public boolean isAfterLast() {
		return delegateCursor.isAfterLast();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isBeforeFirst()
	 */
	@Override
	public boolean isBeforeFirst() {
		return delegateCursor.isBeforeFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return delegateCursor.isClosed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isFirst()
	 */
	@Override
	public boolean isFirst() {
		return delegateCursor.isFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isLast()
	 */
	@Override
	public boolean isLast() {
		return delegateCursor.isLast();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#isNull(int)
	 */
	@Override
	public boolean isNull(int columnIndex) {
		return delegateCursor.isNull(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#move(int)
	 */
	@Override
	public boolean move(int offset) {
		return delegateCursor.move(offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#moveToFirst()
	 */
	@Override
	public boolean moveToFirst() {
		return delegateCursor.moveToFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#moveToLast()
	 */
	@Override
	public boolean moveToLast() {
		return delegateCursor.moveToLast();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#moveToNext()
	 */
	@Override
	public boolean moveToNext() {
		return delegateCursor.moveToNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#moveToPosition(int)
	 */
	@Override
	public boolean moveToPosition(int position) {
		return delegateCursor.moveToPosition(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#moveToPrevious()
	 */
	@Override
	public boolean moveToPrevious() {
		return delegateCursor.moveToPrevious();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeandroid.database.Cursor#registerContentObserver(android.database.
	 * ContentObserver)
	 */
	@Override
	public void registerContentObserver(ContentObserver observer) {
		delegateCursor.registerContentObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeandroid.database.Cursor#registerDataSetObserver(android.database.
	 * DataSetObserver)
	 */
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		delegateCursor.registerDataSetObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#requery()
	 */
	@Override
	public boolean requery() {
		// Otteniamo la nuova MatrixCursor dal TeamFolderProvider
		MatrixCursor matrixCursor = TeamFolderProvider.loadNewData(contentProvider);
		// Aggiogniamo il nuovo cursore
		this.delegateCursor = matrixCursor;
		// Invochiamo i metodo sul nuovo delegate
		return delegateCursor.requery();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.database.Cursor#respond(android.os.Bundle)
	 */
	@Override
	public Bundle respond(Bundle extras) {
		return delegateCursor.respond(extras);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.Cursor#setNotificationUri(android.content.ContentResolver
	 * , android.net.Uri)
	 */
	@Override
	public void setNotificationUri(ContentResolver cr, Uri notifyUri) {
		delegateCursor.setNotificationUri(cr, notifyUri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeandroid.database.Cursor#unregisterContentObserver(android.database.
	 * ContentObserver)
	 */
	@Override
	public void unregisterContentObserver(ContentObserver observer) {
		delegateCursor.unregisterContentObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeandroid.database.Cursor#unregisterDataSetObserver(android.database.
	 * DataSetObserver)
	 */
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		delegateCursor.unregisterDataSetObserver(observer);
	}

}

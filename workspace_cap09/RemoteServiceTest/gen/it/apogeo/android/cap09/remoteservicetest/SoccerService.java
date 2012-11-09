/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\workspace_cap09\\RemoteServiceTest\\src\\it\\apogeo\\android\\cap09\\remoteservicetest\\SoccerService.aidl
 */
package it.apogeo.android.cap09.remoteservicetest;
// Definiamo le operazioni del nostro SoccerService

public interface SoccerService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements it.apogeo.android.cap09.remoteservicetest.SoccerService
{
private static final java.lang.String DESCRIPTOR = "it.apogeo.android.cap09.remoteservicetest.SoccerService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an it.apogeo.android.cap09.remoteservicetest.SoccerService interface,
 * generating a proxy if needed.
 */
public static it.apogeo.android.cap09.remoteservicetest.SoccerService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof it.apogeo.android.cap09.remoteservicetest.SoccerService))) {
return ((it.apogeo.android.cap09.remoteservicetest.SoccerService)iin);
}
return new it.apogeo.android.cap09.remoteservicetest.SoccerService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getScore:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
it.apogeo.android.cap09.remoteservicetest.Score _result = this.getScore(_arg0, _arg1);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getAllScores:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<it.apogeo.android.cap09.remoteservicetest.Score> _arg0;
_arg0 = new java.util.ArrayList<it.apogeo.android.cap09.remoteservicetest.Score>();
int _result = this.getAllScores(_arg0);
reply.writeNoException();
reply.writeInt(_result);
reply.writeTypedList(_arg0);
return true;
}
case TRANSACTION_registerScore:
{
data.enforceInterface(DESCRIPTOR);
it.apogeo.android.cap09.remoteservicetest.Score _arg0;
if ((0!=data.readInt())) {
_arg0 = it.apogeo.android.cap09.remoteservicetest.Score.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.registerScore(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements it.apogeo.android.cap09.remoteservicetest.SoccerService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
// Permette di ottenere il punteggio di una partita tra due team

public it.apogeo.android.cap09.remoteservicetest.Score getScore(java.lang.String localTeam, java.lang.String externalTeam) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
it.apogeo.android.cap09.remoteservicetest.Score _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(localTeam);
_data.writeString(externalTeam);
mRemote.transact(Stub.TRANSACTION_getScore, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = it.apogeo.android.cap09.remoteservicetest.Score.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Ritorna l'elenco di tutti i punteggi delle partite

public int getAllScores(java.util.List<it.apogeo.android.cap09.remoteservicetest.Score> scores) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllScores, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
_reply.readTypedList(scores, it.apogeo.android.cap09.remoteservicetest.Score.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Permette di inserire uno Score

public void registerScore(it.apogeo.android.cap09.remoteservicetest.Score score) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((score!=null)) {
_data.writeInt(1);
score.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_registerScore, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getScore = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getAllScores = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerScore = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
// Permette di ottenere il punteggio di una partita tra due team

public it.apogeo.android.cap09.remoteservicetest.Score getScore(java.lang.String localTeam, java.lang.String externalTeam) throws android.os.RemoteException;
// Ritorna l'elenco di tutti i punteggi delle partite

public int getAllScores(java.util.List<it.apogeo.android.cap09.remoteservicetest.Score> scores) throws android.os.RemoteException;
// Permette di inserire uno Score

public void registerScore(it.apogeo.android.cap09.remoteservicetest.Score score) throws android.os.RemoteException;
}

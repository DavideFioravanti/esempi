/**
 * 
 */
package it.apogeo.android.cap09.remoteservicetest;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe che descrive un oggetto che incapsula le informazioni relative ad una
 * partita. Si tratta di un oggetto Parcelable ovvero trasefribile tra processi
 * diversi
 * 
 * @author MASSIMO
 * 
 */
public class Score implements Parcelable {

	public String localTeam;
	public String externalTeam;
	public Date date;
	public int localScore;
	public int externalScore;

	/**
	 * Ogetto di tipo Creator responsabile della ricostruzione dello Score
	 */
	public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>() {

		@Override
		public Score createFromParcel(Parcel parcel) {
			// Ricostruiamo lo Score leggendo i dati dal parcel
			return new Score(parcel);
		}

		@Override
		public Score[] newArray(int size) {
			// Generiamo l'array di elementi Score
			return new Score[size];
		}

	};

	public Score() {
	}

	/*
	 * Costruttore privato che permette la creazione di uno Score a partire da
	 * una Parcel
	 */
	private Score(Parcel in) {
		// Leggiamo le informazioni relative allo score
		// dal parcel in input
		localTeam = in.readString();
		externalTeam = in.readString();
		// Notiamo la lettura delle informazioni relative alla data
		date = new Date();
		date.setTime(in.readLong());
		// Leggiamo lo score
		localScore = in.readInt();
		externalScore = in.readInt();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		// Implementiamo la logica di scrittura
		parcel.writeString(localTeam);
		parcel.writeString(externalTeam);
		parcel.writeLong(date.getTime());
		parcel.writeInt(localScore);
		parcel.writeInt(externalScore);
	}

}

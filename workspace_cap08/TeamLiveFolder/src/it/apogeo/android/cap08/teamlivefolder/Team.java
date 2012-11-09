/**
 * 
 */
package it.apogeo.android.cap08.teamlivefolder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe che incapsula le informazioni relative ad un Team con i suoi metadata
 * 
 * @author MASSIMO
 * 
 */
public class Team implements Parcelable {

	public static class TeamMetaData {

		public static String ID = "_id";
		public static String NAME = "name";
		public static String CITY = "city";
		public static String COUNTRY = "country";
		public static String WEB_SITE = "web_site";

		public static String TABLE_NAME = "Team";

		public static String[] COLUMNS = new String[] { ID, NAME, CITY,
				COUNTRY, WEB_SITE };
	}

	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {

		@Override
		public Team createFromParcel(Parcel source) {
			return new Team(source);
		}

		@Override
		public Team[] newArray(int size) {
			return new Team[size];
		}

	};

	public long id;
	public String name;
	public String city;
	public String country;
	public String webSite;

	public Team() {

	}

	private Team(Parcel in) {
		id = in.readLong();
		name = in.readString();
		city = in.readString();
		country = in.readString();
		webSite = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(city);
		dest.writeString(country);
		dest.writeString(webSite);
	}

}

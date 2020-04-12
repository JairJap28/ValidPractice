package com.valid.pruebatecnica.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "tracks",
        indices = @Index(value = "track_id", unique = true)
        //foreignKeys = @ForeignKey(entity = Artist.class, parentColumns = "artist_id", childColumns = "track_id")
)
public class Track implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "track_id")

    private String mbid;

    private String name;

    private String duration;

    private String listeners;

    private String url;

    private String image;

    private String rank;

    public Track(String mbid, String name, String duration, String listeners, String url, String rank, String image) {
        this.mbid = mbid;
        this.name = name;
        this.duration = duration;
        this.listeners = listeners;
        this.url = url;
        this.rank = rank;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; };

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getRank() {
        return rank;
    }

    protected Track(Parcel in) {
        id = in.readInt();
        mbid = in.readString();
        name = in.readString();
        duration = in.readString();
        listeners = in.readString();
        url = in.readString();
        image = in.readString();
        rank = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(mbid);
        parcel.writeString(name);
        parcel.writeString(duration);
        parcel.writeString(listeners);
        parcel.writeString(url);
        parcel.writeString(image);
        parcel.writeString(rank);
    }
}

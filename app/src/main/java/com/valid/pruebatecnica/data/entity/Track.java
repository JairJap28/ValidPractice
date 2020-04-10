package com.valid.pruebatecnica.data.entity;

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
public class Track {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "track_id")

    private String mbid;

    private String name;

    private String duration;

    private String listeners;

    private String url;

    private String rank;

    public Track(String mbid, String name, String duration, String listeners, String url, String rank) {
        this.mbid = mbid;
        this.name = name;
        this.duration = duration;
        this.listeners = listeners;
        this.url = url;
        this.rank = rank;
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

    public String getRank() {
        return rank;
    }
}

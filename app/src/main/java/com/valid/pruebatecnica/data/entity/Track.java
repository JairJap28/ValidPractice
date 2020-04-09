package com.valid.pruebatecnica.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "tracks",
        indices = @Index(value = "track_id", unique = true),
        foreignKeys = @ForeignKey(entity = Artist.class, parentColumns = "artist_id", childColumns = "track_id")
)
public class Track {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "track_id")

    private String mbid;

    private String name;

    private long duration;

    private long listeners;

    private String url;

    public Track(String mbid, String name, long duration, long listeners, String url) {
        this.mbid = mbid;
        this.name = name;
        this.duration = duration;
        this.listeners = listeners;
        this.url = url;
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

    public long getDuration() {
        return duration;
    }

    public long getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }
}

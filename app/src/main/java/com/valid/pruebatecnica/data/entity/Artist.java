package com.valid.pruebatecnica.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "artists",
        indices = @Index(value = "artist_id", unique = true)
)
public class Artist {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "artist_id")
    private String mbid;
    private String name;
    private String url;

    public Artist(String mbid, String name, String url) {
        this.mbid = mbid;
        this.name = name;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

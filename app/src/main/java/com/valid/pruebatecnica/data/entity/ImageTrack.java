package com.valid.pruebatecnica.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ImageTrack")
public class ImageTrack {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String idTrack;

    private String url;

    private String size;

    public ImageTrack(String idTrack, String url, String size) {
        this.idTrack = idTrack;
        this.url = url;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdTrack() {
        return idTrack;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }
}

package com.valid.pruebatecnica.data.source.remote.model;

import com.valid.pruebatecnica.data.entity.Artist;

import java.util.List;

public class TrackRemote {
    private String name;
    private long duration;
    private long listeners;
    private String mbid;
    private String url;

    private Artist artist;
    private List<ImageRemote> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getListeners() {
        return listeners;
    }

    public void setListeners(long listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<ImageRemote> getImage() {
        return image;
    }

    public void setImage(List<ImageRemote> image) {
        this.image = image;
    }
}

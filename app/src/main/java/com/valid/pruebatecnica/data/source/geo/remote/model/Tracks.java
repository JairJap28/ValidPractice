package com.valid.pruebatecnica.data.source.geo.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracks {
    @SerializedName("track")
    @Expose
    private List<TrackRemote> track = null;
    @SerializedName("@attr")
    @Expose
    private Attr_ attr;

    public List<TrackRemote> getTrack() {
        return track;
    }

    public void setTrack(List<TrackRemote> track) {
        this.track = track;
    }

    public Attr_ getAttr() {
        return attr;
    }

    public void setAttr(Attr_ attr) {
        this.attr = attr;
    }
}

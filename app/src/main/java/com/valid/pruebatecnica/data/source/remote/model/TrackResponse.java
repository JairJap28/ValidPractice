package com.valid.pruebatecnica.data.source.remote.model;

import java.util.List;

public class TrackResponse {
    List<TrackRemote> tracks;

    public List<TrackRemote> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackRemote> tracks) {
        this.tracks = tracks;
    }
}

package com.valid.pruebatecnica.data.source;

import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

public interface TrackDataSource {
    interface LoadTracksCallack {
        void onTrackLoaded(List<Track> tracks);
        void onDataNotAvailable();
        void onError();
    }

    void getTracks(LoadTracksCallack callack);
    void saveTrack(List<Track> tracks);
}

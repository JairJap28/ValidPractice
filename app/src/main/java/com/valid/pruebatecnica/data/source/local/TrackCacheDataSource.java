package com.valid.pruebatecnica.data.source.local;

import android.util.SparseArray;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class TrackCacheDataSource implements TrackDataSource {
    private static TrackCacheDataSource mInstance;

    private final SparseArray<Track> cacheTracks = new SparseArray<>();

    public static TrackCacheDataSource getInstance(){
        if(mInstance == null) {
            mInstance = new TrackCacheDataSource();
        }
        return mInstance;
    }


    @Override
    public void getTracks(LoadTracksCallack callack) {
        if(cacheTracks.size() > 0) {
            List<Track> tracks = new ArrayList<>();
            for (int i = 0; i < cacheTracks.size(); i++) {
                int key = cacheTracks.keyAt(i);
                tracks.add(cacheTracks.get(key));
            }

            callack.onTrackLoaded(tracks);
        } else{
            callack.onDataNotAvailable();
        }
    }

    @Override
    public void saveTrack(List<Track> tracks) {
        cacheTracks.clear();
        for(Track track : tracks) {
            cacheTracks.put(track.getId(), track);
        }
    }
}

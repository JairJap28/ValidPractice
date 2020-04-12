package com.valid.pruebatecnica.data.source.track.repository;

import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

public class TrackRepository implements TrackDataSource {
    // region Properties
    private final TrackDataSource localTrack;
    private static TrackRepository mInstance;
    // endregion

    // region Constructor
    private TrackRepository(TrackDataSource localTrack) {
        this.localTrack = localTrack;
    }
    // endregion

    // region Class methods
    public static TrackRepository getInstance(TrackDataSource localTrack) {
        if(mInstance == null) {
            mInstance = new TrackRepository(localTrack);
        }
        return mInstance;
    }
    // endregion

    // region Override methods
    @Override
    public void getListData(LoadListCallback<Track> callback, int page) {
        localTrack.getListData(new LoadListCallback<Track>() {
            @Override
            public void onLoaded(List<Track> list) {
                callback.onLoaded(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        }, page);
    }

    @Override
    public void saveListData(List<Track> list) {
        localTrack.saveListData(list);
    }

    @Override
    public void getData(LoadSingleCallback<Track> callback) {

    }

    @Override
    public void saveData(Track object) {

    }

    @Override
    public void deleteAll() {
        localTrack.deleteAll();
    }
    // endregion
}

package com.valid.pruebatecnica.data.source.track.repository;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

public class TrackRepository implements TrackDataSource {

    private final TrackDataSource localTrack;
    private int page;
    private static TrackRepository mInstance;

    private TrackRepository(TrackDataSource localTrack) {
        this.localTrack = localTrack;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public static TrackRepository getInstance(TrackDataSource localTrack) {
        if(mInstance == null) {
            mInstance = new TrackRepository(localTrack);
        }
        return mInstance;
    }

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
}

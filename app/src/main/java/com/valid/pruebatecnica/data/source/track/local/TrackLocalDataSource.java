package com.valid.pruebatecnica.data.source.track.local;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.track.local.dao.TrackDao;
import com.valid.pruebatecnica.data.source.track.repository.TrackDataSource;
import com.valid.pruebatecnica.utils.DiskExecutor;

import java.util.List;
import java.util.concurrent.Executor;

public class TrackLocalDataSource implements TrackDataSource {

    private Executor executor;
    private TrackDao trackDao;

    private static TrackLocalDataSource mInstance;

    private TrackLocalDataSource(Executor executor, TrackDao trackDao) {
        this.executor = executor;
        this.trackDao = trackDao;
    }

    public static TrackLocalDataSource getInstance(TrackDao trackDao){
        if(mInstance == null) {
            mInstance = new TrackLocalDataSource(new DiskExecutor(), trackDao);
        }
        return mInstance;
    }

    @Override
    public void getListData(LoadListCallback<Track> callback, int page) {
        Runnable runnable = () -> {
            List<Track> tracks = trackDao.getTracks(page * 50);
            if(!tracks.isEmpty()) {
                callback.onLoaded(tracks);
            } else {
                callback.onDataNotAvailable();
            }
        };
        executor.execute(runnable);
    }

    @Override
    public void saveListData(List<Track> tracks) {
        Runnable runnable = () -> {
            trackDao.insertAllTracks(tracks);
        };
        executor.execute(runnable);
    }

    @Override
    public void getData(LoadSingleCallback<Track> callback) {

    }

    @Override
    public void saveData(Track object) {

    }

    @Override
    public void deleteAll() {
        Runnable runnable = () -> {
            trackDao.deleteAllTracks();
        };
        executor.execute(runnable);
    }
}

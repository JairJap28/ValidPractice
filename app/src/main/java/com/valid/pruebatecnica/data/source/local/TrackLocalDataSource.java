package com.valid.pruebatecnica.data.source.local;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.TrackDataSource;
import com.valid.pruebatecnica.data.source.local.dao.TrackDao;
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
    public void getTracks(LoadTracksCallack callack) {
        Runnable runnable = () -> {
            List<Track> tracks = trackDao.getTracks();
            if(!tracks.isEmpty()) {
                callack.onTrackLoaded(tracks);
            } else {
                callack.onDataNotAvailable();
            }
        };
        executor.execute(runnable);
    }

    @Override
    public void saveTrack(List<Track> tracks) {
        Runnable runnable = () -> {
            trackDao.insertAllTracks(tracks);
        };
        executor.execute(runnable);
    }
}

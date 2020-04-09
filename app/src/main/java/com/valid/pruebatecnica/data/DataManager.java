package com.valid.pruebatecnica.data;

import com.valid.pruebatecnica.data.source.TrackRepository;
import com.valid.pruebatecnica.data.source.local.TrackCacheDataSource;
import com.valid.pruebatecnica.data.source.local.TrackLocalDataSource;
import com.valid.pruebatecnica.data.source.local.dao.TrackDao;
import com.valid.pruebatecnica.data.source.local.database.GeoDatabase;
import com.valid.pruebatecnica.data.source.remote.TrackRemoteDataSource;
import com.valid.pruebatecnica.data.source.remote.services.TrackApi;
import com.valid.pruebatecnica.data.source.remote.services.TrackService;

public class DataManager {

    private static DataManager mInstance;

    public static synchronized DataManager getInstance(){
        if(mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public TrackRepository getTrackRepository(){
        TrackApi trackApi= TrackService.getInstance().getTrackApi();
        TrackRemoteDataSource trackRemote =TrackRemoteDataSource.getInstance(trackApi);

        TrackDao trackDao = GeoDatabase.getInstance().trackDao();
        TrackLocalDataSource trackLocal = TrackLocalDataSource.getInstance(trackDao);

        TrackCacheDataSource trackCache = TrackCacheDataSource.getInstance();

        return TrackRepository.getInstance(trackRemote, trackLocal, trackCache);
    }
}

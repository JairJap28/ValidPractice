package com.valid.pruebatecnica.data;

import com.valid.pruebatecnica.data.source.geo.repository.GeoRepository;
import com.valid.pruebatecnica.data.source.track.local.TrackLocalDataSource;
import com.valid.pruebatecnica.data.source.track.local.dao.TrackDao;
import com.valid.pruebatecnica.data.base.database.GeoDatabase;
import com.valid.pruebatecnica.data.source.geo.remote.GeoRemoteDataSource;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackApi;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackService;

public class DataManager {

    private static DataManager mInstance;

    public static synchronized DataManager getInstance(){
        if(mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public GeoRepository getTrackRepository(){
        TrackApi trackApi= TrackService.getInstance().getTrackApi();
        GeoRemoteDataSource trackRemote = GeoRemoteDataSource.getInstance(trackApi);

        TrackDao trackDao = GeoDatabase.getInstance().trackDao();
        TrackLocalDataSource trackLocal = TrackLocalDataSource.getInstance(trackDao);

        GeoCacheDataSource trackCache = GeoCacheDataSource.getInstance();

        return GeoRepository.getInstance(trackRemote, trackLocal, trackCache);
    }
}

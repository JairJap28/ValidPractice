package com.valid.pruebatecnica.data.source.geo.remote.services;

import com.valid.pruebatecnica.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackService {
    // region Properties
    private static final String URL = BuildConfig.BASE_URL;
    private TrackApi trackApi;
    private static TrackService mInstance;
    // endregion

    // region Constructor
    private TrackService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        trackApi = retrofit.create(TrackApi.class);
    }
    // endregion

    // region Class methods
    public static TrackService getInstance(){
        if(mInstance == null) {
            mInstance = new TrackService();
        }
        return mInstance;
    }

    public TrackApi getTrackApi() { return trackApi; }
    // endregion
}

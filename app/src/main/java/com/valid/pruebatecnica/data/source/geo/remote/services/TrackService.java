package com.valid.pruebatecnica.data.source.geo.remote.services;

import com.valid.pruebatecnica.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackService {
    private static final String URL = BuildConfig.BASE_URL;

    private TrackApi trackApi;

    private static TrackService mInstance;

    public TrackService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        trackApi = retrofit.create(TrackApi.class);
    }

    public static TrackService getInstance(){
        if(mInstance == null) {
            mInstance = new TrackService();
        }
        return mInstance;
    }

    public TrackApi getTrackApi() { return trackApi; }
}

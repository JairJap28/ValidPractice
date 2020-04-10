package com.valid.pruebatecnica.data.source.geo.remote;

import com.valid.pruebatecnica.data.source.geo.repository.GeoDataSource;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoRemoteDataSource implements GeoDataSource {

    private static GeoRemoteDataSource mInstance;

    private TrackApi trackApi;

    public GeoRemoteDataSource(TrackApi trackApi) {
        this.trackApi = trackApi;
    }

    public static GeoRemoteDataSource getInstance(TrackApi trackApi) {
        if(mInstance == null) {
            mInstance = new GeoRemoteDataSource(trackApi);
        }
        return mInstance;
    }

    @Override
    public void getListData(LoadListCallback<TrackResponse> callback) {
        trackApi.getTracks().enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(Call<TrackResponse> call, Response<TrackResponse> response) {
                if(response.body() != null) {
                    callback.onLoaded(Collections.singletonList(response.body()));
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<TrackResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void saveListData(List<TrackResponse> list) {

    }
}

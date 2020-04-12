package com.valid.pruebatecnica.data.source.geo.remote;

import androidx.annotation.NonNull;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackApi;
import com.valid.pruebatecnica.data.source.geo.repository.GeoDataSource;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoRemoteDataSource implements GeoDataSource {
    // region Properties
    private static GeoRemoteDataSource mInstance;
    private int page;
    private TrackApi trackApi;

    // region Constructor
    private GeoRemoteDataSource(TrackApi trackApi) {
        this.trackApi = trackApi;
    }
    // endregion

    // region Class methods
    public static GeoRemoteDataSource getInstance(TrackApi trackApi) {
        if(mInstance == null) {
            mInstance = new GeoRemoteDataSource(trackApi);
        }
        return mInstance;
    }
    // endregion

    public void setPage(int page) {
        this.page = page;
    }

    private void getDataWithKey(LoadListCallback<TrackResponse> callback, String api_key, String country) {
        trackApi.getTracks(page, api_key, country).enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackResponse> call, @NonNull Response<TrackResponse> response) {
                if (response.body() != null) {
                    callback.onLoaded(Collections.singletonList(response.body()));
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackResponse> call, @NonNull Throwable t) {
                callback.onError();
            }
        });
    }
    // endregion

    // region Override methods
    @Override
    public void getListData(LoadListCallback<TrackResponse> callback, int page) {
        this.page = page;
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
                .setFetchTimeoutInSeconds(0)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(settings);

        firebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        firebaseRemoteConfig.activateFetched();
                        getDataWithKey(callback, firebaseRemoteConfig.getString("api_key"), firebaseRemoteConfig.getString("country"));
                    } else {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void saveListData(List<TrackResponse> list) {

    }

    @Override
    public void getData(LoadSingleCallback<TrackResponse> callback) {

    }

    @Override
    public void saveData(TrackResponse object) {

    }
    // endregion
}

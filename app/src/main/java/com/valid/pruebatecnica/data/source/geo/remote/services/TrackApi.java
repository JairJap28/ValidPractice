package com.valid.pruebatecnica.data.source.geo.remote.services;

import com.valid.pruebatecnica.BuildConfig;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrackApi {
    @GET(BuildConfig.SERVICE_TOP_TRACKS)
    Call<TrackResponse> getTracks();
}

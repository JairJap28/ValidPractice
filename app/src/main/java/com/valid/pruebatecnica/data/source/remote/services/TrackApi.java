package com.valid.pruebatecnica.data.source.remote.services;

import com.valid.pruebatecnica.BuildConfig;
import com.valid.pruebatecnica.data.source.remote.model.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrackApi {
    @GET(BuildConfig.BASE_URL + "?method=geo.gettoptracks&country=spain&api_key=YOUR_API_KEY&format=json")
    Call<TrackResponse> getTracks();
}

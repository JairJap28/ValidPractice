package com.valid.pruebatecnica.data.source.remote;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.TrackDataSource;
import com.valid.pruebatecnica.data.source.remote.model.TrackRemote;
import com.valid.pruebatecnica.data.source.remote.model.TrackResponse;
import com.valid.pruebatecnica.data.source.remote.services.TrackApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackRemoteDataSource implements TrackDataSource {

    private static TrackRemoteDataSource mInstance;

    private TrackApi trackApi;

    public TrackRemoteDataSource(TrackApi trackApi) {
        this.trackApi = trackApi;
    }

    public static TrackRemoteDataSource getInstance(TrackApi trackApi) {
        if(mInstance == null) {
            mInstance = new TrackRemoteDataSource(trackApi);
        }
        return mInstance;
    }

    @Override
    public void getTracks(LoadTracksCallack callack) {
        trackApi.getTracks().enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(Call<TrackResponse> call, Response<TrackResponse> response) {
                List<Track> tracks = new ArrayList<>();
                List<Artist> artists = new ArrayList<>();
                if(response.body() != null) {
                    for (TrackRemote trackRemote: response.body().getTracks()) {
                        tracks.add(new Track(trackRemote.getMbid(), trackRemote.getName(),
                                             trackRemote.getDuration(), trackRemote.getListeners(),
                                             trackRemote.getUrl()));
                        artists.add(trackRemote.getArtist());
                    }

                    if(tracks.size() > 0) {
                        callack.onTrackLoaded(tracks);
                    } else{
                        callack.onDataNotAvailable();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrackResponse> call, Throwable t) {
                callack.onError();
            }
        });
    }

    @Override
    public void saveTrack(List<Track> tracks) {

    }
}

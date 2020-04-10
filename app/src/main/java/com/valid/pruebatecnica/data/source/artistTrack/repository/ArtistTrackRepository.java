package com.valid.pruebatecnica.data.source.artistTrack.repository;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;
import com.valid.pruebatecnica.data.source.track.repository.TrackDataSource;

import java.util.List;

public class ArtistTrackRepository implements ArtistTrackDataSource {

    private final ArtistTrackDataSource localArtistTrack;

    public static ArtistTrackRepository mInstance;

    private ArtistTrackRepository(ArtistTrackDataSource localArtistTrack) {
        this.localArtistTrack = localArtistTrack;
    }

    public static ArtistTrackRepository getInstance(ArtistTrackDataSource localArtistTrack){
        if(mInstance == null) {
            mInstance = new ArtistTrackRepository(localArtistTrack);
        }
        return mInstance;
    }

    @Override
    public void getArtistByIdTrack(LoadSingleCallback<Artist> callback, String mbId) {
        localArtistTrack.getArtistByIdTrack(new LoadSingleCallback<Artist>() {
            @Override
            public void onLoaded(Artist artist) {
                callback.onLoaded(artist);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        }, mbId);
    }

    @Override
    public void getListData(LoadListCallback<ArtistTrack> callback) {

    }

    @Override
    public void saveListData(List<ArtistTrack> list) {

    }

    @Override
    public void getData(LoadSingleCallback<ArtistTrack> callback) {

    }

    @Override
    public void saveData(ArtistTrack artist) {
        localArtistTrack.saveData(artist);
    }
}
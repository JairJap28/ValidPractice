package com.valid.pruebatecnica.data.source.artistTrack.repository;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;

import java.util.List;

public class ArtistTrackRepository implements ArtistTrackDataSource {
    // region Properties
    private final ArtistTrackDataSource localArtistTrack;
    public static ArtistTrackRepository mInstance;
    // endregion

    // region Constructor
    private ArtistTrackRepository(ArtistTrackDataSource localArtistTrack) {
        this.localArtistTrack = localArtistTrack;
    }
    // endregion

    // region Class methods
    public static ArtistTrackRepository getInstance(ArtistTrackDataSource localArtistTrack){
        if(mInstance == null) {
            mInstance = new ArtistTrackRepository(localArtistTrack);
        }
        return mInstance;
    }
    // endregion

    // region Class methods
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
    public void getNumberSongsArtist(LoadSingleCallback<Integer> callback, String mbId) {
        localArtistTrack.getNumberSongsArtist(new LoadSingleCallback<Integer>() {
            @Override
            public void onLoaded(Integer numberSongs) {
                callback.onLoaded(numberSongs);
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
    public void getListData(LoadListCallback<ArtistTrack> callback, int page) {

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
    // endregion
}

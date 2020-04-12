package com.valid.pruebatecnica.data.source.artist.repository;

import com.valid.pruebatecnica.data.entity.Artist;

import java.util.List;

public class ArtistRepository implements ArtistDataSource{
    // region Properties
    private final ArtistDataSource localArtist;

    private static ArtistRepository mInstance;
    // endregion

    // region Constructor
    private ArtistRepository(ArtistDataSource localArtist) {
        this.localArtist = localArtist;
    }
    // endregion

    // region Class methods
    public static ArtistRepository getInstance(ArtistDataSource locarArtist){
        if(mInstance == null) {
            mInstance = new ArtistRepository(locarArtist);
        }
        return mInstance;
    }
    // endregion

    // region Override methods
    @Override
    public void getListData(LoadListCallback<Artist> callback, int page) {
        localArtist.getListData(new LoadListCallback<Artist>() {
            @Override
            public void onLoaded(List<Artist> listArtists) {
                callback.onLoaded(listArtists);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        }, page);
    }

    @Override
    public void saveListData(List<Artist> artists) {
        localArtist.saveListData(artists);
    }

    @Override
    public void getData(LoadSingleCallback<Artist> callback) {

    }

    @Override
    public void saveData(Artist object) {

    }

    @Override
    public void getArtistsByIdTrack(LoadSingleCallback<Artist> callback, String trackMbId) {
        localArtist.getArtistsByIdTrack(new LoadSingleCallback<Artist>() {
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
        }, trackMbId);
    }

    @Override
    public void deleteAll() {
        localArtist.deleteAll();
    }
    // endregion
}

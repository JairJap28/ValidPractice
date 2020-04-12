package com.valid.pruebatecnica.data.source.artist.local;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.source.artist.local.dao.ArtistDao;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistDataSource;
import com.valid.pruebatecnica.utils.DiskExecutor;

import java.util.List;
import java.util.concurrent.Executor;

public class ArtistLocalDataSource implements ArtistDataSource {

    // region Properties
    private Executor executor;
    private ArtistDao artistDao;

    private static ArtistLocalDataSource mInstance;
    // endregion

    // region Constructor
    private ArtistLocalDataSource(Executor executor, ArtistDao artistDao) {
        this.executor = executor;
        this.artistDao = artistDao;
    }
    // endregion

    // region Class methods
    public static ArtistLocalDataSource getInstance(ArtistDao artistDao){
        if(mInstance == null) {
            mInstance = new ArtistLocalDataSource(new DiskExecutor(), artistDao);
        }
        return mInstance;
    }
    // endregion

    // region Override methods
    @Override
    public void getListData(LoadListCallback<Artist> callback, int page) {
        Runnable runnable = () -> {
            List<Artist> artists = artistDao.getAllArtists();
            if (artists.size() > 0) callback.onLoaded(artists);
            else callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }

    @Override
    public void saveListData(List<Artist> list) {
        Runnable runnable = () -> {
            artistDao.insertAllArtists(list);
        };
        executor.execute(runnable);
    }

    @Override
    public void getData(LoadSingleCallback<Artist> callback) {

    }

    @Override
    public void saveData(Artist object) {

    }

    @Override
    public void getArtistsByIdTrack(LoadSingleCallback<Artist> callback, String trackMbId) {
        Runnable runnable = () -> {
            Artist artist = artistDao.getArtistById(trackMbId);
            if(artist != null) callback.onLoaded(artist);
            else callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }

    @Override
    public void deleteAll() {
        Runnable runnable = () -> {
            artistDao.deleteAllArtists();
        };
        executor.execute(runnable);
    }
    // endregion
}

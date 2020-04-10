package com.valid.pruebatecnica.data.source.artist.local;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.source.artist.local.dao.*;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistDataSource;
import com.valid.pruebatecnica.utils.DiskExecutor;

import java.util.List;
import java.util.concurrent.Executor;

public class ArtistLocalDataSource implements ArtistDataSource {

    private Executor executor;
    private ArtistDao artistDao;

    private static ArtistLocalDataSource mInstance;

    private ArtistLocalDataSource(Executor executor, ArtistDao artistDao) {
        this.executor = executor;
        this.artistDao = artistDao;
    }

    public ArtistLocalDataSource getInstance(ArtistDao artistDao){
        if(mInstance == null) {
            mInstance = new ArtistLocalDataSource(new DiskExecutor(), artistDao);
        }
        return mInstance;
    }

    @Override
    public void getListData(LoadListCallback<Artist> callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Artist> artists = artistDao.getAllArtists();
                if(artists.size() > 0) callback.onLoaded(artists);
                else callback.onDataNotAvailable();
            }
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
    public void getArtistsByIdTrack(LoadSingleCallback<Artist> callback, String trackMbId) {
        Runnable runnable = () -> {
            Artist artist = artistDao.getArtistById(trackMbId);
            if(artist != null) callback.onLoaded(artist);
            else callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }
}

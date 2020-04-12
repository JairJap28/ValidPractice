package com.valid.pruebatecnica.data.source.artistTrack.local;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;
import com.valid.pruebatecnica.data.source.artistTrack.local.dao.ArtistTrackDao;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackDataSource;
import com.valid.pruebatecnica.utils.DiskExecutor;

import java.util.List;
import java.util.concurrent.Executor;

public class ArtistTrackLocalDataSource implements ArtistTrackDataSource {
    // region Properties
    private Executor executor;
    private ArtistTrackDao artistTrackDao;
    public static ArtistTrackLocalDataSource mInstance;
    // endregion

    // region Constructor
    private ArtistTrackLocalDataSource(Executor executor, ArtistTrackDao artistTrackDao) {
        this.executor = executor;
        this.artistTrackDao = artistTrackDao;
    }
    // endregion

    // region Class methods
    public static ArtistTrackLocalDataSource getInstance(ArtistTrackDao artistTrackDao) {
        if(mInstance == null) {
            mInstance = new ArtistTrackLocalDataSource(new DiskExecutor(), artistTrackDao);
        }
        return mInstance;
    }
    // endregion

    // region Override methods
    @Override
    public void getArtistByIdTrack(DataStoreBase.LoadSingleCallback<Artist> callback, String mbId) {
        Runnable runnable = () -> {
            Artist artist = artistTrackDao.getArtistByIdTrack(mbId);
            if(artist != null) callback.onLoaded(artist);
            else callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }

    @Override
    public void getNumberSongsArtist(LoadSingleCallback<Integer> callback, String mbId) {
        Runnable runnable = () -> {
            int numberSongs = artistTrackDao.getNumberSongsArtist(mbId);
            callback.onLoaded(numberSongs);
        };
        executor.execute(runnable);
    }

    @Override
    public void getListData(LoadListCallback<ArtistTrack> callback, int page) {

    }

    @Override
    public void saveListData(List<ArtistTrack> list) {
        Runnable runnable = () -> {
            artistTrackDao.insertAll(list);
        };
        executor.execute(runnable);
    }

    @Override
    public void getData(LoadSingleCallback<ArtistTrack> callback) {

    }

    @Override
    public void saveData(ArtistTrack artistTrack) {
        Runnable runnable = () -> {
            artistTrackDao.insert(artistTrack);
        };
        executor.execute(runnable);
    }
    // endregion
}

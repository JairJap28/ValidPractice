package com.valid.pruebatecnica.di;

import com.valid.pruebatecnica.data.base.database.GeoDatabase;
import com.valid.pruebatecnica.data.source.artist.local.ArtistLocalDataSource;
import com.valid.pruebatecnica.data.source.artist.local.dao.ArtistDao;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistDataSource;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistRepository;
import com.valid.pruebatecnica.data.source.artistTrack.local.ArtistTrackLocalDataSource;
import com.valid.pruebatecnica.data.source.artistTrack.local.dao.ArtistTrackDao;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackDataSource;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackRepository;
import com.valid.pruebatecnica.data.source.geo.remote.GeoRemoteDataSource;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackApi;
import com.valid.pruebatecnica.data.source.geo.remote.services.TrackService;
import com.valid.pruebatecnica.data.source.geo.repository.GeoRepository;
import com.valid.pruebatecnica.data.source.track.local.TrackLocalDataSource;
import com.valid.pruebatecnica.data.source.track.local.dao.TrackDao;
import com.valid.pruebatecnica.data.source.track.repository.TrackRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    GeoRepository provideGeoRepository() {
        TrackApi trackApi= TrackService.getInstance().getTrackApi();
        GeoRemoteDataSource trackRemote = GeoRemoteDataSource.getInstance(trackApi);

        TrackDao trackDao = GeoDatabase.getInstance().trackDao();
        TrackLocalDataSource trackLocal = TrackLocalDataSource.getInstance(trackDao);

        ArtistDao artistDao = GeoDatabase.getInstance().artistDao();
        ArtistLocalDataSource artistLocal = ArtistLocalDataSource.getInstance(artistDao);

        ArtistTrackDao artistTrackDao = GeoDatabase.getInstance().artistTrackDao();
        ArtistTrackDataSource artistTrackLocal = ArtistTrackLocalDataSource.getInstance(artistTrackDao);

        return GeoRepository.getInstance(trackRemote, trackLocal, artistLocal, artistTrackLocal);
    }

    @Provides
    TrackRepository provideTrackRepository(){
        TrackDao trackDao = GeoDatabase.getInstance().trackDao();
        TrackLocalDataSource trackLocal = TrackLocalDataSource.getInstance(trackDao);

        return TrackRepository.getInstance(trackLocal);
    }

    @Provides
    ArtistRepository provideArtistRepository() {
        ArtistDao artistDao = GeoDatabase.getInstance().artistDao();
        ArtistDataSource localArtist = ArtistLocalDataSource.getInstance(artistDao);

        return ArtistRepository.getInstance(localArtist);
    }

    @Provides
    ArtistTrackRepository provideArtistTrackRepository(){
        ArtistTrackDao artistTrackDao = GeoDatabase.getInstance().artistTrackDao();
        ArtistTrackDataSource artistTrackLocal = ArtistTrackLocalDataSource.getInstance(artistTrackDao);

        return ArtistTrackRepository.getInstance(artistTrackLocal);
    }
}

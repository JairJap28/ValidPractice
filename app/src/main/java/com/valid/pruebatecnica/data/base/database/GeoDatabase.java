package com.valid.pruebatecnica.data.base.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.valid.pruebatecnica.App;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.artist.local.dao.ArtistDao;
import com.valid.pruebatecnica.data.source.artistTrack.local.dao.ArtistTrackDao;
import com.valid.pruebatecnica.data.source.track.local.dao.TrackDao;

@Database(entities = {Track.class, Artist.class, ArtistTrack.class}, version = 8, exportSchema = false)
public abstract class GeoDatabase extends RoomDatabase {
    public abstract TrackDao trackDao();
    public abstract ArtistDao artistDao();
    public abstract ArtistTrackDao artistTrackDao();

    private static GeoDatabase instance;

    public static synchronized GeoDatabase getInstance(){
        if(instance == null) {
            instance = Room.databaseBuilder(App.getInstance(), GeoDatabase.class, "Geo.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

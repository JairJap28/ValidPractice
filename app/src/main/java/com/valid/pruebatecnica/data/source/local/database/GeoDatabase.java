package com.valid.pruebatecnica.data.source.local.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.valid.pruebatecnica.App;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.local.dao.ArtistDao;
import com.valid.pruebatecnica.data.source.local.dao.TrackDao;

@Database(entities = {Track.class, Artist.class}, version = 1, exportSchema = false)
public abstract class GeoDatabase extends RoomDatabase {
    public abstract TrackDao trackDao();
    public abstract ArtistDao artistDao();

    private static GeoDatabase instance;

    public static synchronized GeoDatabase getInstance(){
        if(instance == null) {
            instance = Room.databaseBuilder(App.getInstance(), GeoDatabase.class, "Geo.db").build();
        }
        return instance;
    }
}

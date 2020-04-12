package com.valid.pruebatecnica.data.source.track.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

@Dao
public interface TrackDao {
    @Query("SELECT * FROM tracks ORDER BY id ASC LIMIT :limit")
    List<Track> getTracks(int limit);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrack(Track track);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTracks(List<Track> tracks);

    @Query("DELETE FROM tracks")
    void deleteAllTracks();
}

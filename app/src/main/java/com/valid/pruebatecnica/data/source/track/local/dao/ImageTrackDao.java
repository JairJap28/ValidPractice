package com.valid.pruebatecnica.data.source.track.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.valid.pruebatecnica.data.entity.ImageTrack;

import java.util.List;

@Dao
public interface ImageTrackDao {
    @Query("SELECT * FROM ImageTrack WHERE idTrack=:idTrack")
    List<ImageTrack> getImagesByIdSong(int idTrack);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ImageTrack imageTrack);

    @Query("DELETE FROM ImageTrack")
    void deleteAllImageTrack();
}

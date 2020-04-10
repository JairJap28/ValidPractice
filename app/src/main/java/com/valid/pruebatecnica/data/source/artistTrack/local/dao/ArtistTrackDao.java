package com.valid.pruebatecnica.data.source.artistTrack.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;

import java.util.List;

@Dao
public interface ArtistTrackDao {
    @Query("SELECT * FROM artists " +
            "INNER JOIN ArtistTrack " +
            "ON artist_id = idArtist " +
            "WHERE idSong=:mbId " +
            "LIMIT 1")
    Artist getArtistByIdTrack(String mbId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArtistTrack artistTrack);

    @Query("DELETE FROM ArtistTrack")
    void deleteAll();
}

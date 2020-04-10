package com.valid.pruebatecnica.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ArtistTrack")
public class ArtistTrack {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int idArtist;

    private int idSong;

    public ArtistTrack(int idArtist, int idSong) {
        this.idArtist = idArtist;
        this.idSong = idSong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public int getIdSong() {
        return idSong;
    }
}

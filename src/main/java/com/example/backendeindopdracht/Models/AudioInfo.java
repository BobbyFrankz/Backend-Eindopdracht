package com.example.backendeindopdracht.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "audioinfo")
public class AudioInfo {
    @Id
    @GeneratedValue
    private Long id;
    String genre;
    int bpm;
    String artist;

    @OneToOne(mappedBy = "audioInfo")
    @JsonIgnore
    private FileDB fileDB;




    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }

    public AudioInfo(String genre, int bpm,String artist, FileDB fileDB) {
        this.genre = genre;
        this.bpm = bpm;
        this.artist = artist;
        this.fileDB = fileDB;
    }
}



package com.example.backendeindopdracht.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AudioInfoDto {


    String genre;
    int bpm;
    String artist;


    public AudioInfoDto(String genre, int bpm, String artist) {
        this.genre = genre;
        this.bpm = bpm;
        this.artist = artist;
    }
}



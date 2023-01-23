package com.example.backendeindopdracht.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AudioInfoDto {


    String genre;
    int bpm;


    public AudioInfoDto(String genre, int bpm) {
        this.genre = genre;
        this.bpm = bpm;
    }
}



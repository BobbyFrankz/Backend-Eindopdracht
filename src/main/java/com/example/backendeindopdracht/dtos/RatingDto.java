package com.example.backendeindopdracht.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RatingDto {

    private Long fileDB;
    private int score;
    private String comment;

    public RatingDto(Long fileDB, int score, String comment) {
        this.fileDB = fileDB;
        this.score = score;
        this.comment = comment;
    }
}

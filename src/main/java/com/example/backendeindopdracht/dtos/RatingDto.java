package com.example.backendeindopdracht.dtos;

import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RatingDto {

    private String userName;
    private int score;
    private String comment;
    private User user;
    private FileDB fileDB;

    public RatingDto() {
    }

    public RatingDto(int score, String comment) {

        this.score = score;
        this.comment = comment;
    }
}

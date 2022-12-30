package com.example.backendeindopdracht.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AudioFileDto {
    private Long id;
    private String title;
    private String artist;
    private String typeOfFile;
    private Double duration;
    private String filePath;
}

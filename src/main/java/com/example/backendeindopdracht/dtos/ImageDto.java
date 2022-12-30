package com.example.backendeindopdracht.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ImageDto {
    private Long id;
    private String name;
    private byte[] data;

}

package com.example.backendeindopdracht.dtos;

import com.example.backendeindopdracht.Models.AudioInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDBDto {


    private String id;

    private String name;


    private String type;


    private byte[] data;


    private AudioInfo audioInfo;

}

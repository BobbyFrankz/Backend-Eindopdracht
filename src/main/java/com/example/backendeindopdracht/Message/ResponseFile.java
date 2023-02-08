package com.example.backendeindopdracht.Message;

import com.example.backendeindopdracht.Models.AudioInfo;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.dtos.AudioInfoDto;

import java.util.List;

public class ResponseFile {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;
    private List<Rating> ratings;

    private AudioInfoDto audioInfoDto;


    public ResponseFile(String id, String name, String url, String type, long size, AudioInfoDto audioInfoDto, List<Rating> ratings) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.audioInfoDto = audioInfoDto;
        this.ratings = ratings;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public AudioInfoDto getAudioInfo() {
        return audioInfoDto;
    }

    public void setAudioInfo(AudioInfoDto audioInfoDto) {
        this.audioInfoDto = audioInfoDto;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }


}

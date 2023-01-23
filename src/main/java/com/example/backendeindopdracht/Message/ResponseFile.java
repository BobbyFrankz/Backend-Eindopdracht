package com.example.backendeindopdracht.Message;

import com.example.backendeindopdracht.Models.AudioInfo;
import com.example.backendeindopdracht.dtos.AudioInfoDto;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;

    private AudioInfoDto audioInfoDto;


    public ResponseFile(String name, String url, String type, long size, AudioInfoDto audioInfoDto) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.audioInfoDto = audioInfoDto;
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
}

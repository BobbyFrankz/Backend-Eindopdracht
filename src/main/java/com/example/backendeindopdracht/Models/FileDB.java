package com.example.backendeindopdracht.Models;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "files")
public class FileDB {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    @Id
    private String id;

    private String name;


    private String type;

    @Lob
    private byte[] data;

    @OneToOne
    private AudioInfo audioInfo;

    @OneToMany
    private Rating rating;

    public FileDB() {
    }


    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;


    }

    public void setAudioInfo(AudioInfo audioInfo) {
        this.audioInfo = audioInfo;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public AudioInfo getAudioInfo() {
        return audioInfo;
    }
}

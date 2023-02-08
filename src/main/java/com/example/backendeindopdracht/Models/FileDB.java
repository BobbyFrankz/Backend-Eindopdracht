package com.example.backendeindopdracht.Models;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "files")
public class FileDB {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;
    @Column(unique = true)
    private String name;


    private String type;

    @Lob
    private byte[] data;

    @OneToOne
    private AudioInfo audioInfo;

    @OneToMany(
            mappedBy = "fileDB",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rating> ratings = new ArrayList<>();

    public FileDB() {
    }


    public FileDB( String name, String type, byte[] data) {
        this.id = getId();
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

//    public String setId(String id) {
//        this.id = id;
//    }

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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}

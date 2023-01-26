package com.example.backendeindopdracht.Models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "image")
    private User user;

    public Image() {
    }


    public Image(String name, byte[] data) {

        this.name = name;
        this.data = data;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUrl(String imageUrl2) {
    }
}
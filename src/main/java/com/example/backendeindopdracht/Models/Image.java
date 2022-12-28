package com.example.backendeindopdracht.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor


@Entity
@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    public Image() {
    }

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
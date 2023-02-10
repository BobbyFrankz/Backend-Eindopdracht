package com.example.backendeindopdracht.Models;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue
    private int id;

    private String userName;

    private int score;
    private String comment;


    @JsonIncludeProperties("name")
    @ManyToOne(fetch = FetchType.LAZY)
    private FileDB fileDB;

    @ManyToOne
    @JsonIncludeProperties("username")
    private User user;


    public Rating(String userName, FileDB fileDB, int score, String comment, LocalDateTime createdAt) {
        this.userName = userName;
        this.fileDB = fileDB;
        this.score = score;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FileDB getFileDB() {
        return fileDB;
    }

    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

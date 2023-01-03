package com.example.backendeindopdracht.Models;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;


}

package com.example.backendeindopdracht.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;


}

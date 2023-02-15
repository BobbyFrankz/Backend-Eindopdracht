package com.example.backendeindopdracht.Repositories;

import com.example.backendeindopdracht.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}


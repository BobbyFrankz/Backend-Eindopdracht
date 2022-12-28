package com.example.backendeindopdracht.Repositories;

import com.example.backendeindopdracht.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}


package com.example.backendeindopdracht.Repositories;

import com.example.backendeindopdracht.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
}

package com.example.backendeindopdracht.Repositories;


import com.example.backendeindopdracht.Models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}

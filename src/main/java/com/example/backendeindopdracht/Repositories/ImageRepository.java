package com.example.backendeindopdracht.Repositories;


import com.example.backendeindopdracht.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImageRepository extends JpaRepository<Image, String> {


}
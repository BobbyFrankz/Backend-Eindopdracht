package com.example.backendeindopdracht.Repositories;

import com.example.backendeindopdracht.Models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepository extends JpaRepository<Rating, String> {

}
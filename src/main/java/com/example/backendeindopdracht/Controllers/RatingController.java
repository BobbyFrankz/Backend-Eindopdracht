package com.example.backendeindopdracht.Controllers;


import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.dtos.RatingDto;
import com.example.backendeindopdracht.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("")
    public ResponseEntity<Rating> createRating(@RequestBody RatingDto ratingDto) {
        ratingService.createRating(ratingDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{rating_id}")
    public ResponseEntity<Void> deleteRating(@PathVariable String rating_id) {
        ratingService.deleteRating(rating_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rating_id}")
    public ResponseEntity<Rating> fetchRating(@PathVariable String rating_id) {
        Rating rating = ratingService.fetchRating(rating_id);
        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> fetchAllRatings() {
        List<Rating> ratings = ratingService.fetchAllRating();
        return ResponseEntity.ok(ratings);
    }
}


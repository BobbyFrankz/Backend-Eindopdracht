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

    @PostMapping("/{fileId}/{username}")
    public ResponseEntity<Rating> createRating(@RequestBody RatingDto ratingDto, @PathVariable String fileId, @PathVariable String username) {
        ratingService.createRating(ratingDto, fileId, username);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{rating_id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer rating_id) {
        ratingService.deleteRating(rating_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rating_id}")
    public ResponseEntity<RatingDto> fetchRating(@PathVariable Integer rating_id) {
        RatingDto ratingDto = ratingService.fetchRating(rating_id);
        return ResponseEntity.ok(ratingDto);
    }

    @GetMapping("")
    public ResponseEntity<List<RatingDto>> fetchAllRatings() {
        List<RatingDto> ratings = ratingService.fetchAllRating();
        return ResponseEntity.ok(ratings);
    }
}


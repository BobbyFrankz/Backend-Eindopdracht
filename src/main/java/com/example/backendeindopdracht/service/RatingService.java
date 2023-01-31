package com.example.backendeindopdracht.service;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.Repositories.RatingRepository;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public void rateFile(Long fileId, int score, String comment) {
        FileDB fileDB = fileDBRepository.findById(String.valueOf(fileId)).get();
        Rating rating = new Rating(null, fileDB, score, comment, LocalDateTime.now());
        ratingRepository.save(rating);
    }
    @Transactional
    public void deleteRating(int rating_id) {

        ratingRepository.deleteById(rating_id);
    }

    @Transactional
    public Rating fetchRating(int rating_id) {

        return ratingRepository.findById(rating_id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Transactional
    public List<Rating> fetchAllRating() {

        return ratingRepository.findAll();
    }
}

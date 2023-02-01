package com.example.backendeindopdracht.service;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.Repositories.RatingRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.dtos.RatingDto;
import com.example.backendeindopdracht.dtos.UserDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public String createRating(RatingDto ratingDto) {
        Rating newRating = new Rating();

        newRating.setUserName(ratingDto.getUserName());
        newRating.setScore(ratingDto.getScore());
        newRating.setComment(ratingDto.getComment());
        newRating.setUser(ratingDto.getUser());
        newRating.setFileDB(ratingDto.getFileDB());


       Rating savedRating =  ratingRepository.save(newRating);
       assignRatingToUser(ratingDto.getUserName(), ratingDto.getUserName());

        return savedRating.getUserName();
    }

    public void assignRatingToUser(String ratingId, String userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);

        if (optionalRating.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            Rating rating = optionalRating.get();
            rating.setUser(user);
            ratingRepository.save(rating);
        } else {
            throw new RecordNotFoundException("User or rating not found");
        }


    }
    @Transactional
    public void deleteRating(String rating_id) {

        ratingRepository.deleteById(rating_id);
    }

    @Transactional
    public Rating fetchRating(String rating_id) {

        return ratingRepository.findById(rating_id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Transactional
    public List<Rating> fetchAllRating() {

        return ratingRepository.findAll();
    }

    public static Rating toRating(RatingDto ratingDto) {

        var rating = new Rating();

        rating.setUserName(ratingDto.getUserName());
        rating.setComment(ratingDto.getComment());
        rating.setScore(ratingDto.getScore());
        rating.setUser(ratingDto.getUser());


        return rating;
    }
    public static RatingDto fromRating(Rating rating) {

        var ratingDto = new RatingDto();

        ratingDto.setUserName(rating.getUserName());
        ratingDto.setComment(rating.getComment());
        ratingDto.setScore(rating.getScore());
        ratingDto.setUser(rating.getUser());


        return ratingDto;
    }
}

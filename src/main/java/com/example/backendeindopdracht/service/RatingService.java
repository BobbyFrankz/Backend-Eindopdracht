package com.example.backendeindopdracht.service;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.Repositories.RatingRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.dtos.RatingDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
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

    public String createRating(RatingDto ratingDto, String nameId, String username) {
        Rating newRating = new Rating();

        newRating.setUserName(ratingDto.getUserName());
        newRating.setScore(ratingDto.getScore());
        newRating.setComment(ratingDto.getComment());
        newRating.setUser(ratingDto.getUser());



       newRating = assignRatingToFileDB(newRating, nameId);
       newRating = assignRatingToUser(newRating, username);
       Rating savedRating =  ratingRepository.save(newRating);

        return savedRating.getUserName();
    }

    public Rating assignRatingToUser(Rating rating, String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            rating.setUser(user);
            return rating;
        } else {
            throw new RecordNotFoundException("User or rating not found");
        }

    }

    @Transactional
    public Rating assignRatingToFileDB(Rating rating, String fileId) {

        Optional<FileDB> optionalFileDB = fileDBRepository.findById(fileId);

        if (optionalFileDB.isPresent()) {
            FileDB fileDB = optionalFileDB.get();

            rating.setFileDB(fileDB);
            return rating;
        } else {
            throw new RecordNotFoundException("FileDB or rating not found");
        }
    }

    @Transactional
    public void deleteRating(Integer rating_id) {

        ratingRepository.deleteById(rating_id);
    }

    @Transactional
    public RatingDto fetchRating(Integer rating_id) {
        Rating rating = ratingRepository.findById(rating_id).orElseThrow(() -> new RecordNotFoundException());
        return fromRating(rating);
    }

    @Transactional
    public List<RatingDto> fetchAllRating() {

        List<Rating> ratinglist = ratingRepository.findAll();
        List<RatingDto> ratingDtoList = new ArrayList<>();
        for(Rating rating : ratinglist){
            RatingDto ratingDto = fromRating(rating);
             ratingDtoList.add(ratingDto);
        }
        return ratingDtoList;
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
        ratingDto.setFileName(rating.getFileDB().getName());


        return ratingDto;
    }

}

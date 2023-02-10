package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.Repositories.RatingRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.dtos.RatingDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static com.example.backendeindopdracht.service.RatingService.fromRating;
import static com.example.backendeindopdracht.service.RatingService.toRating;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    FileDBRepository fileDBRepository;

    @Test
    public void testCreateRating() {
        User user = new User();
        user.setUsername("user1");
        Rating rating = new Rating();
        FileDB fileDB = new FileDB();

        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(ratingRepository.save(any(Rating.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(fileDBRepository.findById(any())).thenReturn(Optional.of(fileDB));

        RatingDto ratingDto = new RatingDto();
        ratingDto.setUserName("rating1");
        ratingDto.setScore(4);
        ratingDto.setComment("good");
        ratingDto.setUser(user);

        String userName = ratingService.createRating(ratingDto,"rating1","user1");

        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(ratingArgumentCaptor.capture());

        Rating createdRating = ratingArgumentCaptor.getValue();
        assertEquals("rating1", createdRating.getUserName());
        assertEquals(4, createdRating.getScore());
        assertEquals("good", createdRating.getComment());
        assertEquals(user, createdRating.getUser());
    }



    @Test
    public void testAssignRatingToFileDB() {
        // Given

        String fileId = "file1";
        FileDB fileDB = new FileDB();
        Rating rating = new Rating();

        when(fileDBRepository.findById(fileId)).thenReturn(Optional.of(fileDB));


        // When
        ratingService.assignRatingToFileDB(rating,fileId);

        // Then
        verify(fileDBRepository).findById(fileId);
        assertEquals(fileDB, rating.getFileDB());
    }


    @Test
    public void testDeleteRating() {
        // Given
        Integer ratingId = 1;

        // When
        ratingService.deleteRating(ratingId);

        // Then
        verify(ratingRepository).deleteById(ratingId);
    }



    @Test
    public void testFetchAllRating() {
        // Given
        Rating rating1 = new Rating();
        rating1.setUserName("user1");
        rating1.setComment("comment1");
        rating1.setScore(5);
        User user1 = new User();
        rating1.setUser(user1);
        FileDB fileDB1 = new FileDB();
        fileDB1.setName("file1");
        rating1.setFileDB(fileDB1);

        Rating rating2 = new Rating();
        rating2.setUserName("user2");
        rating2.setComment("comment2");
        rating2.setScore(3);
        User user2 = new User();
        rating2.setUser(user2);
        FileDB fileDB2 = new FileDB();
        fileDB2.setName("file2");
        rating2.setFileDB(fileDB2);

        List<Rating> ratingList = Arrays.asList(rating1, rating2);
        when(ratingRepository.findAll()).thenReturn(ratingList);

        // When
        List<RatingDto> result = ratingService.fetchAllRating();

        // Then
        assertThat(result.size(), is(ratingList.size()));
        assertThat(result.get(0).getUserName(), is(rating1.getUserName()));
        assertThat(result.get(0).getComment(), is(rating1.getComment()));
        assertThat(result.get(0).getScore(), is(rating1.getScore()));
        assertThat(result.get(0).getUser(), is(rating1.getUser()));
        assertThat(result.get(0).getFileName(), is(fileDB1.getName()));

        assertThat(result.get(1).getUserName(), is(rating2.getUserName()));
        assertThat(result.get(1).getComment(), is(rating2.getComment()));
        assertThat(result.get(1).getScore(), is(rating2.getScore()));
        assertThat(result.get(1).getUser(), is(rating2.getUser()));
        assertThat(result.get(1).getFileName(), is(fileDB2.getName()));
    }


    @Test
    public void testToRating() {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setUserName("user1");
        ratingDto.setComment("comment1");
        ratingDto.setScore(5);
        User user = new User();
        ratingDto.setUser(user);


        Rating result = toRating(ratingDto);


        assertThat(result.getUserName(), is(ratingDto.getUserName()));
        assertThat(result.getComment(), is(ratingDto.getComment()));
        assertThat(result.getScore(), is(ratingDto.getScore()));
        assertThat(result.getUser(), is(ratingDto.getUser()));
    }

    @Test
    public void testFromRating() {
        // Given
        Rating rating = new Rating();
        rating.setUserName("user1");
        rating.setComment("comment1");
        rating.setScore(5);
        User user = new User();
        rating.setUser(user);
        FileDB fileDB = new FileDB();
        fileDB.setName("file1");
        rating.setFileDB(fileDB);

        // When
        RatingDto result = fromRating(rating);

        // Then
        assertThat(result.getUserName(), is(rating.getUserName()));
        assertThat(result.getComment(), is(rating.getComment()));
        assertThat(result.getScore(), is(rating.getScore()));
        assertThat(result.getUser(), is(rating.getUser()));
        assertThat(result.getFileName(), is(rating.getFileDB().getName()));
    }
}
package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.ImageRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.dtos.UserDto;

import com.example.backendeindopdracht.exceptions.UsernameNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageRepository imageRepository;


    @InjectMocks
    private UserService userService;

    @Test
    void getUsersTest() {
        // Arrange
        User user1 = new User("testUsername1", "testPassword1", true, "testApiKey1", "test1@test.nl", true,null, new ArrayList<>(), null);
        User user2 = new User("testUsername2", "testPassword2", false, "testApiKey2", "test2@test.nl", false,null,  new ArrayList<>(), null);
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDto> userDtos = UserService.getUsers();

        // Assert
        assertEquals(2, userDtos.size());
        assertEquals("testUsername1", userDtos.get(0).getUsername());
        assertEquals("testUsername2", userDtos.get(1).getUsername());
        assertTrue(userDtos.get(0).getEnabled());
        assertFalse(userDtos.get(1).getEnabled());
        assertEquals("testApiKey1", userDtos.get(0).getApikey());
        assertEquals("testApiKey2", userDtos.get(1).getApikey());
        assertEquals("test1@test.nl", userDtos.get(0).getEmail());
        assertEquals("test2@test.nl", userDtos.get(1).getEmail());
        assertTrue(userDtos.get(0).getArtistOrProducer());
        assertFalse(userDtos.get(1).getArtistOrProducer());
        assertNull(userDtos.get(0).getAuthorities());
        assertNull(userDtos.get(1).getAuthorities());
        assertNull(userDtos.get(0).getImage());
        assertNull(userDtos.get(1).getImage());

    }

    @Test
    void getUser_ValidUsername_ReturnsUserDto() {

        String username = "testUsername1";
        User user = new User(username, "testPassword1", true, "testApiKey", "test@test.nl", true, null, null, null);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("someId1", null, 5, "someDescription1", LocalDateTime.now()));
        ratings.add(new Rating("someId2", null, 4, "someDescription2", LocalDateTime.now()));
        user.setRating(ratings);
        UserDto expectedUserDto = UserService.fromUser(user);
        when(userRepository.findById(username)).thenReturn(Optional.of(user));

        UserDto actualUserDto = userService.getUser(username);

        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void getUser_InvalidUsername_ThrowsException() {

        String username = "invalidUsername";
        when(userRepository.findById(username)).thenReturn(Optional.empty());


        assertThrows(UsernameNotFoundException.class, () -> userService.getUser(username));
    }

    @Test
    void deleteUser() {

        String username = "testUsername";


        userService.deleteUser(username);


        verify(userRepository).deleteById(username);
    }

    @Test
    void fromUser() {

        User user = new User("testUsername1", "testPassword1", true, "testApiKey", "test@test.nl", true, null, null, null);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("someId1", null, 5, "someDescription1", LocalDateTime.now()));
        ratings.add(new Rating("someId2", null, 4, "someDescription2", LocalDateTime.now()));
        user.setRating(ratings);


        UserDto userDto = UserService.fromUser(user);


        assertEquals("testUsername1", userDto.getUsername());
        assertEquals("testPassword1", userDto.getPassword());
        assertTrue(userDto.getEnabled());
        assertEquals("testApiKey", userDto.getApikey());
        assertEquals("test@test.nl", userDto.getEmail());
        assertTrue(userDto.getArtistOrProducer());
        assertNull(userDto.getAuthorities());
        assertNull(userDto.getImage());

    }
    @Test
    void toUser() {

        UserDto userDto = new UserDto();
        userDto.setUsername("testUsername1");
        userDto.setPassword("testAddress1");
        userDto.setEnabled(true);
        userDto.setApikey("testApiKey");
        userDto.setEmail("test@test.nl");

        User user = UserService.toUser(userDto);

        assertEquals("testUsername1", user.getUsername());
        assertEquals("testAddress1", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals("testApiKey", user.getApikey());
        assertEquals("test@test.nl", user.getEmail());
    }
}
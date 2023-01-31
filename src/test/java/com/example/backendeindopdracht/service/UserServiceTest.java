package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.ImageRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.dtos.UserDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void getUsersTest() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("John Doe");
        user1.setPassword("password1");
        user1.setEmail("johndoe@example.com");
        users.add(user1);
        User user2 = new User();
        user2.setUsername("Jane Doe");
        user2.setPassword("password2");
        user2.setEmail("janedoe@example.com");
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> userDtos = userService.getUsers();
        assertEquals(2, userDtos.size());
        assertEquals("John Doe", userDtos.get(0).getUsername());
        assertEquals("johndoe@example.com", userDtos.get(0).getEmail());
        assertEquals("Jane Doe", userDtos.get(1).getUsername());
        assertEquals("janedoe@example.com", userDtos.get(1).getEmail());
    }

    @Test
    public void getUserTest() {
        User user = new User();
        user.setUsername("John Doe");
        user.setPassword("Doe John");
        user.setEmail("johndoe@example.com");

        when(userRepository.findById("John Doe")).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser("John Doe");
        assertEquals("John Doe", userDto.getUsername());
        assertEquals("johndoe@example.com", userDto.getEmail());
    }

    @Test
    void deleteUser() {
        // Arrange
        String username = "testUsername";

        // Act
        userService.deleteUser(username);

        // Assert
        verify(userRepository).deleteById(username);
    }

    @Test
    void fromUser() {
        //Arrange
        User user = new User("testUsername1", "testAddress1", true, "testApiKey", "test@test.nl", true, null, null);

        //Act
        UserDto userdto = UserService.fromUser(user);

        //Assert
        assertEquals("testUsername1", userdto.getUsername());
        assertEquals("testAddress1", userdto.getPassword());
        assertEquals(true, userdto.getEnabled());
        assertEquals("testApiKey", userdto.getApikey());
        assertEquals("test@test.nl", userdto.getEmail());
    }
    @Test
    void toUser() {
// Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("testUsername1");
        userDto.setPassword("testAddress1");
        userDto.setEnabled(true);
        userDto.setApikey("testApiKey");
        userDto.setEmail("test@test.nl");
// Act
        User user = UserService.toUser(userDto);
// Assert
        assertEquals("testUsername1", user.getUsername());
        assertEquals("testAddress1", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals("testApiKey", user.getApikey());
        assertEquals("test@test.nl", user.getEmail());
    }
}
package com.example.backendeindopdracht.Controllers;


import com.example.backendeindopdracht.Models.Authority;
import com.example.backendeindopdracht.dtos.UserDto;
import com.example.backendeindopdracht.service.ImageStorageService;
import com.example.backendeindopdracht.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private ImageStorageService imageStorageService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        UserController userController = new UserController(userService, imageStorageService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        // Arrange
        List<UserDto> expectedUserDtos = createMockUserDtos();
        when(userService.getUsers()).thenReturn(expectedUserDtos);

        // Act
        MvcResult result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        List<UserDto> actualUserDtos = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<UserDto>>() {
                });
        assertThat(actualUserDtos, containsInAnyOrder(expectedUserDtos.toArray()));
    }

    @Test
    public void testGetUser() throws Exception {
        // Arrange
        UserDto expectedUserDto = createMockUserDto();
        when(userService.getUser("user1")).thenReturn(expectedUserDto);

        // Act
        MvcResult result = mockMvc.perform(get("/users/user1"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        UserDto actualUserDto = objectMapper.readValue(result.getResponse().getContentAsString(), UserDto.class);
        assertThat(actualUserDto, is(expectedUserDto));
    }



    private UserDto createMockUserDto() {
        UserDto user = new UserDto();
        user.setUsername("user1");
        user.setEmail("user1@example.com");
        user.setArtistOrProducer(true);
        user.setEnabled(true);
        user.setPassword("password1");
        user.setApikey("apikey1");
        user.setAuthorities(new HashSet<Authority>());

        return user;
    }

    private List<UserDto> createMockUserDtos() {
        List<UserDto> mockUserDtos = new ArrayList<>();

        UserDto user1 = new UserDto();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setArtistOrProducer(true);
        user1.setEnabled(true);
        user1.setPassword("password1");
        user1.setApikey("apikey1");
        user1.setAuthorities(new HashSet<Authority>());


        UserDto user2 = new UserDto();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setArtistOrProducer(false);
        user2.setEnabled(true);
        user2.setPassword("password2");
        user2.setApikey("apikey2");
        user2.setAuthorities(new HashSet<Authority>());

        mockUserDtos.add(user1);
        mockUserDtos.add(user2);

        return mockUserDtos;
    }
}
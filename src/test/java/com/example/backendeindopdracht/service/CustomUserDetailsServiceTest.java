package com.example.backendeindopdracht.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.backendeindopdracht.Models.Authority;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.service.CustomUserDetailsService;
import com.example.backendeindopdracht.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserService userService;

    private CustomUserDetailsService customUserDetailsService;

    @Before
    public void setUp() {
        customUserDetailsService = new CustomUserDetailsService(userService);
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("test_user");
        user.setPassword("test_password");

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");


        when(userService.getUserByUsername(anyString())).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test_user");

        assertEquals("test_user", userDetails.getUsername());
        assertEquals("test_password", userDetails.getPassword());


    }
}
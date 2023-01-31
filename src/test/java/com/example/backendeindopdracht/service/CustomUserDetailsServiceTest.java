package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Authority;
import com.example.backendeindopdracht.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testuser";
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword("password");
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        userDto.setAuthorities(authorities);


        UserService userService = mock(UserService.class);
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(userService);


        when(userService.getUser(username)).thenReturn(userDto);


        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);


        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        verify(userService, times(1)).getUser(username);
    }

    private UserService verify(UserService userService, Times times) {
        return userService;
    }

}
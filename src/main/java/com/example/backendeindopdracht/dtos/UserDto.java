package com.example.backendeindopdracht.dtos;


import com.example.backendeindopdracht.Models.Authority;
import com.example.backendeindopdracht.Models.Image;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;




public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    public boolean artistOrProducer;
    public Set<Authority> authorities;
    public Image image;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public String getEmail() {
        return email;
    }

    public boolean getArtistOrProducer() {return artistOrProducer;}

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setArtistOrProducer(boolean artistOrProducer) {
        this.artistOrProducer = artistOrProducer;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDto)) {
            return false;
        }
        UserDto other = (UserDto) obj;
        return Objects.equals(username, other.username)
                && Objects.equals(password, other.password)
                && Objects.equals(enabled, other.enabled)
                && Objects.equals(apikey, other.apikey)
                && Objects.equals(email, other.email)
                && Objects.equals(artistOrProducer, other.artistOrProducer)
                && Objects.equals(authorities, other.authorities)
                && Objects.equals(image, other.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled, apikey, email, artistOrProducer, authorities, image);
    }
}
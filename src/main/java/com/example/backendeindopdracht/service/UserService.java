package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Image;
import com.example.backendeindopdracht.Models.Rating;
import com.example.backendeindopdracht.Repositories.ImageRepository;
import com.example.backendeindopdracht.dtos.UserDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import com.example.backendeindopdracht.Models.Authority;
import com.example.backendeindopdracht.Models.User;
import com.example.backendeindopdracht.Repositories.UserRepository;
import com.example.backendeindopdracht.exceptions.UsernameNotFoundException;
import com.example.backendeindopdracht.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private static  UserRepository userRepository;
    private static  ImageRepository imageRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }


    public static List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));

        return newUser.getUsername() ;
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) {
            throw new RecordNotFoundException();
        }
        User user = userRepository.findById(username).get();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        user.setArtistOrProducer(newUser.getArtistOrProducer());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
    public static void assignImageToUser(String id, String userName) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalImage.isPresent() && optionalUser.isPresent()) {
            Image image = optionalImage.get();
            User user = optionalUser.get();
            user.setImage(image);
            userRepository.save(user);

        } else {
            throw new RecordNotFoundException();
        }

    }

    public static UserDto fromUser(User user) {

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.artistOrProducer = user.isArtistOrProducer();
        dto.authorities = user.getAuthorities();
        dto.image = user.getImage();
        List<Integer> ratingids = new ArrayList<>();
        for(Rating rating  : user.getRating()){
            ratingids.add(rating.getId());
        }
        dto.setRatingIds(ratingids);

        return dto;
    }

    public static User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());
        user.setArtistOrProducer(userDto.getArtistOrProducer());
        user.setImage(userDto.getImage());


        return user;
    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("not found");
        }
        else {
            return optionalUser.get();
        }
    }
}

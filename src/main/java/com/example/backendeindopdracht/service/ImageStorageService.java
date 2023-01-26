package com.example.backendeindopdracht.service;


import java.io.IOException;
import java.util.stream.Stream;


import com.example.backendeindopdracht.Models.Image;


import com.example.backendeindopdracht.Repositories.ImageRepository;
import com.example.backendeindopdracht.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;




@Service
public class ImageStorageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;



    public ImageStorageService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(fileName, file.getBytes());

        return imageRepository.save(image);
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllImages() {
        return imageRepository.findAll().stream();
    }
}

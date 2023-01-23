package com.example.backendeindopdracht.Controllers;

import java.util.List;
import java.util.stream.Collectors;


import com.example.backendeindopdracht.Message.ResponseImage;
import com.example.backendeindopdracht.Message.ResponseMessageImage;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Models.Image;


import com.example.backendeindopdracht.service.ImageStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@Controller
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageStorageService storageService;

    public ImageController(ImageStorageService storageService) {this.storageService = storageService; }

    @PostMapping("/upload/image")
    public ResponseEntity<ResponseMessageImage> uploadImage(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            storageService.store(file);

            message = "Uploaded the image successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageImage(message));
        } catch (Exception e) {
            message = "Could not upload the image: " + file.getOriginalFilename() + "!" + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageImage(message));
        }
    }


    @GetMapping("/images")
    public ResponseEntity<List<ResponseImage>> getListImages() {
        List<ResponseImage> images = storageService.getAllImages().map(image1 -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(image1.getId())
                    .toUriString();

            return new ResponseImage(
                    image1.getName(),
                    fileDownloadUri,

                    image1.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Image image = storageService.getImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(image.getData());
    }
}

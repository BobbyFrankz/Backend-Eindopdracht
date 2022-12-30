package com.example.backendeindopdracht.Controllers;

import com.example.backendeindopdracht.dtos.ImageDto;
import com.example.backendeindopdracht.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {
    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/images")
    public Iterable<ImageDto> getAllImages() {

        var dtos = imageService.getAllImages();

        return dtos;
    }

    @GetMapping("/images/{id}")
    public ImageDto getOneImage(@PathVariable("id") Long id) {

        ImageDto imageDto = imageService.getOneImage(id);

        return imageDto;
    }

    @PostMapping("/images")
    public ImageDto createImage(@RequestBody ImageDto dto) {
        ImageDto imageDto = imageService.createImage(dto);
        return imageDto;
    }

    @DeleteMapping("/images/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping("/images/{id}")
    public ImageDto updateImage(@PathVariable("id") Long id, @RequestBody ImageDto imageDto) {
        imageService.updateImage(id, imageDto);
        return imageDto;
    }

}

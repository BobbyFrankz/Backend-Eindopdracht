package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Image;
import com.example.backendeindopdracht.Repositories.ImageRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageStorageServiceTest {

    @Mock
    ImageRepository imageRepository;

    @InjectMocks
    ImageStorageService imageStorageService;

    @Test
    public void getImageTest() {
        Image image = new Image();
        image.setId("image-id");
        image.setData(new byte[10]);

        when(imageRepository.findById("image-id")).thenReturn(Optional.of(image));

        Image returnedImage = imageStorageService.getImage("image-id");
        verify(imageRepository).findById("image-id");
        assertEquals("image-id", returnedImage.getId());
        assertArrayEquals(new byte[10], returnedImage.getData());
    }
    @Test
    public void getAllImagesTest() {
        Image image1 = new Image();
        image1.setId("image-id-1");
        image1.setData(new byte[10]);

        Image image2 = new Image();
        image2.setId("image-id-2");
        image2.setData(new byte[20]);

        List<Image> images = new ArrayList<>();
                images.add(image1);
                images.add(image2);

        when(imageRepository.findAll()).thenReturn(images);

        Stream<Image> returnedImages = imageStorageService.getAllImages();
        verify(imageRepository).findAll();

        assertEquals(2, returnedImages.count());
    }

}
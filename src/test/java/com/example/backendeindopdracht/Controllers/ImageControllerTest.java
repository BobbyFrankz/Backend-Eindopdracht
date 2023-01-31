package com.example.backendeindopdracht.Controllers;

import com.example.backendeindopdracht.Message.ResponseImage;
import com.example.backendeindopdracht.Message.ResponseMessageImage;
import com.example.backendeindopdracht.Models.Image;
import com.example.backendeindopdracht.service.ImageStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageControllerTest {

    @Test
    public void testUploadImage() throws Exception {
        // Create a mock file
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());

        // Create a mock storage service
        ImageStorageService storageService = mock(ImageStorageService.class);
        Image img = new Image("test.jpg",file.getBytes());
        when(storageService.store(file)).thenReturn(img);

        // Inject the mock storage service into the controller
        ImageController controller = new ImageController(storageService);

        // Call the uploadImage method
        ResponseEntity<ResponseMessageImage> response = controller.uploadImage(file);

        // Verify that the storage service's store method was called with the file
        verify(storageService).store(file);

        // Verify that the response has a status of OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the response body contains the correct message
        assertEquals("Uploaded the image successfully: test.jpg", response.getBody().getMessage());
    }

    @Test
    public void testGetListImages() throws Exception {
        // Declare constants for expected values
        final String IMAGE1_NAME = "image1.jpg";
        final String IMAGE2_NAME = "image2.jpg";
        final int IMAGE1_SIZE = 10;
        final int IMAGE2_SIZE = 20;


        // Create a mock storage service
        ImageStorageService storageService = mock(ImageStorageService.class);

        // Create a list of mock images
        List<Image> images = new ArrayList<>();
        images.add(new Image(IMAGE1_NAME, new byte[IMAGE1_SIZE]));
        images.add(new Image(IMAGE2_NAME, new byte[IMAGE2_SIZE]));

        // Set the mock storage service to return the list of images
        when(storageService.getAllImages()).thenReturn(images.stream());

        // Inject the mock storage service into the controller
        ImageController controller = new ImageController(storageService);

        // Create a mock request object
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        // Call the getListImages method
        ResponseEntity<List<ResponseImage>> response = controller.getListImages();

        // Verify that the storage service's getAllImages method was called
        verify(storageService).getAllImages();

        // Verify that the response has a status of OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the response body contains the correct number of images
        List<ResponseImage> responseImages = response.getBody();
        assertEquals(2, responseImages.size());

        // Verify that the first image in the response body has the correct properties
        ResponseImage firstImage = responseImages.get(0);
        assertEquals(IMAGE1_NAME, firstImage.getName());
        assertEquals(IMAGE1_SIZE, firstImage.getSize());

        // Verify that the second image in the response body has the correct properties
        ResponseImage secondImage = responseImages.get(1);
        assertEquals(IMAGE2_NAME, secondImage.getName());
        assertEquals(IMAGE2_SIZE, secondImage.getSize());
    }


}
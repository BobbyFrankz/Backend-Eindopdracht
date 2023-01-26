package com.example.backendeindopdracht.Controllers;


import com.example.backendeindopdracht.Message.ResponseMessage;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.service.FileStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

    @Mock
    private FileStorageService storageService;

    @Mock
    private FileDBRepository fileDBRepository;

    @InjectMocks
    private FileController fileController;

    @Test
    public void testUploadFileSuccess() throws IOException {
        // prepare mock file
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        doReturn(new FileDB()).when(storageService).store(file, "genre", 123, "artist");

        // call method
        ResponseEntity<ResponseMessage> response = fileController.uploadFile(file, "genre", 123, "artist");

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Uploaded the file successfully: test.txt",
                response.getBody().getMessage());
    }
    @Test
    public void testUploadFileFailure() throws IOException {
        // prepare mock file
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        doThrow(new RuntimeException()).when(storageService).store(file, "genre", 123, "artist");

        // call method
        ResponseEntity<ResponseMessage> response = fileController.uploadFile(file, "genre", 123, "artist");

        // assert
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Could not upload the file: test.txt!null", response.getBody().getMessage());
    }

}
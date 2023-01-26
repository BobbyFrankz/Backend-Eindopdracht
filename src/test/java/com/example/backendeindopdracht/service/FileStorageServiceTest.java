//package com.example.backendeindopdracht.service;
//
//import com.example.backendeindopdracht.Models.AudioInfo;
//import com.example.backendeindopdracht.Models.FileDB;
//import com.example.backendeindopdracht.Repositories.AudioInfoRepository;
//import com.example.backendeindopdracht.Repositories.FileDBRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class FileStorageServiceTest {
//    @Mock
//    private FileDBRepository fileDBRepository;
//    @Mock
//    private AudioInfoRepository audioInfoRepository;
//    @InjectMocks
//    private FileStorageService fileStorageService;
//
//    @Test
//    public void storeTest() throws IOException {
//        MultipartFile file = new MockMultipartFile("test.txt", "test.txt", "text/plain", "Hello World".getBytes());
//        String genre = "Pop";
//        int bpm = 120;
//        String artist = "Test Artist";
//        FileDB fileDB = new FileDB("test.txt", "text/plain", "Hello World".getBytes());
//        AudioInfo audioInfo = new AudioInfo(genre, bpm, artist, fileDB);
//        when(fileDBRepository.save(fileDB)).thenReturn(fileDB);
//        when(audioInfoRepository.save(audioInfo)).thenReturn(audioInfo);
//
//        FileDB result = fileStorageService.store(file, genre, bpm, artist);
//
//        assertEquals(fileDB, result);
//        verify(fileDBRepository).save(fileDB);
//        verify(audioInfoRepository).save(audioInfo);
//    }
//}

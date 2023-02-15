package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.AudioInfo;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Repositories.AudioInfoRepository;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileStorageServiceTest {
    @Mock
    private FileDBRepository fileDBRepository;
    @Mock
    private AudioInfoRepository audioInfoRepository;
    @InjectMocks
    private FileStorageService fileStorageService;


    @Test
    public void storeTest() throws IOException {
        // Create a real MultipartFile object
        byte[] data = "Hello World".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("test.txt", "test.txt", "text/plain", data);

        // Generate dynamic values for genre, bpm, artist
        String genre = "Pop";
        int bpm = 120;
        String artist = "Test Artist";

        when(fileDBRepository.save(any(FileDB.class))).thenReturn(new FileDB("test.txt", "text/plain", data));
        when(audioInfoRepository.save(any(AudioInfo.class))).thenReturn(new AudioInfo(genre, bpm, artist, new FileDB("test.txt", "text/plain", data)));

        FileDB result = fileStorageService.store(multipartFile, genre, bpm, artist);
        assertThat(result.getName()).isEqualTo("test.txt");
        assertThat(result.getType()).isEqualTo("text/plain");
        assertThat(result.getData()).isEqualTo(data);
        verify(fileDBRepository, atLeastOnce()).save(any(FileDB.class));
    }

   }

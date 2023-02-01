package com.example.backendeindopdracht.service;


import java.io.IOException;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.backendeindopdracht.Models.*;
import com.example.backendeindopdracht.Repositories.AudioInfoRepository;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.Repositories.RatingRepository;
import com.example.backendeindopdracht.dtos.AudioInfoDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;




@Service
public class FileStorageService {

    private final FileDBRepository fileDBRepository;
    private final AudioInfoRepository audioInfoRepository;
    private final RatingRepository ratingRepository;



    public FileStorageService(FileDBRepository fileDBRepository, AudioInfoRepository audioInfoRepository, RatingRepository ratingRepository) {
        this.fileDBRepository = fileDBRepository;
        this.audioInfoRepository = audioInfoRepository;
        this.ratingRepository = ratingRepository;
    }

    public FileDB store(MultipartFile file, String genre, int bpm, String artist) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        fileDBRepository.save(fileDB);
        AudioInfo audioInfo = new AudioInfo(genre, bpm, artist, fileDB);
        audioInfoRepository.save(audioInfo);
        fileDB.setAudioInfo(audioInfo);


        return fileDBRepository.save(fileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


    public static AudioInfoDto toDto(AudioInfo audioInfo) {
        return new AudioInfoDto(audioInfo.getGenre(), audioInfo.getBpm(), audioInfo.getArtist());

    }

    public void assignRatingToFile(String fileName, String ratingId) {
        Optional<FileDB> fileDBOptional = Optional.ofNullable(fileDBRepository.findByName(fileName));
        Optional<Rating> ratingOptional = ratingRepository.findById(ratingId);

        if (fileDBOptional.isPresent() && ratingOptional.isPresent()) {
            FileDB fileDB = fileDBOptional.get();
            Rating rating = ratingOptional.get();
            fileDB.getRatings().add(rating);
            fileDBRepository.save(fileDB);
        } else {
            throw new RecordNotFoundException("File or rating not found");
        }


    }
}

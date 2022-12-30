package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.AudioFile;
import com.example.backendeindopdracht.Repositories.AudioFileRepository;
import com.example.backendeindopdracht.dtos.AudioFileDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AudioFileService {
    private final AudioFileRepository auRepos;

    public AudioFileService(AudioFileRepository auRepos) {
        this.auRepos = auRepos;
    }

    //logica van get mapping all audio files
    public Iterable <AudioFileDto> getAllAudioFiles() {
        ArrayList<AudioFileDto> allDtoAudioFiles = new ArrayList<AudioFileDto>();
        Iterable <AudioFile> getAllAudioFiles = auRepos.findAll();
        for (AudioFile a: getAllAudioFiles) {
            AudioFileDto AudioFileDto = transferToDto(a);
            allDtoAudioFiles.add(AudioFileDto);
        }

        return allDtoAudioFiles;

    }


    //logica van get mapping voor 1 Audio file
    public AudioFileDto getOneAudioFile(Long id) {
        Optional<AudioFile> foundAudioFile = auRepos.findById(id);

        if (foundAudioFile.isPresent()) {
            AudioFile audiofile1 = foundAudioFile.get();
            return transferToDto(audiofile1);
        } else {
            throw new RecordNotFoundException("Audio File not found");
        }
    }

    // logica van postmapping
    public AudioFileDto createAudioFile(AudioFileDto audioFileDto) {
        AudioFile audioFile = transferFromDto(audioFileDto);
        AudioFile savedAudioFile = auRepos.save(audioFile);

        return audioFileDto;

    }



    //logica van delete mapping
    public String deleteAudioFile(Long id) {
        Optional<AudioFile> deletedAudioFile = auRepos.findById(id);
        if (deletedAudioFile.isPresent()) {
            AudioFile audiofile1 = deletedAudioFile.get();
            auRepos.delete(audiofile1);
            return "Audio-File Removed";
        } else {
            throw new RecordNotFoundException("Audio-File(ID) not found!");
        }
    }

    //logica van put mapping
    public AudioFileDto updateAudioFile(Long id, AudioFileDto audioFileDto) {
        Optional<AudioFile> updatedAudioFile = auRepos.findById(id);
        if (updatedAudioFile.isPresent()) {
            // haal de audiofile uit de database
            AudioFile updateAudioFile = updatedAudioFile.get();
            // pas de Audio File aan met nieuwe waarde
            updateAudioFile.setTitle(audioFileDto.getTitle());
            updateAudioFile.setArtist(audioFileDto.getArtist());
            updateAudioFile.setTypeOfFile(audioFileDto.getTypeOfFile());
            updateAudioFile.setDuration(audioFileDto.getDuration());
            updateAudioFile.setFilePath(audioFileDto.getFilePath());


            // sla de television op
            auRepos.save(updateAudioFile);
            //return
            return transferToDto(updateAudioFile);
        } else {
            throw new RecordNotFoundException("Audio-File(id) not found");
        }

    }

    public AudioFileDto transferToDto (AudioFile audioFile) {
        AudioFileDto audto = new AudioFileDto();

        audto.setTitle(audioFile.getTitle());
        audto.setArtist(audioFile.getArtist());
        audto.setTypeOfFile(audioFile.getTypeOfFile());
        audto.setDuration(audioFile.getDuration());
        audto.setFilePath(audioFile.getFilePath());


        return audto;
    }
    public AudioFile transferFromDto (AudioFileDto audioFileDto) {

        AudioFile audioFile = new AudioFile();

        audioFile.setTitle(audioFileDto.getTitle());
        audioFile.setArtist(audioFileDto.getArtist());
        audioFile.setTypeOfFile(audioFileDto.getTypeOfFile());
        audioFile.setDuration(audioFileDto.getDuration());
        audioFile.setFilePath(audioFileDto.getFilePath());

        return audioFile;
    }
}
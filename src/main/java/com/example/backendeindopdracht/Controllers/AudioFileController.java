package com.example.backendeindopdracht.Controllers;

import com.example.backendeindopdracht.dtos.AudioFileDto;
import com.example.backendeindopdracht.service.AudioFileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AudioFileController {
    private final AudioFileService audioFileService;


    public AudioFileController(AudioFileService audioFileService) {
        this.audioFileService = audioFileService;
    }


    @GetMapping("/audiofiles")
    public Iterable<AudioFileDto> getAllAudioFiles() {

        var dtos = audioFileService.getAllAudioFiles();

        return dtos;
    }

    @GetMapping("/audiofiles/{id}")
    public AudioFileDto getOneAudioFile(@PathVariable("id") Long id) {

        AudioFileDto audioFileDto = audioFileService.getOneAudioFile(id);

        return audioFileDto;
    }

    @PostMapping("/audiofiles")
    public AudioFileDto createAudioFile(@RequestBody AudioFileDto dto) {
        AudioFileDto audioFileDto = audioFileService.createAudioFile(dto);
        return audioFileDto;
    }

    @DeleteMapping("/audiofiles/{id}")
    public void deleteAudioFile(@PathVariable("id") Long id) {
        audioFileService.deleteAudioFile(id);
    }

    @PutMapping("/audiofiles/{id}")
    public AudioFileDto updateAudioFile(@PathVariable("id") Long id, @RequestBody AudioFileDto audioFileDto) {
        audioFileService.updateAudioFile(id, audioFileDto);
        return audioFileDto;
    }

}

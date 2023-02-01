package com.example.backendeindopdracht.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.backendeindopdracht.Message.ResponseFile;
import com.example.backendeindopdracht.Message.ResponseMessage;
import com.example.backendeindopdracht.Models.FileDB;
import com.example.backendeindopdracht.Repositories.FileDBRepository;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import com.example.backendeindopdracht.service.FileStorageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@Controller
@CrossOrigin(origins = "*")
public class FileController {

    private final FileStorageService storageService;
    private final FileDBRepository fileDBRepository;


    public FileController(FileStorageService storageService, FileDBRepository fileDBRepository) {
        this.storageService = storageService;
        this.fileDBRepository = fileDBRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String genre, @RequestParam int bpm, @RequestParam String artist) {
        String message;
        try {
            storageService.store(file, genre, bpm, artist);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
           message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
   }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,

                    dbFile.getType(),
                    dbFile.getData().length,
                    FileStorageService.toDto(dbFile.getAudioInfo()))

                    ;
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @PutMapping("/{fileId}/ratings/{ratingId}")
    public ResponseEntity<Void> assignRatingToFile(@PathVariable String fileId, @PathVariable String ratingId) {
        storageService.assignRatingToFile(fileId, ratingId);
        return ResponseEntity.noContent().build();
    }
}

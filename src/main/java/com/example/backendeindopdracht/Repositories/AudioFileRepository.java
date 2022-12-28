package com.example.backendeindopdracht.Repositories;

import com.example.backendeindopdracht.Models.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioFileRepository extends JpaRepository <AudioFile, Long> {
}

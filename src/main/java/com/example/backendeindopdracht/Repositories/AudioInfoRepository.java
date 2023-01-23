package com.example.backendeindopdracht.Repositories;


import com.example.backendeindopdracht.Models.AudioInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioInfoRepository extends JpaRepository<AudioInfo, Long> {

}

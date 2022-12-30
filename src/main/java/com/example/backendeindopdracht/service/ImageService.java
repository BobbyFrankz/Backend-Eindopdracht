package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Image;
import com.example.backendeindopdracht.Repositories.ImageRepository;
import com.example.backendeindopdracht.dtos.ImageDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imRepos;

    public ImageService(ImageRepository imRepos) {
        this.imRepos = imRepos;
    }

    //logica van get mapping all Images
    public Iterable <ImageDto> getAllImages() {
        ArrayList<ImageDto> allDtoImages = new ArrayList<ImageDto>();
        Iterable <Image> getAllImages = imRepos.findAll();
        for (Image i: getAllImages) {
            ImageDto ImageDto = transferToDto(i);
            allDtoImages.add(ImageDto);
        }

        return allDtoImages;

    }


    //logica van get mapping voor 1 image
    public ImageDto getOneImage(Long id) {
        Optional<Image> foundImage = imRepos.findById(id);

        if (foundImage.isPresent()) {
            Image image1 = foundImage.get();
            return transferToDto(image1);
        } else {
            throw new RecordNotFoundException("Image not found");
        }
    }

    // logica van postmapping voor een nieuwe image
    public ImageDto createImage(ImageDto imageDto) {
        Image image = transferFromDto(imageDto);
        Image savedImage = imRepos.save(image);

        return imageDto;

    }



    //logica van delete mapping
    public String deleteImage(Long id) {
        Optional<Image> deletedImage = imRepos.findById(id);
        if (deletedImage.isPresent()) {
            Image image1 = deletedImage.get();
            imRepos.delete(image1);
            return "Image Removed";
        } else {
            throw new RecordNotFoundException("Image(ID) not found!");
        }
    }

    //logica van put mapping
    public ImageDto updateImage(Long id, ImageDto imageDto) {
        Optional<Image> updatedImage = imRepos.findById(id);
        if (updatedImage.isPresent()) {
            // haal de audiofile uit de database
            Image updateImage = updatedImage.get();
            // pas de Audio File aan met nieuwe waarde
            updateImage.setName(imageDto.getName());
            updateImage.setData(imageDto.getData());



            // sla de Images op
            imRepos.save(updateImage);
            //return
            return transferToDto(updateImage);
        } else {
            throw new RecordNotFoundException("Image (id) not found");
        }

    }

    public ImageDto transferToDto (Image image) {
        ImageDto imdto = new ImageDto();

        imdto.setName(image.getName());
        imdto.setData(image.getData());



        return imdto;
    }
    public Image transferFromDto (ImageDto imageDto) {

        Image image = new Image();

        image.setName(imageDto.getName());
        image.setData(imageDto.getData());


        return image;
    }
}


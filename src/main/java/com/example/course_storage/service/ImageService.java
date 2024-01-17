package com.example.course_storage.service;

import com.example.course_storage.domain.ImageDto;
import com.example.course_storage.model.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void save(ImageDto imageDto);

   ImageEntity uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String imageName);

    public ImageEntity getImageEntityByName(String imageName);


}

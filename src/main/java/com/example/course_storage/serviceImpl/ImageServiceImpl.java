package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.ImageDto;
import com.example.course_storage.exceptions.ExceptionNumber1;
import com.example.course_storage.mapper.ImageMapper;
import com.example.course_storage.model.ImageEntity;
import com.example.course_storage.repository.ImageRepository;
import com.example.course_storage.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor


@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public void save(ImageDto imageDto) {
        if (imageDto == null) {
            throw new RuntimeException();
        }

        ImageEntity imageEnt = imageMapper.toImageEnt(imageDto);
        imageRepository.save(imageEnt);


    }

    @Override
    public ImageEntity uploadImage(MultipartFile file) throws IOException {

        ImageEntity build = ImageEntity.builder()
                .name(file.getOriginalFilename())
                .bytes(file.getBytes())
                //  .goodEntities(E)
                .build();



        ImageEntity imageEntityByName = getImageEntityByName(file.getOriginalFilename());
        //ImageDto imageDto = imageMapper.toImageDto(imageEntityByName);

        //return "file uploaded successfully : " + file.getOriginalFilename();
        ImageEntity saveImage = imageRepository.save(build);
        return saveImage;


    }

    @Override
    public byte[] downloadImage(String imageName) {
        Optional<ImageEntity> byName = imageRepository.findByName(imageName);
        ImageEntity imageEntity = byName.get();
        byte[] bytes = imageEntity.getBytes();
        return bytes;
    }

    @Override
    public ImageEntity getImageEntityByName(String imageName) {
        Optional<ImageEntity> byName = imageRepository.findByName(imageName);
        if (byName.isEmpty()) {
                        return new ImageEntity();
        } else {
            throw new ExceptionNumber1("Exception");
            //return null;
        }
    }
}

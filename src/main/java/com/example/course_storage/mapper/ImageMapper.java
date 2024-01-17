package com.example.course_storage.mapper;

import com.example.course_storage.domain.ImageDto;
import com.example.course_storage.model.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring" )
public interface ImageMapper {
    @Mapping(target = "id", source = "id")
    ImageEntity toImageEnt(ImageDto imageDto);

    ImageDto toImageDto(ImageEntity imageEntity);

}


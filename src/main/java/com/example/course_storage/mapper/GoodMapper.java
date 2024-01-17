package com.example.course_storage.mapper;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.ImageDto;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.GoodEntity;
import com.example.course_storage.model.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


    @Mapper(componentModel = "Spring" )
    public interface GoodMapper {

     @Mapping(target = "id", source = "id")
           // @Mapping(target = "name", source = "name")*/
        GoodDto toGoodDto(GoodEntity goodEntity);
        //@Mapping(target = "uuid", source = "uuid")
        FirmDto toFirmDto(FirmEntity firmEntity);
       // @Mapping(target = "uuid", source = "uuid")
        FirmEntity toFirmEntity(FirmDto firmDto);
        /*@Mapping(target = "id", source = "id")
        @Mapping(target = "name", source = "name")*/

      /*@Mapping(target = "id", source = "id")
      @Mapping(target = "name", source = "name")*/
        ImageEntity toImageEnt(ImageDto imageDto);

        ImageDto toImageDto(ImageEntity imageEntity);
        GoodEntity toGoodEntity(GoodDto goodDto);

        List<GoodDto> toListGoodDto(List<GoodEntity> goodEntities);

    }


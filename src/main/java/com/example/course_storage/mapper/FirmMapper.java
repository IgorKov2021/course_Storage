package com.example.course_storage.mapper;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.GoodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "Spring" )
public interface FirmMapper {

    @Mapping(target = "uuid", source = "uuid")
    FirmDto toFirmDto(FirmEntity firmEntity);
    FirmEntity toFirmEntity(FirmDto firmDto);
  /*@Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")*/
  List<FirmDto> toListFirmDto(List<FirmEntity> firmEntities);
    List<FirmEntity> toListEntity(List<FirmDto> firmEntities);

}


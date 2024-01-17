package com.example.course_storage.mapper;

import com.example.course_storage.domain.PersonDto;
import com.example.course_storage.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PersonMapper {
  @Mapping(target = "id", source = "id")
 // @Mapping(target = "name", source = "name")
   // @Mapping(target = "password", source = "password")
    PersonEntity toPersonEntity(PersonDto personDto);

    PersonDto toPersonDto(PersonEntity personEntity);
}

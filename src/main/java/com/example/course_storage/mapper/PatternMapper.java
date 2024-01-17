package com.example.course_storage.mapper;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.PatternDto;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.PatternEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "Spring")
public interface PatternMapper {

 @Mapping(target = "id", source = "id")
    PatternDto toPatternDto(PatternEntity patternEntity);

    PatternEntity toPatternEntity(PatternDto patternDto);

    List<PatternDto> toListPatternDto(List<PatternEntity> patternEntities);
    List<PatternEntity> toListPatternEntities(List<PatternDto> patternEntities);


}


package com.example.course_storage.mapper;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.MoleculeDto;
import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.MoleculesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "Spring")
public interface MolecularMapper {

    @Mapping(target = "id", source = "id")
    MoleculeDto toMolDto(MoleculesEntity moleculesEntity);
    //@Mapping(target = "name", source = "name")
    MoleculesEntity toMolEntity(MoleculeDto moleculeDto);

    List<MoleculeDto> toMolDtos(List<MoleculesEntity> moleculesEntities);

    List<MoleculesEntity> toMolEntities(List<MoleculeDto> moleculesDtos);

}


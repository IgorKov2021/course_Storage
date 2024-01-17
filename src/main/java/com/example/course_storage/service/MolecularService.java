package com.example.course_storage.service;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.MoleculeDto;
import com.example.course_storage.model.FirmEntity;

import java.util.List;

public interface MolecularService {

    void save(MoleculeDto molDto);

    List<MoleculeDto> getAll();


}

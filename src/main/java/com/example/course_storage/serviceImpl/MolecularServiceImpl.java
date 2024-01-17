package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.MoleculeDto;
import com.example.course_storage.mapper.MolecularMapper;
import com.example.course_storage.model.MoleculesEntity;
import com.example.course_storage.repository.MolecularRepository;
import com.example.course_storage.service.MolecularService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class MolecularServiceImpl implements MolecularService {

    private final MolecularMapper molMapper;
    private final MolecularRepository molecularRepository;

    @Override
    public void save(MoleculeDto molDto) {

        MoleculesEntity molEntity = molMapper.toMolEntity(molDto);
        molecularRepository.save(molEntity);
    }


    @Override
    public List<MoleculeDto> getAll() {

        List<MoleculesEntity> all = molecularRepository.findAll();
        return molMapper.toMolDtos(all);

    }
}

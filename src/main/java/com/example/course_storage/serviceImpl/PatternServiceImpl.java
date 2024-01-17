package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.MoleculeDto;
import com.example.course_storage.domain.PatternDto;
import com.example.course_storage.exceptions.ExceptionNumber2;
import com.example.course_storage.exceptions.ExceptionZeroProduct;
import com.example.course_storage.mapper.GoodMapper;
import com.example.course_storage.mapper.PatternMapper;
import com.example.course_storage.model.GoodEntity;
import com.example.course_storage.model.MoleculesEntity;
import com.example.course_storage.model.PatternEntity;
import com.example.course_storage.repository.PatternRepository;
import com.example.course_storage.service.MolecularService;
import com.example.course_storage.service.PatternService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor

@Service
public class PatternServiceImpl implements PatternService {

    private final PatternRepository patternRepository;
    private final PatternMapper patternMapper;
    private final MolecularService molecularService;
    private final GoodMapper goodMapper;

    @Override
    public List<PatternDto> getAllPatterns() {

        List<PatternEntity> allPatterns = patternRepository.findAll();
        return patternMapper.toListPatternDto(allPatterns);
    }

    @Override
    public void savePattern(PatternDto patternDto) {

        MoleculesEntity molecule = patternDto.getMolecule();
        Long id1 = molecule.getId();

        List<PatternEntity> all = patternRepository.findAll();
        if (all != null) {
            boolean noSuchId = all.stream().allMatch(patternEntity -> patternEntity.getMolecule().getId() != id1);
            if (noSuchId) {
                PatternEntity patternEntity = patternMapper.toPatternEntity(patternDto);
                patternRepository.save(patternEntity);
            } else {
                throw new ExceptionNumber2();
            }
        } else {
            PatternEntity patternEntity = patternMapper.toPatternEntity(patternDto);
            patternRepository.save(patternEntity);
        }


    }

    @Override
    public PatternDto getById(Long id) {

        if (id == null) {
            throw new RuntimeException();

        }

        PatternEntity byId = patternRepository.getById(id);
        return patternMapper.toPatternDto(byId);
    }

    @Transactional
    @Override
    public void UpdatePatternProducts(Long id, PatternDto patternDto) {
        List<GoodEntity> goodEntities = patternDto.getGoodEntities();
        PatternEntity byId = patternRepository.getById(id);
        byId.setGoodEntities(goodEntities);


    }

    @Override
    public void deletePattern(Long id) {

        patternRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void writeOffGoods(Long id) {
        PatternEntity byId = patternRepository.getById(id);
        List<GoodEntity> goodEntities = byId.getGoodEntities();
        if (goodEntities != null) {
            boolean b = goodEntities.stream().allMatch(goodEntity -> goodEntity.getQuantity() > 0);
            if (b) {
                List<GoodEntity> collect = goodEntities.stream().peek(goodEntity -> goodEntity.setQuantity(goodEntity.getQuantity() - 1)).collect(Collectors.toList());
                byId.setGoodEntities(collect);
            } else {
                List<GoodEntity> collect = goodEntities.stream().filter(goodEntity -> goodEntity.getQuantity() <= 0).collect(Collectors.toList());
                List<GoodDto> listGoodDto = goodMapper.toListGoodDto(collect);
                throw new ExceptionZeroProduct("The quantity of one product is 0, please check", listGoodDto );
            }


        }
        //byId.setGoodEntities( goodEntities);

    }
}

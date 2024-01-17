package com.example.course_storage.service;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.PatternDto;
import com.example.course_storage.domain.SearchGoodDto;

import java.util.List;
import java.util.UUID;

public interface PatternService {

    List<PatternDto> getAllPatterns();

    void savePattern(PatternDto patternDto);

    PatternDto getById(Long id);

    void UpdatePatternProducts(Long id, PatternDto patternDto);

    void deletePattern(Long id);

    void writeOffGoods(Long id);


}

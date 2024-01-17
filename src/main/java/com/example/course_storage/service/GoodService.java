package com.example.course_storage.service;

import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.SearchGoodDto;
import com.example.course_storage.model.GoodEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GoodService {
    void save(GoodDto goodDto);
    //void save(GoodDto goodDto, String name);

    List<GoodDto> getAll();

    GoodDto getById(UUID id);

    void update(UUID id, GoodDto dto);

    List<GoodDto> search (SearchGoodDto searchGoodDto);
    List<GoodDto> searchSpecification (SearchGoodDto searchGoodDto);

    void delete(UUID id);
    List<GoodDto> findByKeyword(String keyword);

    List<GoodDto> findAllBySort(String param, String prop);
}

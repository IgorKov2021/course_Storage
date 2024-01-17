package com.example.course_storage.service;

import com.example.course_storage.domain.FirmDto;
import com.example.course_storage.domain.GoodDto;
import com.example.course_storage.domain.SearchGoodDto;
import com.example.course_storage.model.FirmEntity;

import java.util.List;
import java.util.UUID;

public interface FirmService {
    void save(FirmDto firmDto);

    List<FirmDto> getAll();

    FirmEntity searchByCompanyName(String name);

}

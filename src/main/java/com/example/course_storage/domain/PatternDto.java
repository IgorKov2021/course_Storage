package com.example.course_storage.domain;

import com.example.course_storage.model.GoodEntity;
import com.example.course_storage.model.MoleculesEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatternDto {

    private Long id;

    private String nameOfPattern;

    private MoleculesEntity molecule;

    private List<GoodEntity> goodEntities;



}


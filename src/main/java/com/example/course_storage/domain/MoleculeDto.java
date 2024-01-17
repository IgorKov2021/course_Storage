package com.example.course_storage.domain;

import com.example.course_storage.model.PatternEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MoleculeDto {


    private Long id;
    private String name;
    private String trivialName;
    private PatternEntity patternEntity;
}


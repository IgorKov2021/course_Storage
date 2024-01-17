package com.example.course_storage.repository;

import com.example.course_storage.model.PatternEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository extends JpaRepository<PatternEntity, Long> {

PatternEntity getById(Long id);

}

package com.example.course_storage.repository;

import com.example.course_storage.model.MoleculesEntity;
import com.example.course_storage.model.PatternEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MolecularRepository extends JpaRepository<MoleculesEntity, Long> {



}

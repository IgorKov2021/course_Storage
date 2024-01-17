package com.example.course_storage.repository;

import com.example.course_storage.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

    Optional<ImageEntity> findByName(String s);



}

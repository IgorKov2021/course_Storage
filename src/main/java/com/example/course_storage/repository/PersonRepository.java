package com.example.course_storage.repository;

import com.example.course_storage.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {


    Optional<PersonEntity> findByName(String userName);
}

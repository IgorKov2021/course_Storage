package com.example.course_storage.repository;

import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FirmRepository extends JpaRepository<FirmEntity, UUID> {

  FirmEntity findByCompanyName(String name);

}

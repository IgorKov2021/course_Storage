package com.example.course_storage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "firms")
public class FirmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String companyName;

    private String country;

    @OneToMany(mappedBy = "company" )
    private List<GoodEntity> entityList;
   /*@OneToMany(mappedBy = "company")
    private <> goodEntity;*/


}

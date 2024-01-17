package com.example.course_storage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.access.method.P;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "molecules")
public class MoleculesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String name;
    private String trivialName;

    @OneToOne(mappedBy = "molecule")
    @JsonIgnore
    @ToString.Exclude
    private PatternEntity patternEntity;


}

package com.example.course_storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.parser.Part;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patterns")
public class PatternEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfPattern;
    @OneToOne
    @JoinColumn(name = "molecule_id")
    private MoleculesEntity molecule;

    @ManyToMany //cascade = {CascadeType.ALL}
    @JsonIgnore
    @JoinTable(
            name = "patterns_goods",
            joinColumns = {@JoinColumn(name = "pattern_id")},
            inverseJoinColumns = {@JoinColumn(name = "good_id")}
    )
    private List<GoodEntity> goodEntities;

}

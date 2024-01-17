package com.example.course_storage.domain;

import com.example.course_storage.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GoodDto {

    private UUID id;

    @NotBlank
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfReceiving;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfExpire;

    private String serialNumber;

    private String internalCode;

    private int quantity;

    private String description;

    private boolean isCertificatePresent;


    @Column(name = "create_date")
    private Date createDate;


    @Column(name = "modify_date")
    private Date modifyDate;

    private ImageEntity images;


    private List<PatternEntity> patternEntities;


    private FirmEntity company;


    private Price price;



   // private UUID modifyBy;
}



package com.example.course_storage.domain;

import com.example.course_storage.model.FirmEntity;
import com.example.course_storage.model.ImageEntity;
import com.example.course_storage.model.PatternEntity;
import com.example.course_storage.model.Price;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SearchGoodDto {
    private String name;

    private String serialNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfReceiving;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfExpire;

    private String internalCode;

    private String description;


}

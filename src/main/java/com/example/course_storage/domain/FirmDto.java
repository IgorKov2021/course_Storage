package com.example.course_storage.domain;

import com.example.course_storage.model.GoodEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FirmDto {
    private UUID uuid;

    @NotBlank
    private String companyName;

    private String country;

    //private GoodEntity goodEntity;

}

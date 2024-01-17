package com.example.course_storage.domain;

import com.example.course_storage.model.GoodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImageDto {
    private UUID id;

    private String name;

    private byte[] bytes;

    //List<GoodEntity> goodEntities;

}

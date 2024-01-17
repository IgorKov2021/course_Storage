package com.example.course_storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private UUID id;

    private String name;

    private String surname;

    private String email;

    private String login;

    private String password;


}

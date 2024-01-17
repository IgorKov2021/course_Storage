package com.example.course_storage.service;

import com.example.course_storage.domain.PersonDto;

public interface PersonService {
    String save(PersonDto personDto);

    Boolean checkLogin( PersonDto personDto);
    Boolean checkPassword( PersonDto personDto);
}

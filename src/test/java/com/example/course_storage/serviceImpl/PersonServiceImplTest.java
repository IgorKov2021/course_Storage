package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.PersonDto;
import com.example.course_storage.mapper.PersonMapper;
import com.example.course_storage.mapper.PersonMapperImpl;
import com.example.course_storage.model.PersonEntity;
import com.example.course_storage.repository.PersonRepository;
import com.example.course_storage.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PersonServiceImplTest {
    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private PersonRepository personRepository = Mockito.mock(PersonRepository.class);

    private PersonMapper personMapper = Mockito.mock(PersonMapper.class);
    PersonService personService = new PersonServiceImpl(passwordEncoder, personRepository, personMapper);


    @Test
    void checkLogin() {

        //give
        PersonDto personDto = new PersonDto();
        personDto.setLogin("User");

        PersonEntity p1 = new PersonEntity();
        PersonEntity p2 = new PersonEntity();
        PersonEntity p3 = new PersonEntity();

        p1.setLogin("User");
        p2.setLogin("User1");
        p3.setLogin("User2");

        List<PersonEntity> persons = List.of(p1,p2,p3);

        Mockito.when(personRepository.findAll()).thenReturn(persons);

        //when

        Boolean aBoolean = personService.checkLogin(personDto);

        //that

        Assertions.assertFalse(aBoolean);
    }

    @Test
    void checkPasswordTrue() {
        //give
        PersonDto personDto = new PersonDto();
        personDto.setPassword("123456789");

        //when
        Boolean aBoolean = personService.checkPassword(personDto);

        //then
        Assertions.assertTrue(aBoolean);


    }

    @Test
    void checkPasswordFalse() {
        //give
        PersonDto personDto = new PersonDto();
        personDto.setPassword("1234");

        //when
        Boolean aBoolean = personService.checkPassword(personDto);

        //then
        Assertions.assertFalse(aBoolean);


    }

    @Test
    void save() {
        //give
        PersonDto personDto = new PersonDto();
        personDto.setLogin("User4");
        personDto.setPassword("user4");

        PersonEntity entity = new PersonEntity();
        entity.setPassword(personDto.getPassword());
        entity.setLogin(personDto.getLogin());


       Mockito.when(personMapper.toPersonEntity(personDto)).thenReturn(entity);
       Mockito.when(passwordEncoder.encode(entity.getPassword())).thenReturn(personDto.getPassword());
       Mockito.when(personRepository.save(entity)).thenReturn(entity);

        //When
        String save = personService.save(personDto);
        PersonEntity save1 = personRepository.save(entity);

        //Then

        Assertions.assertEquals("User successfully created. Please login!", save);
        Assertions.assertEquals("User4", save1.getLogin());
    }

    @Test
    void saveSavePasswordNotEnoughLength() {
        //give
        PersonDto personDto = new PersonDto();
        personDto.setLogin("User4");
        personDto.setPassword("1234");

        PersonEntity entity = new PersonEntity();
        entity.setPassword(personDto.getPassword());
        entity.setLogin(personDto.getLogin());


        Mockito.when(personMapper.toPersonEntity(personDto)).thenReturn(entity);
        Mockito.when(passwordEncoder.encode(entity.getPassword())).thenReturn(personDto.getPassword());
        Mockito.when(personRepository.save(entity)).thenReturn(entity);

        //When
        String save = personService.save(personDto);
      //PersonEntity save1 = personRepository.save(entity);

        //Then

        Mockito.verify(personRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertEquals("Password less than 5 characters!", save);
    }

   /* @Test
    void saveLoginWrong() {
        //give
        PersonDto personDto = new PersonDto();
        personDto.setLogin("User4");
        personDto.setPassword("1234");

        ArgumentCaptor<PersonEntity> argumentCaptor = ArgumentCaptor.forClass(PersonEntity.class);
        PersonEntity save1 = Mockito.verify(personRepository).save(argumentCaptor.capture());
        PersonEntity value = argumentCaptor.getValue();



        //When
        String save = personService.save(personDto);
        //PersonEntity save1 = personRepository.save(entity);

        //Then

        Mockito.verify(personRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertEquals("Password less than 5 characters!", save);
    }*/
}
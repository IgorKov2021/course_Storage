package com.example.course_storage.serviceImpl;

import com.example.course_storage.domain.PersonDto;
import com.example.course_storage.mapper.PersonMapper;
import com.example.course_storage.model.PersonEntity;
import com.example.course_storage.repository.PersonRepository;
import com.example.course_storage.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class PersonServiceImpl implements PersonService {

    private final PasswordEncoder passwordEncoder;

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public String save(PersonDto personDto) {
        Boolean checkLogin = checkLogin(personDto);
        Boolean checkPassword = checkPassword(personDto);

        if (checkLogin && checkPassword) {
            PersonEntity personEntity = personMapper.toPersonEntity(personDto);
            personEntity.setPassword(passwordEncoder.encode(personEntity.getPassword()));
            personRepository.save(personEntity);
            return "User successfully created. Please login!";
        } else if (!checkLogin && checkPassword) {
            return "Login is already used!";
        } else if (!checkPassword && checkLogin) {
            return "Password less than 5 characters!";
        } else {
            return "Login is already used and password less than 5 characters!";
        }

    }

    @Override
    public Boolean checkLogin(PersonDto personDto) {
        String login = personDto.getLogin();
        List<PersonEntity> all = personRepository.findAll();
        if (all != null) {

            List<String> logins = all.stream()
                    .map(personEntity -> personEntity.getLogin())
                    .collect(Collectors.toList());

            boolean contains = !logins.contains(login);
            return contains;
        } else {
            return true;
        }
    }

    @Override
    public Boolean checkPassword(PersonDto personDto) {
        int length = personDto.getPassword().length();

        if (length < 5) {
            return false;
        } else {
            return true;
        }
    }
}

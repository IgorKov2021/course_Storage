package com.example.course_storage.service;

import com.example.course_storage.model.PersonEntity;
import com.example.course_storage.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class DBUserDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<PersonEntity> byName = personRepository.findByName(username);
        if(byName.isEmpty()) {
            throw new RuntimeException();
        }
            PersonEntity personEntity = byName.get();

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
               // return new ArrayList<>();

                List<GrantedAuthority> authorities = personEntity.getPersonsRoles().stream()
                        .map((role) -> new SimpleGrantedAuthority(role.getRole().toString()))
                        .collect(Collectors.toList());
                return authorities;


            }

            @Override
            public String getPassword() {
                return personEntity.getPassword();
            }

            @Override
            public String getUsername() {
                return personEntity.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}

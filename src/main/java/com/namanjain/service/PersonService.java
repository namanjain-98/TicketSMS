package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.namanjain.entity.Person;


public interface PersonService extends UserDetailsService {
	
    public List < Person > getPersons();

    public void savePerson(Person thePerson);

    public Optional<Person> getPerson(int theId);

    public void deletePerson(int theId);

    public Person findByUsername(String username);

}

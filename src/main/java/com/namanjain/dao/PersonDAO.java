package com.namanjain.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.Person;


public interface PersonDAO extends CrudRepository<Person,Integer> {
	
	public Person findById(int theId);
	
    public List < Person > findAll();
    
    public void deleteById(int theId);

    public Person findByUsername(String username);

}
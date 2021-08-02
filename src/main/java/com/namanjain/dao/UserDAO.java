package com.namanjain.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.*;

public interface UserDAO extends CrudRepository<User,Integer> {
	
	public User findById(int theId);
	
    public List<User> findAll();


    public void deleteById(int theId);

	public User findByUsername(String email);
}
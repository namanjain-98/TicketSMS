package com.namanjain.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.*;

public interface EmployeeDAO extends CrudRepository<Employee,Integer> {

	public Employee findById(int theId);
	
    public List<Employee> findAll();

    public void deleteById(int theId);
	
}
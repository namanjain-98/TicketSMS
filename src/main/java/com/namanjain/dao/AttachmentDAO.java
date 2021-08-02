package com.namanjain.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.*;

public interface AttachmentDAO extends CrudRepository<Attachment,Integer> {
	
	public Attachment findById(int theId);
	
    public List<Attachment> findAll();



    public void deleteById(int theId);

}
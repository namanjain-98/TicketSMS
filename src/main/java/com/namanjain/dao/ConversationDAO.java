package com.namanjain.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.*;

public interface ConversationDAO extends CrudRepository<Conversation,Integer> {
	
	public Conversation findById(int theId);
	
    public List<Conversation> findAll();
   
    public List < Conversation > getConversationsByCreatedBy(String createdBy);
    
    public void deleteById(int theId);
}
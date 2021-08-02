package com.namanjain.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namanjain.entity.*;

public interface TicketDAO extends CrudRepository<Ticket,Integer> {

	
	public Ticket findById(int theId);
	
    public List<Ticket> findAll();

    public List<Conversation> findAllConversationsById(int theId);

    public void deleteById(int theId);

	public Ticket findByCode(String theCode);

	public List<Ticket> findAllByTicketStatus(String theStatus);
}
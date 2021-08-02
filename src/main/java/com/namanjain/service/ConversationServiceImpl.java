package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namanjain.dao.*;
import com.namanjain.entity.Conversation;



@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationDAO conversationDAO;

    @Transactional
    public List < Conversation > getConversations() {
        return (List<Conversation>) conversationDAO.findAll();
    }

    @Transactional
    public void saveConversation(Conversation theConversation) {
		theConversation.getAttachment().setConversation(theConversation);
    	theConversation.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.getAttachment().setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.getAttachment().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        conversationDAO.save(theConversation);
    }

    @Transactional
    public  Optional<Conversation> getConversation(int theId) {
        return Optional.of(conversationDAO.findById(theId));
    }

    @Transactional
    public void deleteConversation(int theId) {
        conversationDAO.deleteById(theId);
    }

	@Override
	public Conversation findById(int theId) {
		// TODO Auto-generated method stub
		return conversationDAO.findById(theId);
	}





}
package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import com.namanjain.entity.Conversation;

public interface ConversationService {

	
	public Optional<Conversation> getConversation(int theId);
	
	public Conversation findById(int theId);

	public void saveConversation(Conversation theConversation);

    public void deleteConversation(int theId);
    
    public List < Conversation > getConversations();
    
	
	
}

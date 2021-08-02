package com.namanjain.service;

import java.util.List;
import com.namanjain.entity.*;

public interface TicketService {

	public Ticket getTicket(int theId);
	
	public Ticket findById(int theId);
	
	public Ticket findByCode(String theCode);

    public void saveTicket(Ticket theTicket); 

    public void deleteTicket(int theId);
    
    public List < Ticket > getTickets();
    
    public Conversation addConversation(Ticket theTicket,Conversation theConversation);
    
    public List<Conversation> findAllConversationsById(int theId);
    
    public List<Conversation> findAllConversationsByCode(String theCode);
    
    public List < Ticket > getTicketsByTicketStatus(String theStatus);
    
    public Ticket changeStatus(Ticket theTicket);
    
}
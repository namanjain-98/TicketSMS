package com.namanjain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namanjain.dao.*;
import com.namanjain.entity.Conversation;
import com.namanjain.entity.Ticket;



@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    

    @Autowired
    private JavaMailSender javaMailSender;


    @Transactional
    public List < Ticket > getTickets() {
        return (List<Ticket>) ticketDAO.findAll();
    }

    @Transactional
    public void saveTicket(Ticket theTicket) {
    	theTicket.setCode("T"+ (int)(Math.random() * 100000));
    	theTicket.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theTicket.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	ticketDAO.save(theTicket);
    }



    @Transactional
    public void deleteTicket(int theId) {
        ticketDAO.deleteById(theId);
    }

	@Override
	public Conversation addConversation(Ticket theTicket, Conversation theConversation) {
		theConversation.setTicketId(theTicket);
		theConversation.getAttachment().setConversation(theConversation);
    	theConversation.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.getAttachment().setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theConversation.getAttachment().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		theTicket.getConversations().add(theConversation);
		ticketDAO.save(theTicket);
		return theConversation;
	}

	@Override
	public Ticket findById(int theId) {
		return ticketDAO.findById(theId);
	}

	@Override
	public List<Conversation> findAllConversationsById(int theId) {
		Ticket ticket = ticketDAO.findById(theId);
		return ticket.getConversations();
	}

	@Override
	public Ticket findByCode(String theCode) {
		return ticketDAO.findByCode(theCode);
	}

	@Override
	public List<Conversation> findAllConversationsByCode(String theCode) {
		Ticket ticket = ticketDAO.findByCode(theCode);
		return ticket.getConversations();
	}

	@Override
	public Ticket getTicket(int theId) {	
		return ticketDAO.findById(theId);
	}

	@Override
	public List<Ticket> getTicketsByTicketStatus(String theStatus) {
		return (List<Ticket>) ticketDAO.findAllByTicketStatus(theStatus);
	}

	@Transactional
	public Ticket changeStatus(Ticket theTicket) {
	
		
		
		if(theTicket.getTicketStatus().equals("OPEN")) {
			theTicket.setTicketStatus("CLOSED");
		}
		else if(theTicket.getTicketStatus().equals("CLOSED")) {
			theTicket.setTicketStatus("REOPENED");
		}
		else if(theTicket.getTicketStatus().equals("REOPENED")) {
			theTicket.setTicketStatus("CLOSED");
		
		}
		theTicket.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	
		
		
		if(theTicket.getTicketStatus().equals("CLOSED")) {
			
			SimpleMailMessage mailToSend = new SimpleMailMessage();
			mailToSend.setTo(theTicket.getCreatedBy());
			mailToSend.setSubject("Your Ticket Status");
			mailToSend.setText("Hi, \n Your ticket has been changed to " + theTicket.getTicketStatus());
			
			javaMailSender.send(mailToSend);
		}
		
		return ticketDAO.save(theTicket);
	}
	
	
	
}	
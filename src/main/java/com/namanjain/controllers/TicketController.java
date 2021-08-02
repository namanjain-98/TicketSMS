package com.namanjain.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.namanjain.converters.ConversationDtoConverter;
import com.namanjain.converters.TicketDtoConverter;
import com.namanjain.dto.ConversationDTO;
import com.namanjain.dto.TicketDTO;
import com.namanjain.entity.Attachment;
import com.namanjain.entity.Conversation;
import com.namanjain.entity.Ticket;
import com.namanjain.service.ConversationService;
import com.namanjain.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	private static final String UPLOAD_DIRECTORY_FOR_ATTACHMENTS = "C:\\Users\\Naman Jain\\eclipse-workspace\\ticketsms3upload\\images\\attachments";

	@Autowired
	private TicketService TicketService;

	@Autowired
	private ConversationService ConversationService;

	@RequestMapping("/listTickets")
	public String listTickets(Model theModel) {
		List<Ticket> theTickets = TicketService.getTickets();
		theModel.addAttribute("Tickets", theTickets);
		int openTickets = TicketService.getTicketsByTicketStatus("OPEN").size()
				+ TicketService.getTicketsByTicketStatus("REOPENED").size();
		int closeTickets = TicketService.getTicketsByTicketStatus("CLOSED").size();
		theModel.addAttribute("openTickets", openTickets);
		theModel.addAttribute("closeTickets", closeTickets);
		return "list-tickets";
	}

	@RequestMapping("/createTicket")
	public String createTicket(Model theModel) {
		TicketDTO theTicketDto = new TicketDTO();
		theModel.addAttribute("ticketDto", theTicketDto);
		return "ticket-form";
	}

	@RequestMapping("/saveTicket")
	public String saveTicket(@ModelAttribute("ticketDto") TicketDTO theTicketDto, Model theModel) throws IOException {
		String sub = theTicketDto.getTicketSubject().toString();
		Ticket theTicket = TicketDtoConverter.DtoToEntity(theTicketDto);
		theTicket.setTicketSubject(sub);
		TicketService.saveTicket(theTicket);
		
		Conversation theConversation = new Conversation();
		theConversation.setTicketId(theTicket);
		theConversation.setMessage(theTicketDto.getMessage());

		Attachment theAttachment = new Attachment();
		theAttachment.setConversation(theConversation);
		theConversation.setAttachment(theAttachment);
		ConversationService.saveConversation(theConversation);
		
		String filename = Integer.toString(theConversation.getId());	
		byte[] bytes = theTicketDto.getAttachmentFile().getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(UPLOAD_DIRECTORY_FOR_ATTACHMENTS + File.separator + filename + ".jpg")));
		stream.write(bytes);
		stream.flush();
		stream.close();
		
		theConversation.getAttachment().setFileName(theTicketDto.getAttachmentFile().getOriginalFilename());
		theConversation.getAttachment().setFilePath(UPLOAD_DIRECTORY_FOR_ATTACHMENTS + File.separator + filename + ".jpg");
		ConversationService.saveConversation(theConversation);
		
		theModel.addAttribute("ticketId", theTicket.getId());
		return "redirect:/ticket/ticketDetail";
	}

	@RequestMapping("/updateTicket")
	public String updateTicket(@RequestParam("ticketId") int theId, Model theModel) {
		Ticket theTicket = TicketService.getTicket(theId);
		theModel.addAttribute("ticket", theTicket);
		return "ticket-form";
	}

	@RequestMapping("/deleteTicket")
	public String deleteTicket(@RequestParam("ticketId") int theId) {
		TicketService.deleteTicket(theId);
		return "redirect:/ticket/listTickets";
	}

	@RequestMapping("/ticketDetail")
	public String ticketDetail(@RequestParam("ticketId") int theId, Model theModel) {
		Ticket theTicket = TicketService.findById(theId);
		List<Conversation> theConversations = TicketService.findAllConversationsById(theId);
		theModel.addAttribute("ticket", theTicket);
		theModel.addAttribute("ticketId", theId);
		theModel.addAttribute("conversations", theConversations);
		Conversation theConversation = new Conversation();
		ConversationDTO theConversationDto = ConversationDtoConverter.entityToDto(theConversation);
		theModel.addAttribute("conversationDto", theConversationDto);
		return "ticket-detail";
	}

	@RequestMapping("/track")
	public String ticket() {
		return "track-ticket";
	}

	@RequestMapping("/trackTicket")
	public String trackTicket(@RequestParam("code") String theCode, Model theModel) {
		Ticket theTicket = TicketService.findByCode(theCode);
		theModel.addAttribute("ticket_code", theCode);
		List<Conversation> theConversations = TicketService.findAllConversationsByCode(theCode);
		theModel.addAttribute("ticket", theTicket);
		theModel.addAttribute("ticketId", theTicket.getId());
		theModel.addAttribute("conversations", theConversations);
		Conversation theConversation = new Conversation();
		ConversationDTO theConversationDto = ConversationDtoConverter.entityToDto(theConversation);
		theModel.addAttribute("conversationDto", theConversationDto);
		return "ticket-detail";
	}

	@RequestMapping(value = "/{ticketId}/addConversation", method = RequestMethod.POST)
	public String addConversation(@PathVariable(name = "ticketId") int ticketId,
			@ModelAttribute("conversationDto") ConversationDTO theConversationDto, Model theModel) throws IOException {

		Ticket theTicket = TicketService.getTicket(ticketId); 
		theModel.addAttribute("ticketId", theTicket.getId());
		
		Conversation theConversation = ConversationDtoConverter.DtoToEntity(theConversationDto);
		theConversation.setTicketId(theTicket);	
		ConversationService.saveConversation(theConversation);
				
		String filename = Integer.toString(theConversation.getId());
		
		byte[] bytes = theConversationDto.getAttachmentFile().getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(UPLOAD_DIRECTORY_FOR_ATTACHMENTS + File.separator + filename + ".jpg")));
		stream.write(bytes);
		stream.flush();
		stream.close();

		theConversation.getAttachment()
		.setFilePath(UPLOAD_DIRECTORY_FOR_ATTACHMENTS + File.separator + filename + ".jpg");
		theConversation.getAttachment().setFileName(theConversationDto.
				 getAttachmentFile().getOriginalFilename());
		ConversationService.saveConversation(theConversation);
		
		
		return "redirect:/ticket/ticketDetail";
	}

	@RequestMapping(value = "/downloadAttachment")
	public void downloadImage(@RequestParam("conversationId") int theId, HttpServletResponse response) {

		Conversation theConversation = ConversationService.findById(theId);

		System.out.print(theConversation.getAttachment().getFilePath());

		File file = new File(theConversation.getAttachment().getFilePath());

		response.setContentType("application/jpg");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/changeStatus")
	public String changeStatus(@RequestParam("ticketId") int theId, Model theModel) {
		Ticket theTicket = TicketService.getTicket(theId);
		TicketService.changeStatus(theTicket);
		theModel.addAttribute("ticketId", theTicket.getId());
		return "redirect:/ticket/ticketDetail";
	}

}

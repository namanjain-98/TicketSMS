package com.namanjain.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class TicketDTO {

	private int id;
	private String ticketSubject;
	private String ticketStatus;
	private String message;
	private CommonsMultipartFile attachmentFile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTicketSubject() {
		return ticketSubject;
	}
	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CommonsMultipartFile getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(CommonsMultipartFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	
	
	
}

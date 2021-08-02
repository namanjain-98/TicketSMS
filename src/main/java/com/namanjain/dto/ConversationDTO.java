package com.namanjain.dto;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ConversationDTO {

	private int id;	
	private String message;
	private AttachmentDTO attachment;
	private Date createdAt;
	private String createdBy;
	private Date updatedAt;
	private Date updatedBy;
	private CommonsMultipartFile attachmentFile;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AttachmentDTO getAttachment() {
		return attachment;
	}

	public void setAttachment(AttachmentDTO attachment) {
		this.attachment = attachment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Date updatedBy) {
		this.updatedBy = updatedBy;
	}

	public CommonsMultipartFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(CommonsMultipartFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}


	
}

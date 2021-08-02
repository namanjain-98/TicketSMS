package com.namanjain.dto;

import java.util.Date;

public class AttachmentDTO {

	private int id;
	
	private String filePath;
	private ConversationDTO conversationId;
	private Date createdAt;
	private String createdBy;
	private Date updatedAt;
	private Date updatedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public ConversationDTO getConversationId() {
		return conversationId;
	}
	public void setConversationId(ConversationDTO conversationId) {
		this.conversationId = conversationId;
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
	
	
}

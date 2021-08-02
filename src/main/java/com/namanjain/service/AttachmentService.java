package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import com.namanjain.entity.Attachment;

public interface AttachmentService {

	public Optional<Attachment> getAttachment(int theId);

    public static void saveAttachment(Attachment theAttachment) {
		// TODO Auto-generated method stub
		
	} 

    public void deleteAttachment(int theId);
    
    public List < Attachment > getAttachments();
	
}

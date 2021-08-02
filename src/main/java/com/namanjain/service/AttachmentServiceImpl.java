package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namanjain.dao.*;
import com.namanjain.entity.Attachment;



@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Transactional
    public List < Attachment > getAttachments() {
        return (List<Attachment>) attachmentDAO.findAll();
    }

    @Transactional
    public void saveAttachment(Attachment theAttachment) {
    	theAttachment.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theAttachment.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        attachmentDAO.save(theAttachment);
    }

    @Transactional
    public  Optional<Attachment> getAttachment(int theId) {
        return Optional.of(attachmentDAO.findById(theId));
    }

    @Transactional
    public void deleteAttachment(int theId) {
        attachmentDAO.deleteById(theId);
    }

}
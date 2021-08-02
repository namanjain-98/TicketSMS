package com.namanjain.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.namanjain.dto.AttachmentDTO;
import com.namanjain.entity.Attachment;

public class AttachmentDtoConverter {
	
	public AttachmentDTO entityToDto(Attachment attachment) {
		
		ModelMapper mapper = new ModelMapper();
		AttachmentDTO map = mapper.map(attachment,AttachmentDTO.class);
		return map;		
	}
	
	public Attachment DtoToEntity(AttachmentDTO attachmentDto) {		
		ModelMapper mapper = new ModelMapper();
		/*
		 * mapper.addMappings(AttachmentDTO -> attachmentDto.getFileName(),
		 * Attachment::setFileName);
		 */
		Attachment map = mapper.map(attachmentDto,Attachment.class);
		/*
		 * mapper.addMappings(new PropertyMap<AttachmentDTO, Attachment>() { protected
		 * void configure() { map.setFileName(attachmentDto.getFileName()); } });
		 */
		
		return map;		
	}
	
	public List<AttachmentDTO> entityToDto(List<Attachment> attachments) {
		return attachments.stream().map(x -> entityToDto(x) ).collect(Collectors.toList());
	}
	
	public List<Attachment> DtoToEntity(List<AttachmentDTO> attachmentDtos) {
		return attachmentDtos.stream().map(x -> DtoToEntity(x) ).collect(Collectors.toList());
	}
	

}

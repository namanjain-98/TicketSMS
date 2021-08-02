package com.namanjain.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.namanjain.dto.ConversationDTO;
import com.namanjain.entity.Conversation;

public class ConversationDtoConverter {
	
	public static ConversationDTO entityToDto(Conversation conversation) {
		
		ModelMapper mapper = new ModelMapper();
		ConversationDTO map = mapper.map(conversation,ConversationDTO.class);
		return map;		
	}
	
	public static Conversation DtoToEntity(ConversationDTO conversationDto) {		
		ModelMapper mapper = new ModelMapper();
		Conversation map = mapper.map(conversationDto,Conversation.class);
		return map;		
	}
	
	public List<ConversationDTO> entityToDto(List<Conversation> conversations) {
		return conversations.stream().map(x -> entityToDto(x) ).collect(Collectors.toList());
	}
	
	public List<Conversation> DtoToEntity(List<ConversationDTO> conversationDtos) {
		return conversationDtos.stream().map(x -> DtoToEntity(x) ).collect(Collectors.toList());
	}
	

}

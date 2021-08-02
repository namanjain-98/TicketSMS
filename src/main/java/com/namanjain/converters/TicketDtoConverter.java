package com.namanjain.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.namanjain.dto.TicketDTO;
import com.namanjain.entity.Ticket;

public class TicketDtoConverter {
	
	public TicketDTO entityToDto(Ticket ticket) {
		
		ModelMapper mapper = new ModelMapper();
		TicketDTO map = mapper.map(ticket,TicketDTO.class);
		return map;		
	}
	
	public static Ticket DtoToEntity(TicketDTO ticketDto) {		
		ModelMapper mapper = new ModelMapper();
		Ticket map = mapper.map(ticketDto,Ticket.class);
		return map;		
	}
	
	public List<TicketDTO> entityToDto(List<Ticket> tickets) {
		return tickets.stream().map(x -> entityToDto(x) ).collect(Collectors.toList());
	}
	
	public List<Ticket> DtoToEntity(List<TicketDTO> ticketDtos) {
		return ticketDtos.stream().map(x -> DtoToEntity(x) ).collect(Collectors.toList());
	}
	
	
}

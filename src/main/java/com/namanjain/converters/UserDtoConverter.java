package com.namanjain.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.namanjain.dto.UserDTO;
import com.namanjain.entity.User;

public class UserDtoConverter {

	public static UserDTO entityToDto(User user) {
		
		ModelMapper mapper = new ModelMapper();
		UserDTO map = mapper.map(user,UserDTO.class);
		return map;		
	}
	
	public static User DtoToEntity(UserDTO userDto) {		
		ModelMapper mapper = new ModelMapper();
		User map = mapper.map(userDto,User.class);
		return map;		
	}
	
	public static List<UserDTO> entityToDto(List<User> users) {
		return users.stream().map(x -> entityToDto(x) ).collect(Collectors.toList());
	}
	
	public List<User> DtoToEntity(List<UserDTO> userDtos) {
		return userDtos.stream().map(x -> DtoToEntity(x) ).collect(Collectors.toList());
	}
	
}

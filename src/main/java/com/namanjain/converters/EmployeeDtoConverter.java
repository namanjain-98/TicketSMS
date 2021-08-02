package com.namanjain.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.namanjain.dto.EmployeeDTO;
import com.namanjain.entity.Employee;

public class EmployeeDtoConverter {

	public static EmployeeDTO entityToDto(Employee employee) {
		
		ModelMapper mapper = new ModelMapper();
		EmployeeDTO map = mapper.map(employee,EmployeeDTO.class);
		return map;		
	}
	
	public static Employee DtoToEntity(EmployeeDTO employeeDto) {		
		ModelMapper mapper = new ModelMapper();
		Employee map = mapper.map(employeeDto,Employee.class);
		return map;		
	}
	
	public static List<EmployeeDTO> entityToDto(List<Employee> employees) {
		return employees.stream().map(x -> entityToDto(x) ).collect(Collectors.toList());
	}
	
	public List<Employee> DtoToEntity(List<EmployeeDTO> employeeDtos) {
		return employeeDtos.stream().map(x -> DtoToEntity(x) ).collect(Collectors.toList());
	}
	
}

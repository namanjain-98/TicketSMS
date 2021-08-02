package com.namanjain.service;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.namanjain.dao.*;
import com.namanjain.dto.EmployeeDTO;
import com.namanjain.entity.Conversation;
import com.namanjain.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private ConversationDAO conversationDAO;

    @Transactional
    public List < Employee > getEmployees() {
        return (List<Employee>) employeeDAO.findAll();
    }

    @Transactional
    public void saveEmployee(Employee theEmployee) {
    	theEmployee.setEmployeeCode("EMP"+ (int)(Math.random() * 1000)) ;
    	theEmployee.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theEmployee.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        employeeDAO.save(theEmployee);
    }

    @Transactional
    public  Optional<Employee> getEmployee(int theId) {
        return Optional.of(employeeDAO.findById(theId));
    }

    @Transactional
    public void deleteEmployee(int theId) {
    Employee theEmployee = employeeDAO.findById(theId);
    File file1 = new File(theEmployee.getDocumentPath());
    file1.delete();
    File file2 = new File(theEmployee.getProfileImg());
    file2.delete();
    employeeDAO.deleteById(theId);
    }

	@Override
	public Employee getEmployeeById(int theId) {
		return employeeDAO.findById(theId);
	}

	@Override
	public List<EmployeeDTO> setTotalTickets(List<EmployeeDTO> theEmployeeDtos) {
		for(EmployeeDTO employeeDto : theEmployeeDtos )
		{					
			List<Conversation> listofconversation = conversationDAO.getConversationsByCreatedBy(employeeDto.getUsername());
			Set<Integer> setofconversation = new HashSet<Integer>();
			for(Conversation conversation : listofconversation) {
		    	 	setofconversation.add(conversation.getTicketId().getId());		    	
		    }
			int size = setofconversation.size();
			employeeDto.setTotalTickets(size);
		}
		
		return theEmployeeDtos;
	}


}
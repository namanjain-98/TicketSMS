package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import com.namanjain.dto.EmployeeDTO;
import com.namanjain.entity.*;

public interface EmployeeService {

    public List < Employee > getEmployees();

    public void saveEmployee(Employee theEmployee);

    public Optional<Employee> getEmployee(int theId);
    
    public Employee getEmployeeById(int theId);

    public void deleteEmployee(int theId);
    
    public List<EmployeeDTO> setTotalTickets(List<EmployeeDTO> theEmployeeDto);


}
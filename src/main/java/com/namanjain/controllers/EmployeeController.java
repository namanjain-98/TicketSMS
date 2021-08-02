package com.namanjain.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.namanjain.converters.EmployeeDtoConverter;
import com.namanjain.dto.EmployeeDTO;
import com.namanjain.entity.Employee;
import com.namanjain.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final String UPLOAD_DIRECTORY_FOR_ID_PROOF ="C:\\Users\\Naman Jain\\eclipse-workspace\\ticketsms3upload\\images\\employee\\idProof";
	private static final String UPLOAD_DIRECTORY_FOR_PROFILE_IMG ="C:\\Users\\Naman Jain\\eclipse-workspace\\ticketsms3upload\\images\\employee\\profileImage";
	
	@Autowired
	private EmployeeService EmployeeService;

	@RequestMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees = EmployeeService.getEmployees();
		List<EmployeeDTO> theEmployeeDtos = EmployeeDtoConverter.entityToDto(theEmployees);
		EmployeeService.setTotalTickets(theEmployeeDtos);
		theModel.addAttribute("Employees", theEmployeeDtos);
		return "list-employees";
	}

	@RequestMapping("/add")
	public String showFormForAdd(Model theModel) {
		return "employee-form";
	}

	@RequestMapping(value = "/saveEmployee")
	public String saveEmployee(@RequestParam("idProofFile") CommonsMultipartFile idProofFile,
			@RequestParam("profileImgFile") CommonsMultipartFile profileImgFile,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("dateOfBirth") java.sql.Date dateOfBirth, @RequestParam("idProof") String idProof,
			@RequestParam("role") String role) throws IOException {
		Employee theEmployee = new Employee();
		theEmployee.setFirstName(firstName);
		theEmployee.setLastName(lastName);
		theEmployee.setUsername(username);
		theEmployee.setPassword(password);
		theEmployee.setDateOfBirth(dateOfBirth);
		theEmployee.setIdProof(idProof);
		theEmployee.setRole(role);
		EmployeeService.saveEmployee(theEmployee);  
		theEmployee.setDocumentPath(UPLOAD_DIRECTORY_FOR_ID_PROOF + File.separator + theEmployee.getId() + ".jpg");
		theEmployee.setProfileImg( UPLOAD_DIRECTORY_FOR_PROFILE_IMG + File.separator + theEmployee.getId() + ".jpg");
		EmployeeService.saveEmployee(theEmployee);  
		
		String filename1 = Integer.toString(theEmployee.getId());  	  
	    byte[] bytes1 = idProofFile.getBytes();  
	    BufferedOutputStream stream1 =new BufferedOutputStream(new FileOutputStream(  
	         new File( UPLOAD_DIRECTORY_FOR_ID_PROOF + File.separator + filename1 + ".jpg") ));  
	    stream1.write(bytes1);  
	    stream1.flush();  
	    stream1.close(); 
	    
	    String filename2 = Integer.toString(theEmployee.getId());	  
	    byte[] bytes2 = profileImgFile.getBytes();  
	    BufferedOutputStream stream2 =new BufferedOutputStream(new FileOutputStream(  
	         new File( UPLOAD_DIRECTORY_FOR_PROFILE_IMG + File.separator + filename2 + ".jpg") ));  
	    stream2.write(bytes2);  
	    stream2.flush();  
	    stream2.close(); 
	    
		return "redirect:/";
	}

	@RequestMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		Optional<Employee> theEmployee = EmployeeService.getEmployee(theId);
		theModel.addAttribute("employee", theEmployee);
		return "employee-update-form";
	}

	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		EmployeeService.deleteEmployee(theId);
		return "redirect:/employee/list";
	}

	@RequestMapping("/detail")
	public String employeeDetail(@RequestParam("employeeId") int theId, Model theModel) {
		Employee theEmployee = EmployeeService.getEmployeeById(theId);
		theModel.addAttribute("employee", theEmployee);
		return "employee-detail";
	}

	@RequestMapping(value = "/downloadImage")
	public void downloadImage(@RequestParam("employeeId") int theId,HttpServletResponse response) {

		Employee theEmployee = EmployeeService.getEmployeeById(theId);
		
		File file = new File(theEmployee.getDocumentPath());
		
		response.setContentType("application/jpg");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/showImage")
	public void showImage(@RequestParam("employeeId") int theId,HttpServletResponse response) {

		Employee theEmployee = EmployeeService.getEmployeeById(theId);
		
		File file = new File(theEmployee.getProfileImg());
		
		response.setContentType("application/jpg");
		response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
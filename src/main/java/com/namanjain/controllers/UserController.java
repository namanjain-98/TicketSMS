package com.namanjain.controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.namanjain.converters.UserDtoConverter;
import com.namanjain.dto.UserDTO;
import com.namanjain.entity.User;
import com.namanjain.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String UPLOAD_DIRECTORY_FOR_PROFILE_IMG ="C:\\Users\\Naman Jain\\eclipse-workspace\\ticketsms3upload\\images\\user\\profileImg";
	
    @Autowired
    private UserService UserService;

    @RequestMapping("/list")
    public String listUsers(Model theModel) {
        List < User > theUsers = UserService.getUsers();
        theModel.addAttribute("Users", theUsers);
        return "list-users";
    }

    @RequestMapping("/add")
    public String showFormForAdd(Model theModel) {
        UserDTO theUserDto = new UserDTO();
        theModel.addAttribute("user", theUserDto);
        return "user-form";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDTO theUserDto) {
    	User theUser = UserDtoConverter.DtoToEntity(theUserDto);
    	UserService.saveUser(theUser);
    	
    	
    	String filename =Integer.toString(theUser.getId());	  
	    byte[] bytes = theUserDto.getProfileImg().getBytes();
	    BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(  
			     new File( UPLOAD_DIRECTORY_FOR_PROFILE_IMG + File.separator + filename + ".jpg") ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	    try {
			stream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    try {
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    
	    theUser.setProfileImg(UPLOAD_DIRECTORY_FOR_PROFILE_IMG + File.separator + theUser.getId() + ".jpg");
	    UserService.saveUser(theUser);
        
        return "redirect:/login";
    }

    @RequestMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int theId,
        Model theModel) {
        Optional<User> theUser = UserService.getUser(theId);
        theModel.addAttribute("user", theUser);
        return "user-form";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        UserService.deleteUser(theId);
        return "redirect:/user/list";
    }
}
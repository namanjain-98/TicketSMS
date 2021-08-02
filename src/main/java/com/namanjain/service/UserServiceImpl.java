package com.namanjain.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namanjain.dao.*;
import com.namanjain.entity.CustomOauth2User;
import com.namanjain.entity.Provider;
import com.namanjain.entity.User;



@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List < User > getUsers() {
        return (List<User>) userDAO.findAll();
    }

    @Transactional
    public void saveUser(User theUser) {
    	theUser.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    	theUser.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        userDAO.save(theUser);
    }

    @Transactional
    public  Optional<User> getUser(int theId) {
        return Optional.of(userDAO.findById(theId));
    }

    @Transactional
    public void deleteUser(int theId) {
    	User theUser =userDAO.findById(theId);
    	File file = new File(theUser.getProfileImg());
    	file.delete();
        userDAO.deleteById(theId);
    }

	@Override
	public void processOAuthPostLogin(CustomOauth2User oauth2user) {
		String email = oauth2user.getEmail();
		
		User existUser = userDAO.findByUsername(email);
         
	        if (existUser == null) {
	            User newUser = new User();
	            newUser.setUsername(email);
	            newUser.setRole("USER");
	            newUser.setFirstName(oauth2user.getGivenName());
	            newUser.setLastName(oauth2user.getFamilyName());
	            newUser.setProvider(Provider.GOOGLE);
	            userDAO.save(newUser);
	        }
	}

}
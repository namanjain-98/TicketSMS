package com.namanjain.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserDTO {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private java.sql.Date dateOfBirth;

	private String role;
	
	private CommonsMultipartFile profileImg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CommonsMultipartFile getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(CommonsMultipartFile profileImg) {
		this.profileImg = profileImg;
	}
	
	

}

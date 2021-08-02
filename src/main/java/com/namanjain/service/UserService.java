package com.namanjain.service;

import java.util.List;
import java.util.Optional;

import com.namanjain.entity.*;

public interface UserService{

    public List < User > getUsers();

    public void saveUser(User theUser);

    public Optional<User> getUser(int theId);

    public void deleteUser(int theId);

	public void processOAuthPostLogin(CustomOauth2User oauthUser);


}
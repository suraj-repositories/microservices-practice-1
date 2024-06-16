package com.on29may.user.service.services;

import java.util.List;

import com.on29may.user.service.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String user);	
	
}

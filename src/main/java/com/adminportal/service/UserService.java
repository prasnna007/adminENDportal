package com.adminportal.service;

import java.util.List;
import java.util.Set;

import com.adminportal.entities.User;
import com.adminportal.pojos.security.UserRole;

public interface UserService {	
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);
	
	List<User> getAllUser();
	
	User findOne(String username);

	void remove(User user);

	User findByUsername(String username);
	
	User findById(int id);
}

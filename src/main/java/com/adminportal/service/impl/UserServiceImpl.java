package com.adminportal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.entities.User;
import com.adminportal.pojos.security.UserRole;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserService;
 

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localeUser = userRepository.findByUsername(user.getUsername());
		if(localeUser != null) {
			LOG.info("User {} already exists. Nothing will be done.", user.getUsername());
			
		}else {
			for( UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			localeUser = userRepository.save(user);
		}
		
		
		return localeUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findOne(String username) {
		Optional<User> bookResponse = Optional.ofNullable(userRepository.findByUsername(username));
		User user = bookResponse.get();
		return user;

	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
		
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	 

	
}

package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adminportal.entities.User;
import com.adminportal.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user.getUsername().equals("admin") || user.getUsername().equals("admin2")) {
		return user;
		}
		throw new UsernameNotFoundException("Username not found.");
	}

	public User loadUserByid(int uid) {
		User user = userRepository.findById(uid);
		if(null == user) {
			throw new UsernameNotFoundException("User with this ID not found.");
		}
		return user;
	}
	
}

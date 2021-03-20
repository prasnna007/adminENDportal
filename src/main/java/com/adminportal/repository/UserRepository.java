package com.adminportal.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.adminportal.entities.User;
 

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

	User findById(int uid);
	
	 
	//User findById(Integer id);
	
	
}

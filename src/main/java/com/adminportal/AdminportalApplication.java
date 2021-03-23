package com.adminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adminportal.entities.User;
import com.adminportal.pojos.security.Role;
import com.adminportal.pojos.security.UserRole;
import com.adminportal.service.UserService;
import com.adminportal.utility.SecurityUtility;



@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminportalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("admin@gmail.com");
		user1.setFirstName("ADMIN");
		user1.setPhone("00100");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);
		
		User user2 = new User();
		user2.setUsername("admin2");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("admin2"));
		user2.setEmail("dream.fello.12.12@gmail.com");
		user2.setFirstName("ADMIN");
		user2.setPhone("00100");
		Set<UserRole> userRoles2 = new HashSet<>();
		Role role2= new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, role2));
		userService.createUser(user2, userRoles2);
		
		
		
		
	}
}

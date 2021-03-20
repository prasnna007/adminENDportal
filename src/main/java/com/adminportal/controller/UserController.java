package com.adminportal.controller;
 
 
import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.adminportal.entities.ReqBook;
import com.adminportal.entities.User;
import com.adminportal.service.ReqBookService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.UserSecurityService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReqBookService reqBookservice;
	 
	@Autowired
	private UserSecurityService usersecservice;
	
	@RequestMapping("/userList")
	public String bookList(Model model) {
		List<User> userList = userService.getAllUser();
		//HTML page model
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") int id, Model model) {
		User user = userService.findById(id);
		if(user.getUsername().equals("admin") || user.getFirstName().equals("ADMIN"))
		{ 
		return "redirect:/user/userList";
		}

		model.addAttribute("user", user);
		userService.remove(user);
		return "redirect:/user/userList";
		
	}
	 
	
	 
	@RequestMapping("/reqbookLists")
	public String reqbookList(Model model) {
		List<ReqBook> reqbookLists = reqBookservice.findAll();
		model.addAttribute("reqbookLists", reqbookLists);
		
		return "reqbookLists";
	}

	 
	
	
	@RequestMapping("/reqBooksInfo")
	public String reqBookInfo(@RequestParam("id") Integer id, Model model) {
		ReqBook reqbook = reqBookservice.findOne(id);
		model.addAttribute("reqbook",reqbook);
		return "reqbooksInfo";
	}
	
	

	
	@RequestMapping("/userInfo")
	public String userInfo(@RequestParam("uid") int uid, Model model,Principal principal) {
		User user = (User) usersecservice.loadUserByid(uid);
	
		model.addAttribute("user",user);
		
		return "userInfo";
	}
	}


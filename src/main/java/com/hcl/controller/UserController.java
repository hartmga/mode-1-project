package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.exception.AuthorityDoesNotExistException;
import com.hcl.exception.UsernameUnavailableException;
import com.hcl.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService us;
	
	@GetMapping("/register")
	public String registrationForm() {
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password) throws AuthorityDoesNotExistException, UsernameUnavailableException {
		us.registerUser(username, password);
		return "redirect:/products";
	}
	
}

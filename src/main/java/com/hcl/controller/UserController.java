package com.hcl.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String registrationForm(Model model, String invalidUsername) {
		if(invalidUsername!=null) {
			model.addAttribute("error", "Username not available");
		}
		return "register";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(error !=null) {
			model.addAttribute("errorMsg", "Invalid credentials");
		}
		if(logout!=null) {
			model.addAttribute("msg", "You have successefully logged out");
		}
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) throws AuthorityDoesNotExistException {
		try {
			us.registerUser(username, password);
			request.login(username, password);
		} catch (UsernameUnavailableException e) {
			return "redirect:/register?invalidUsername";
		} catch (ServletException e) {
			return "redirect:/register";
		}
		return "redirect:/products";
	}
	
}

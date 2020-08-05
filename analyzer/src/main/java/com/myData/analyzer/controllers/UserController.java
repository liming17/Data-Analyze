package com.myData.analyzer.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myData.analyzer.entities.Role;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.pojos.UserRegistration;
import com.myData.analyzer.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@GetMapping(value = "/users")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping(value = "/register")
	public String register(@RequestBody UserRegistration userRegistration) {
		if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
			return "password do not match";
		}else if(userService.getUser(userRegistration.getUsername()) != null) {
			return "user already exists";
		}
		
		userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER")), true));
		
		return "User created!";
	}
	
	@GetMapping(value = "/getCurrentUser")
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@GetMapping(value = "/logoutCurrentUser")
	public String logout(@RequestParam (value="access_token") String accessToken) {
		tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
		return "Logout successfully!";
	}

}

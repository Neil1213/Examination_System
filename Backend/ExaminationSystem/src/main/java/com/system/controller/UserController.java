package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.User;
import com.system.service.UserService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200")
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/getUsers/role={role}")
	private final List<User> getUsers(@PathVariable String role){
		return service.getUsers(role);
	}
	@GetMapping("/getUsers")
	private final List<User> getUsers(){
		return service.getUsers();
	}
	@GetMapping("/getUser/username={username}")
	private final User getUser(@PathVariable String username) {
		return service.getUser(username);
	}
}

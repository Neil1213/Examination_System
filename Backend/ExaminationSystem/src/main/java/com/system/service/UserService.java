package com.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.UserRepository;
import com.system.model.Role;
import com.system.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	public final List<User> getUsers(String role){
		ArrayList<User> bufRtnData = new ArrayList<User>();
		repo.findAll().forEach((user)->{
			user.getRoles().forEach((roles)->{
				if(roles.getName().toString().equals(role)) {
					bufRtnData.add(user);
				}
			});
		});
		List<User> rtnData = bufRtnData;
		return rtnData;
	}
	public final List<User> getUsers(){
		return repo.findAll();
	}
	public final User getUser(String username) {
		ArrayList<User> buf = new ArrayList<User>();
		repo.findAll().forEach((data)->{
			if(data.getUsername().equals(username)) {
				 buf.add(data);
			}
		});
		User rtnData = buf.get(0);
		return rtnData;
	}
}

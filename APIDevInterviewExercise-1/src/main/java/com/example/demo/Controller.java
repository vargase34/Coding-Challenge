package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	ConcurrentMap<String, User> users = new ConcurrentHashMap<>();
	
	@GetMapping("/{name}")
	public User getUser(@PathVariable String name) {
		return users.get(name);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return new ArrayList<User>(users.values());
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		users.put(user.getName(), user);
		return user;
	}
}
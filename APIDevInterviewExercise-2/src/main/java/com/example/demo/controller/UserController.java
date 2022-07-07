package com.example.demo.controller;

import java.util.HashMap; 
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("users")
//	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//		User savedUser = userService.createUser(user);
//		
//		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
//	}
	
	@PostMapping("users")
	public Map<String, Object> createUser(@Valid @RequestBody User user){
		String url = "http://ip-api.com/json/" + user.getIp();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		result = result.replaceAll("\"", "");
		
		Map<String,String> map = new HashMap<String,String>();
		String[] entries = result.split(",");
//		return entries;
		try {
			for (String entry : entries) {
				String[] keyValue = entry.split(":");
				String key = keyValue[0].trim();
				String value = keyValue[1].trim();
				map.put(key,value);
			}
		}
		catch(Exception e) {}
//		return map;
		
		User savedUser = userService.createUser(user);	
		user.setCity(map.get("city"));
		
		Map<String, Object> post = new HashMap<String, Object>();
		if(!map.get("country").equals("Canada")) {
//			return map.get("country");
//			throw new IllegalStateException("Only Canadians Allowed.");		
//			return "hello";
//			this.denied();		
//			return new ResponseEntity<User>(savedUser, HttpStatus.UNAUTHORIZED);
////			return "Only Canadians Allowed";
			user.setErrorMsg("Only Canadians allowed to register");
			user.setId(null);
			user.setWelcomeMsg(map.get("city"), "N/A");
			post.put("errorMsg", user.getErrorMsg());
			return post;
		}
		else {
			//return "hello2";
			user.setErrorMsg("N/A");
			user.setWelcomeMsg(map.get("city"), "Welcome " + user.getName() + " from the Canadian city of " + map.get("city") + ".");
			post.put("id", user.getId());
			post.put("welcomeMsg", user.getWelcomeMsg());
			post.put("cityName", user.getCity());
			return post;
		}
////		return map.get("city");
//		User savedUser = userService.createUser(user);			
//		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		
	}
	
//	@PostMapping("users")
//	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//		String url = "http://ip-api.com/json/" + user.getIp();
//		RestTemplate restTemplate = new RestTemplate();
//		String result = restTemplate.getForObject(url, String.class);
//		result = result.replaceAll("\"", "");
//		
//		Map<String,String> map = new HashMap<String,String>();
//		String[] entries = result.split(",");
////		return entries;
//		try {
//			for (String entry : entries) {
//				String[] keyValue = entry.split(":");
//				String key = keyValue[0].trim();
//				String value = keyValue[1].trim();
//				map.put(key,value);
//			}
//		}
//		catch(Exception e) {}
////		return map;
//		user.setCity(map.get("city"));
//		if(!map.get("country").equals("Canada")) {
////			return map.get("country");
////			throw new IllegalStateException("Only Canadians Allowed.");		
////			return "hello";
////			this.denied();
////			User savedUser = userService.createUser(user);			
////			return new ResponseEntity<User>(savedUser, HttpStatus.UNAUTHORIZED);
//////			return "Only Canadians Allowed";
//			user.setErrorMsg("Only Canadians allowed to register");
//			user.setId(null);
//			user.setWelcomeMsg(map.get("city"), "N/A");
//		}
//		else {
//			//return "hello2";
//			user.setErrorMsg("N/A");
//			user.setWelcomeMsg(map.get("city"), "Welcome " + user.getName() + " from the Canadian city of " + map.get("city") + ".");
//		}
////		return map.get("city");
//		User savedUser = userService.createUser(user);			
//		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
//	}
	
//	public String denied() {
//		
//		return "Only Canadians Allowed";
//	}
}
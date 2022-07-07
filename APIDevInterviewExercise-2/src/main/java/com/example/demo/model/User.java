package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Table(name = "users")
@Entity
public class User {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator
	@GeneratedValue
	private UUID id;
	
	@Column(name = "name", nullable = false)
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(min = 8, message = "must be at least 8 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_#$%.]).{8,}$", message = "must have at least 1 uppercase, 1 lowercase, 1 digit, and 1 special character such as _ # $ % or .")
	private String password;
	
	@NotEmpty
	@Pattern(regexp = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}", message = "must be valid")
	public String ip;
	
	private String welcomeMsg;
	
	private String city;
	
	private String errorMsg;

	public User(String name, String password, String ip, String welcomeMsg, String city) {
		super();
		this.name = name;
		this.password = password;
		this.ip = ip;
		this.welcomeMsg = welcomeMsg;
		this.city = city;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getWelcomeMsg() {
//		if(welcomeMsg != null) {
//			welcomeMsg = "Welcome " + name + " from the Canadian city of " + city + ".";
//		}
//		else {
//			welcomeMsg = "N/A";
//		}
		return welcomeMsg;
	}
	public void setWelcomeMsg(String city, String welcomeMsg) {
		this.welcomeMsg = welcomeMsg;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String regionName) {
		this.city = regionName;
	}
//	public void getError() {
//		errorMsg = "only canadians allowed"
//	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
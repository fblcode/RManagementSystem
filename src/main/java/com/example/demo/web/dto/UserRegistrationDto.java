package com.example.demo.web.dto;

import java.util.List;

import com.example.demo.model.Role;

public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Role> roles;
	private String roleName;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password, String roleName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleName = roleName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
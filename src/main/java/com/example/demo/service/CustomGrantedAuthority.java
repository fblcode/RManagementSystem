package com.example.demo.service;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.model.Role;

public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;
	private Role role;

    public CustomGrantedAuthority(Role role) {
        this.role = role;
    }
    
    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
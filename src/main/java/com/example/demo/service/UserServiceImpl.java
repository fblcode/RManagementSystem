package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Transactional
	@Override
	public User save(UserRegistrationDto registrationDto) {
	    // Check if the email is already registered
	    if (isEmailAlreadyRegistered(registrationDto.getEmail())) {
	        throw new RuntimeException("Email is already registered");
	        // You might want to create a specific exception class for this case.
	    }

	    try {
	        User user = new User(registrationDto.getFirstName(), 
	                registrationDto.getLastName(), registrationDto.getEmail(),
	                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
	        
	        return userRepository.save(user);
	    } catch (Exception e) {
	        // Handle the exception, log it, or rethrow a more specific exception
	        throw new RuntimeException("Error while saving the user", e);
	    }
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }
	
}
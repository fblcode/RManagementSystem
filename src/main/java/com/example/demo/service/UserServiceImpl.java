package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
	    if (isEmailAlreadyRegistered(registrationDto.getEmail())) {
	        throw new RuntimeException("Email is already registered");
	    }

	    try {
	        // Only allow ROLE_USER or ROLE_ADMIN
	        if (!isValidRole(registrationDto.getRoleName())) {
	            throw new RuntimeException("Invalid role specified");
	        }

	        List<Role> roles = Arrays.asList(new Role(registrationDto.getRoleName()));

	        User user = new User(registrationDto.getFirstName(),
	                registrationDto.getLastName(), registrationDto.getEmail(),
	                passwordEncoder.encode(registrationDto.getPassword()), roles);

	        return userRepository.save(user);
	    } catch (Exception e) {
	        throw new RuntimeException("Error while saving the user", e);
	    }
	}

	private boolean isValidRole(String roleName) {
	    // Implement logic to check if the provided role is either ROLE_USER or ROLE_ADMIN
	    return roleName.equals("ROLE_USER") || roleName.equals("ROLE_ADMIN");
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
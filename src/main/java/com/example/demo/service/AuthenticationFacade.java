package com.example.demo.service;

import org.springframework.security.core.Authentication;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getAuthenticatedUser();
}

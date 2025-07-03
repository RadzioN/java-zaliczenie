package com.napora.napora.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.napora.napora.model.User;
import com.napora.napora.web.dto.UserRegistrationDto;


public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}

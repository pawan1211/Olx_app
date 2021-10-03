package com.spring.olxlogin.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;

import com.spring.olxlogin.dto.Users;
import com.spring.olxlogin.entity.UserEntity;

public interface UserService {

	Users userRegis(Users user);
	boolean logoutUser(String authToken);
	ResponseEntity<?> findByUserName(String username);

	
}

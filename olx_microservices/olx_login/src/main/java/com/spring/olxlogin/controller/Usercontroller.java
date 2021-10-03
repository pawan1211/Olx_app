package com.spring.olxlogin.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import com.spring.olxadvertise.exception.InvalidAdvertiseIdException;
import com.spring.olxlogin.dto.Users;
import com.spring.olxlogin.entity.UserEntity;
import com.spring.olxlogin.exception.InvalidAuthorizationTokenException;
import com.spring.olxlogin.exception.InvalidloginException;
import com.spring.olxlogin.service.UserService;
import com.spring.olxlogin.service.UserServiceDetailsImple;
import com.spring.springsecurity.dt.AuthenticationRequest;
import com.spring.springsecurity.util.Jwtutil;

import io.swagger.annotations.ApiOperation;

@RestController
public class Usercontroller {
	
	@Autowired
	 UserService userService;

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	Jwtutil jwtUtils;
	
	@ExceptionHandler(InvalidAuthorizationTokenException.class)
	public ResponseEntity<String> handleInvalidCategoryIdException(InvalidAuthorizationTokenException invalidAuthorizationTokenException) {
		return new ResponseEntity<String>(invalidAuthorizationTokenException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/user/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authrequest) {
		try {
			
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
																													
		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException(authrequest.getUsername());
		}

		String jwtToken = jwtUtils.generateToken(authrequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);

	}
	
	@GetMapping(value="/token/validate") 
	public ResponseEntity<Boolean>isTokenValid(@RequestHeader("Authorization") String JwtToken)
	{
		  JwtToken = JwtToken.substring(7); 
		  String username = jwtUtils.extractUsername(JwtToken);
		  UserDetails userdetails = userDetailsService.loadUserByUsername(username);
		  boolean isValid = jwtUtils.validateToken(JwtToken, userdetails); 
		  if(isValid)
		  { 
			  return new ResponseEntity<Boolean>(true,HttpStatus.OK); 
		  }
		  else
		  {
			  return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST); 
		  }
	}


	
	@PostMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
								  produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Users> userRegistration(@RequestBody Users user) {
		
		Users user1 =userService.userRegistration(user);
		return new ResponseEntity<Users>(user1, HttpStatus.OK);

	}

	
	@GetMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authToken) {
		
		authToken = authToken.substring(7); 
		String username = jwtUtils.extractUsername(authToken);
		if(username.isEmpty())
		{
			throw new InvalidloginException(""+authToken);
		}
		return userService.findByUserName(username);

	}

	
	@DeleteMapping(value = "/user/logout", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> logoutUser(@RequestHeader("auth-token") String authToken) {
		
			return new ResponseEntity(true, HttpStatus.OK);
		

	}	
	
	}

package com.spring.olxlogin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.spring.olxlogin.dto.Users;
import com.spring.olxlogin.entity.UserEntity;
import com.spring.olxlogin.exception.InvalidloginException;
import com.spring.olxlogin.repo.UserRepository;
import com.spring.olxlogin.util.Jwtutil;


@Service
public class UserServiceImple implements UserService{

	@Autowired
	UserRepository userrepository;
	
	@Autowired
	UserServiceDetailsImple userServiceDetailsImple;
	
	@Override
	public Users userRegis(Users user) {
		UserEntity userEntity = new UserEntity(user.getFirstName(),user.getLastName(),user.getUserName(),user.getPassword(),user.getEmail(),user.getPhone());
		userEntity = userrepository.save(userEntity);
		user = new Users(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getUserName(),userEntity.getPassword(),userEntity.getEmail(),userEntity.getPhone());
		return user;
	}

	@Override
	public boolean logoutUser(String authToken) {
		
		return false;
	}

	@Override
	public ResponseEntity<?> findByUserName(String username) {
	
		List<UserEntity> userEntityList = userrepository.findByUserName(username);
		List<Users> userList = new ArrayList();
		for( UserEntity userEntity:userEntityList)
		{
			Users user = new Users(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getUserName(),userEntity.getPassword(),userEntity.getEmail(),userEntity.getPhone());
			userList.add(user);
		}
		return ResponseEntity.ok(userList);
	}

}

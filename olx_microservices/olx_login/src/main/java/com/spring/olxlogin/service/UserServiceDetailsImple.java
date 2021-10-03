package com.spring.olxlogin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.olxlogin.dto.Users;
import com.spring.olxlogin.repo.UserRepository;
import com.spring.springsecurity.entity.UserEntity;
import com.spring.springsecurity.rep.Userrepo;


@Service
public class UserServiceDetailsImple  implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<com.spring.olxlogin.entity.UserEntity> userEntityList = userRepo.findByUserName(username);
		if(userEntityList==null || userEntityList.size()==0) { 
			throw new UsernameNotFoundException(username);
		}
		com.spring.olxlogin.entity.UserEntity userEntity = userEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<>();
		Users user = new Users(userEntity.getUserName(),userEntity.getPassword(),
				authorities);
		return user;
	}

	
}

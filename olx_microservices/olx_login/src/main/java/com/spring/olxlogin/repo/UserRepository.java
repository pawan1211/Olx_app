package com.spring.olxlogin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.olxlogin.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	List<UserEntity> findByUserName(String username);
}

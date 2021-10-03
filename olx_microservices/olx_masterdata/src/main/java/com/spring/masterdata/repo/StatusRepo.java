package com.spring.masterdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.masterdata.entity.StatusEntity;

public interface StatusRepo extends JpaRepository<StatusEntity, Integer>{

}

package com.spring.olxadvertise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spring.olxadvertise.entity.AdvertiseEntity;


public interface AdvertiseRepository  extends JpaRepository<AdvertiseEntity, Integer>,
 
AdvertiseCustomRepository,JpaSpecificationExecutor<AdvertiseEntity>{
	
	
	    List<AdvertiseEntity> search(String searchtext);
		 List<AdvertiseEntity> findByUserName(String username);
		 List<AdvertiseEntity> findBySearch();
	
}


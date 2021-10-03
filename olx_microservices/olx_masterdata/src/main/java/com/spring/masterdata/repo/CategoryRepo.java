package com.spring.masterdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.masterdata.entity.CategoryEntity;



public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{

}

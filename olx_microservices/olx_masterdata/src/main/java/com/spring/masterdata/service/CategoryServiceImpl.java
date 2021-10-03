package com.spring.masterdata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.masterdata.entity.CategoryEntity;
import com.spring.masterdata.exception.InvalidCategoryIdException;
import com.spring.masterdata.repo.CategoryRepo;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public List<Category> getAllCategory() {
		List<CategoryEntity> categoryEntityList = categoryRepo.findAll();
		List<Category> categoryList = new ArrayList(); 
		for(CategoryEntity categoryEntity:categoryEntityList)
		{
			Category category = new Category(categoryEntity.getId(),categoryEntity.getCategory());
			categoryList.add(category);
		}
		return categoryList;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Optional<CategoryEntity> opEntity = categoryRepo.findById(categoryId);
		if(opEntity.isPresent())
		{
			CategoryEntity categoryEntity = opEntity.get();
			java.util.Locale.Category category = new Category(categoryEntity.getId(),categoryEntity.getCategory());
			return category;
		}
		 throw new InvalidCategoryIdException(""+categoryId);
	}

}

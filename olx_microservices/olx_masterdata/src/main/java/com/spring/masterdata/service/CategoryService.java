package com.spring.masterdata.service;

import java.util.List;
import java.util.Locale.Category;



public interface CategoryService {
	
	List<Category>getAllCategory();

	Category getCategoryById(int Id);

}

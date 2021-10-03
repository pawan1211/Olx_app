package com.spring.olxadvertise.service;

import java.util.List;
import java.util.Locale.Category;

import ch.qos.logback.core.status.Status;

public interface CategoryServiceDelegate {
	
	List<Category> getCategoryList();

	Category getCategory(String category);

	Status getStatus(String status);

}

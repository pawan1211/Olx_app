package com.spring.olxadvertise.service;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.olxadvertise.exception.InvalidCategoryException;
import com.spring.olxadvertise.exception.InvalidStatusException;

import ch.qos.logback.core.status.Status;

@Service
public class CategoryServiceDelegateImpl implements CategoryServiceDelegate{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Category> getCategoryList() {
		
		ResponseEntity<List> categoryResponse = restTemplate.exchange("http://localhost:6000/advertise/category",
				HttpMethod.GET,
				null, List.class);
							
		List<Category> categoryList = categoryResponse.getBody();
		return categoryList;
	}
	
	@Override
	public Category getCategory(String category) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
		ResponseEntity<Category> result = restTemplate.exchange("http://localhost:6000/advertise/category"+category,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<Category>() {});
		
		
		
		return result.getBody();
	
		
		}
		catch(Exception e)
		{
			throw new InvalidCategoryException(""+category);
		}
	}

	@Override
	public Status getStatus(String status) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
		ResponseEntity<Status> result1 = restTemplate.exchange("http://localhost:6000/advertise/status"+status,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<Status>() {});
		
		
		
		return result1.getBody();
	
		
		}
		catch(Exception e)
		{
			throw new InvalidStatusException(""+status);
		}
	}
}

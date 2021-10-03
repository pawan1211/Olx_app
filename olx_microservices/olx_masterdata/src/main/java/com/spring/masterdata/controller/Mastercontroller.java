package com.spring.masterdata.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.masterdata.dto.Status;
import com.spring.masterdata.exception.InvalidCategoryIdException;
import com.spring.masterdata.service.CategoryService;
import com.spring.masterdata.service.StatusService;

import io.swagger.annotations.ApiOperation;


@RestController

public class Mastercontroller {


	
	@Autowired
	StatusService statusService;
	@Autowired
	CategoryService categoryService;
	
	@ExceptionHandler(InvalidCategoryIdException.class)
	public ResponseEntity<String> handleInvalidCategoryIdException(InvalidCategoryIdException invalidCategoryIdException) {
		return new ResponseEntity<String>("Local Exception :"+invalidCategoryIdException.toString(), HttpStatus.BAD_REQUEST);
	}
	

	
	@GetMapping(value="/advertise/category/{id}",produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE} )
	
	public Category getCategoryById(@PathVariable("id") int categoryId)
	{
		
		return categoryService.getCategoryById(categoryId);
	}

	@GetMapping(value="/advertise/status",produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public java.util.List<Status>getAllStatus()
	{
		return statusService.getAllStatus();
		
	}
	
	@GetMapping(value="/advertise/category",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE })
	public java.util.List<Category>getAllCategory()
	{
		return categoryService.getAllCategory();
	}
	

}

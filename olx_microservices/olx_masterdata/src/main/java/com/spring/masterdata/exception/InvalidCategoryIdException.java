package com.spring.masterdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdException extends RuntimeException{

	private String meg;

	public InvalidCategoryIdException()
	{
		
	}
	
	public InvalidCategoryIdException(String meg)
	{
		this.meg = meg;
	}
	
	@Override
	public String toString() {
		return "Invalid Category Id: " +this.meg;
	}
}
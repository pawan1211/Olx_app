package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidStatusException extends RuntimeException{

	private String msg;
	
	public InvalidStatusException()
	{
		
	}
	
	public InvalidStatusException(String msg)
	{
		this.msg=msg;
	}

	@Override
	public String toString() {
		return "Invalid Status Exception :"+msg;
	}
	
	
}

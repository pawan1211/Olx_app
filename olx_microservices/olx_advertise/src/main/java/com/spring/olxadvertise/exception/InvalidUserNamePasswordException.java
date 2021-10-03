package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidUserNamePasswordException extends RuntimeException{
	
	private String msg;
	public InvalidUserNamePasswordException()
	{
		
	}
	
	public InvalidUserNamePasswordException(String msg)
	{
		this.msg=msg;
	}
	
	@Override
	public String toString() {
		return "Invalid UserName and Password: " +this.msg;
		
	}
}

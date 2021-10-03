package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidonDateSearchException extends RuntimeException {

	
       private String meg;
	
	public InvalidonDateSearchException()
	{
		
	}
	public InvalidonDateSearchException(String meg)
	{
		this.meg=meg;
	}
	@Override
	public String toString() {
		return "Invalid onDate Search Exception : "+meg;
	}
	
	
}

package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidtoDateSearchException extends RuntimeException{

	private String meg;
	
	public InvalidtoDateSearchException()
	{
		
	}
	public InvalidtoDateSearchException(String meg)
	{
		this.meg=meg;
	}
	@Override
	public String toString() {
		return "Invalid toDate Search Exception:"+meg;
	}
	
	
}

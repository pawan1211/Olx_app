package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidfromDateSearchException extends RuntimeException{
	
	
    private String meg;
	
	public InvalidfromDateSearchException()
	{
		
	}
	
	public InvalidfromDateSearchException(String meg)
	{
		this.meg=meg;
	}

	@Override
	public String toString() {
		return "Invalid fromDate Search Exception:"+meg;
	}
	

}
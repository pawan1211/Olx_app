package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseIdException extends RuntimeException {

	private String meg;
	
	public InvalidAdvertiseIdException()
	{
		this.meg="";
	}
	
	public InvalidAdvertiseIdException(String meg)
	{
		this.meg = meg;
	}

	@Override
	public String toString() {
		return "Invalid Advertise Id " +this.meg;
		
	}
	
	
}

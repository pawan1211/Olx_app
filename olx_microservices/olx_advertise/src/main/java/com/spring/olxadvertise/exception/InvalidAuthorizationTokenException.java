package com.spring.olxadvertise.exception;

public class InvalidAuthorizationTokenException extends RuntimeException{
	
	private String meg;
	public InvalidAuthorizationTokenException()
	{

		
	}
	public InvalidAuthorizationTokenException(String mg)
	{
		this.meg=meg;
		
	}
	
	public String toString()
	{
		return "Invalid Authorization Token"+this.meg;
	}
}

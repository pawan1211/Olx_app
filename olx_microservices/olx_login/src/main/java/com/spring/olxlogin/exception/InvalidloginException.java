package com.spring.olxlogin.exception;

public class InvalidloginException extends RuntimeException{
	
	private String message;
	
	public InvalidloginException ()
	{
		this.message="";
	}

	public InvalidloginException(String messages)
	{
		this.message=message;
	}
	
	
	@Override
	public String toString() {
		return "InvalidloginException [message=" + message + "]";
	}
}
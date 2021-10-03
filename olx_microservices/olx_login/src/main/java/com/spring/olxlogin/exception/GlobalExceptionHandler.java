package com.spring.olxlogin.exception;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value= {InvalidloginException.class})

	public ResponseEntity<Object>handlelogin(RuntimeException exception,WebRequest request)
	{
		return handleExceptionInternal
		(exception,exception.toString(),new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
	}
	
	
	
	@ExceptionHandler(value= {InvalidAuthorizationTokenException.class})
	public ResponseEntity<Object> handleInvalidAuthorizationToken(RuntimeException exception, WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}


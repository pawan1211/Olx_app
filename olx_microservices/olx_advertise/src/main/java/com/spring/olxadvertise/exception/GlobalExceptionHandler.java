package com.spring.olxadvertise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleAdvertiseId(RuntimeException exception, WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	

	@ExceptionHandler(value= {InvalidAuthorizationTokenException.class})
	public ResponseEntity<Object> handleAuthorization(RuntimeException exception, WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	

	@ExceptionHandler(value= {InvalidCategoryException.class})
	public ResponseEntity<Object> InvalidCategException(RuntimeException exception, WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	  @ExceptionHandler(value= {InvalidfromDateSearchException.class}) 
public ResponseEntity<Object> fromDate(RuntimeException exception ,WebRequest request) 
	  { 
		  return handleExceptionInternal(exception,exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	  }
	  
	  @ExceptionHandler(value= {InvalidtoDateSearchException.class}) 
public ResponseEntity<Object> toDate(RuntimeException exception ,WebRequest request) 
	  { 
		  return handleExceptionInternal(exception,exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	  }
	  

		@ExceptionHandler(value= {InvalidStatusException.class})
		public ResponseEntity<Object> InvalidStatException(RuntimeException exception, WebRequest request) {
			return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		}
		
		
	  @ExceptionHandler(value= {InvalidonDateSearchException.class}) 
public ResponseEntity<Object> onDate(RuntimeException exception ,WebRequest request) 
	  { 
		  return handleExceptionInternal(exception,exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	  }
	 
	  

		@ExceptionHandler(value= {InvalidUserNamePasswordException.class})
		public ResponseEntity<Object> InvalidUserNamePasswException(RuntimeException exception, WebRequest request) {
			return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		}
		
}


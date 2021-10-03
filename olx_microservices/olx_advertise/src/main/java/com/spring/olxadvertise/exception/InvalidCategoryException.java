package com.spring.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public class InvalidCategoryException extends RuntimeException{

		private String msg;
	         
		
		public InvalidCategoryException()
		{
			
		}
		public InvalidCategoryException(String msg)
		{
			this.msg=msg;
		}
		@Override
		public String toString() {
			return "Invalid Category Exception :"+msg;
		}
		
		
	}


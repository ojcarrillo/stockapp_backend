package com.stockapp.stockapp.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RestControllerAdvice
public class ApplicationExceptionHandler {

	//@ExceptionHandler
	ResponseEntity handle(Exception e) {
		System.out.println(e.getCause().getCause().getLocalizedMessage());
		
		return new ResponseEntity("Some message", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}

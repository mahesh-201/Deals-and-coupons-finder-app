package com.casestudy.profilemanagementmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CatalogExceptionHandler {

	@ExceptionHandler(value= {CatalogRequestException.class})
	public ResponseEntity<Object> handleProfileRequestException(CatalogRequestException e){
		
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
	    CatalogException apiException=new CatalogException(e.getMessage());
		return new ResponseEntity<Object>(apiException,badRequest);
	}
}

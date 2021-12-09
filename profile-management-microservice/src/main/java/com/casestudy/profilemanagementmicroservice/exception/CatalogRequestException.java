package com.casestudy.profilemanagementmicroservice.exception;

public class CatalogRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CatalogRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public CatalogRequestException(String message) {
		super(message);
	}

}

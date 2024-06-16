package com.on4june.rating_service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6288181081730296195L;

	public ResourceNotFoundException() {
		super("Resource not found on server !!");
	}

	public ResourceNotFoundException(String s) {
		super(s);
	}
	
}

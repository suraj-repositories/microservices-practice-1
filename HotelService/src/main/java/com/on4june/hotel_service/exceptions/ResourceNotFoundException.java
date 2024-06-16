package com.on4june.hotel_service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1824407946699726599L;

	public ResourceNotFoundException(){
		super("Resource not found !!");
	}
	
	public ResourceNotFoundException(String s) {
		super(s);
	}
}

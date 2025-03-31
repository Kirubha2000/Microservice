package com.kirubha.userservice.exception;

public class ResourceNotCreatedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotCreatedException(String message) {
		super(message);
	}

}

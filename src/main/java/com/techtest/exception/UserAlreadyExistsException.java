package com.techtest.exception;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
	public UserAlreadyExistsException() {
		//EMPTY CONSTRUCTOR
	}
}

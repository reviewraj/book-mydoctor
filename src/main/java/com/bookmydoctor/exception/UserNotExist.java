package com.bookmydoctor.exception;

public class UserNotExist extends RuntimeException {
	
private String message;

public UserNotExist(String message) {
	super(message);
	this.message = message;
}

}

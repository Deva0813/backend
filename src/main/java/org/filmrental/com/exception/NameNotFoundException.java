package org.filmrental.com.exception;

public class NameNotFoundException extends RuntimeException{
	public NameNotFoundException(String message) {
		super(message);
	}
}

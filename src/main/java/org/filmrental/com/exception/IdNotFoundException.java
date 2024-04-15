package org.filmrental.com.exception;

public class IdNotFoundException extends RuntimeException  {
	public IdNotFoundException(String message) {
		super(message);
	}
}

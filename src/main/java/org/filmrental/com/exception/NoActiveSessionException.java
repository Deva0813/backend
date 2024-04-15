package org.filmrental.com.exception;

public class NoActiveSessionException extends RuntimeException {
	public NoActiveSessionException(String message) {
		super(message);
	}
}
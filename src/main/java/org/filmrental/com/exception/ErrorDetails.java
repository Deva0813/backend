package org.filmrental.com.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorDetails {
	private LocalDateTime errorDateTime = LocalDateTime.now();
	private String message;
	public LocalDateTime getErrorDateTime() {
		return errorDateTime;
	}
	public void setErrorDateTime(LocalDateTime errorDateTime) {
		this.errorDateTime = errorDateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorDetails(LocalDateTime errorDateTime, String message) {
		super();
		this.errorDateTime = errorDateTime;
		this.message = message;
	}
	public ErrorDetails() {
		super();
	}
	
	
}

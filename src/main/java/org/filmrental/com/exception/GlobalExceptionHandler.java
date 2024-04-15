package org.filmrental.com.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
		@ExceptionHandler(DataNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleException(DataNotFoundException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleException(IdNotFoundException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(UpdationErrorException.class)
		public ResponseEntity<ErrorDetails> handleException(UpdationErrorException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(InvalidRatingException.class)
		public ResponseEntity<ErrorDetails> handleException(InvalidRatingException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(InvalidCategoryException.class)
		public ResponseEntity<ErrorDetails> handleException(InvalidCategoryException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(InvalidLanguageException.class)
		public ResponseEntity<ErrorDetails> handleException(InvalidLanguageException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(PostException.class)
		public ResponseEntity<ErrorDetails> handleException(PostException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(NameNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleException(NameNotFoundException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(DeletionException.class)
		public ResponseEntity<ErrorDetails> handleException(DeletionException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(NegativeValueException.class)
	    public ResponseEntity<ErrorDetails> handleNegativeValueException(NegativeValueException ex, WebRequest req) {
	        ErrorDetails errorDetails = new ErrorDetails();
	        errorDetails.setErrorDateTime(LocalDateTime.now());
	        errorDetails.setMessage(ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
		
		@ExceptionHandler(InvalidAssignmentException.class)
		public ResponseEntity<ErrorDetails> handleException(InvalidAssignmentException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(NoActiveSessionException.class)
		public ResponseEntity<ErrorDetails> handleException(NoActiveSessionException exe,WebRequest req){
			ErrorDetails err=new ErrorDetails();
			err.setErrorDateTime(LocalDateTime.now());
			err.setMessage(exe.getMessage());
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
		
}

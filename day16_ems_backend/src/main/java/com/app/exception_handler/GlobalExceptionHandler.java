package com.app.exception_handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;

@RestControllerAdvice // To tell SC , following class is centralized exc handler ,
//it will work as  common advice to all rest controllers
public class GlobalExceptionHandler {
	// exc handling logic : repeatative logic
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("method arg invalid " + e);
		// API : List<FieldError> getFieldErrors()
		//convert list of field errs -->Map<Field name , Error mesg>
		Map<String, String> errMap=new HashMap<>();
		for(FieldError field : e.getFieldErrors())
			errMap.put(field.getField(), field.getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap); //sc 400 error
	}
	
	
	
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getLocalizedMessage()));
		}

		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Data exist add new "));
		}
		
}

package com.kirubha.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kirubha.userservice.dto.ValidationResponse;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationResponse> handleValidationErrors(MethodArgumentNotValidException ex){
		ValidationResponse validationResponse = new ValidationResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ValidationResponse> handleUnreadable(HttpMessageNotReadableException ex){
		ValidationResponse validationResponse = new ValidationResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.BAD_REQUEST);
		
	}
	
}
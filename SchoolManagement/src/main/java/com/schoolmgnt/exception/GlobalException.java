package com.schoolmgnt.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalException 
{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String,List<String>>> handelValidatioonError(MethodArgumentNotValidException e)
	{
		List<String> error = e .getBindingResult()
								.getFieldErrors( ).stream()
								.map(FieldError::getDefaultMessage)
								.collect(Collectors.toList());
		return  new ResponseEntity<>(getErrorMap(error),new HttpHeaders(),HttpStatus.BAD_REQUEST);

	}
	private Map<String,List<String>> getErrorMap(List<String> error) 
	{
		Map<String,List<String>>errorResponse=new HashMap<>();
		errorResponse.put("errors", error); 
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String,List<String>>> handelValidatioonError(NoSuchElementException ec)
	{
		List<String> errors =  /* .getBindingResult()
								 .getFieldErrors( ).stream()
								 .map(FieldError::getDefaultMessage)
								 .collect(Collectors.toList());*/
				Collections.singletonList(ec.getMessage());
		return  new ResponseEntity<>(getErrorMap(errors),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	private Map<String,List<String>> getErrorMap1(List<String> errors) 
	{
		Map<String,List<String>>errorResponse=new HashMap<>();
		errorResponse.put("errors", errors); 
		return errorResponse;
	}
	
	
		@SuppressWarnings("serial")
		@ResponseStatus(HttpStatus.NOT_FOUND)
		class ResourceNotFoundException extends RuntimeException 
		{
			public ResponseEntity<Map<String,List<String>>> handelValidatioonError(ResourceNotFoundException ec)
			{
				List<String> errors = 
										Collections
										.singletonList
										(ec.getMessage());
			return  new ResponseEntity<>(getErrorMap(errors),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		}


	
}


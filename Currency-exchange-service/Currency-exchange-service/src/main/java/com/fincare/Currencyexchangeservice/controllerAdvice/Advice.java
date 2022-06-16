package com.fincare.Currencyexchangeservice.controllerAdvice;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fincare.Currencyexchangeservice.exception.CustomException;





@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleException(CustomException customException)
	{
		return new ResponseEntity<String>(customException.getMessage(),HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		Map<String, String> error=new HashMap<>();
		
		List<ObjectError> list = ex.getBindingResult().getAllErrors();
		for (int i = 0; i < list.size(); i++) {
			String fieldName = ((FieldError) list.get(i)).getField();
			//System.out.println(fieldName);
			error.put(fieldName, list.get(i).getDefaultMessage());
		}
		error.put("status", status.toString());
		error.put("timestamp", new Date().toString());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	
}

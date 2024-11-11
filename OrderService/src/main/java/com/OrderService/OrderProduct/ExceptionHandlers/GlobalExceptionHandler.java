package com.OrderService.OrderProduct.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

		
	@ExceptionHandler(ServiceNotAvailableException.class)
	public ResponseEntity<OrderError> serviceDown(ServiceNotAvailableException exception){
		
		OrderError error = new OrderError();
		error.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
//		error.setHttpStatusCode((HttpStatus.SERVICE_UNAVAILABLE).());
		error.setDescription(exception.getLocalizedMessage());
		
		return new ResponseEntity<OrderError>(error,HttpStatus.SERVICE_UNAVAILABLE);
	}
}

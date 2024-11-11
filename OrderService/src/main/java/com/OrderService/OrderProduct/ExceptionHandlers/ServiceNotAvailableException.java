package com.OrderService.OrderProduct.ExceptionHandlers;

public class ServiceNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceNotAvailableException() {
		super();
	}

	public ServiceNotAvailableException(String msg) {
		super(msg);
	}

	
}

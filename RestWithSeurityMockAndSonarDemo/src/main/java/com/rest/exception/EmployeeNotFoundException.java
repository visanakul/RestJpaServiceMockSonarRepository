package com.rest.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
}

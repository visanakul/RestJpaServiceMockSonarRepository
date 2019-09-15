package com.rest.exception;
/**
 * 
 * @author VISHAL
 * Employee persistence exception
 */
public class EmployeePersistException extends RuntimeException {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public EmployeePersistException(String msg) {
		super(msg);
	}
}

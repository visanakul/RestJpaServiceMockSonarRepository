package com.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * @author VISHAL Global exception handler
 */
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * SL4J Logger
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handle any exception
	 * 
	 * @param ex
	 */
	@ExceptionHandler(Exception.class)
	public void generalExceptionHandler(Exception ex) {
		LOGGER.error("Exception : {}", ex.getMessage());
	}
}

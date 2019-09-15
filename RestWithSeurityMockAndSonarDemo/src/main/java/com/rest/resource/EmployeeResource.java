package com.rest.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.exception.GlobalExceptionHandler;
import com.rest.model.EmployeeModel;
import com.rest.service.IEmployeeService;

/**
 * Rest service for Employee Service
 * 
 * @author VISHAL
 *
 */
@RestController
public class EmployeeResource {
	/**
	 * SL4J Logger
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);

	/**
	 * Injecting EmployeeServiceImpl
	 */
	@Autowired(required = true)
	private IEmployeeService empService;

//	@GetMapping(value = "/", produces = { MediaType.TEXT_PLAIN_VALUE })
//	public String helloWorld() {
//		return "Hello World!!!";
//	}

	/**
	 * Request for all employees
	 * 
	 * @return
	 */
	@GetMapping(value = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<EmployeeModel> getEmployeeList() {
		LOGGER.debug("getEmployeeList start");

		List<EmployeeModel> employeeModels = empService.getAllEmployees();
		LOGGER.debug("Employee List : " + employeeModels);
		LOGGER.debug("getEmployeeList end");

		return employeeModels;
	}
}

package com.rest.service;

import java.util.List;
/**
 * Employee Service
 */
import com.rest.model.EmployeeModel;

public interface IEmployeeService {
	/**
	 * Find all employee
	 * 
	 * @return
	 */
	List<EmployeeModel> getAllEmployees();

	/**
	 * 
	 * @param employeeModel
	 * @return true if added else false
	 */
	Integer saveOrUpdateEmployee(EmployeeModel employeeModel);

	/**
	 * 
	 * @param employeeModel
	 * @return true if deleted else false
	 */
	boolean deleteEmployee(Integer id);

	/**
	 * 
	 * @param id
	 * @return employee model if exists else null
	 */
	EmployeeModel searchEmployee(Integer id);
}

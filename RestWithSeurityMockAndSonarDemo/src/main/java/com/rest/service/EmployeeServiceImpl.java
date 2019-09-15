package com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.EmployeeEntity;
import com.rest.exception.EmployeeNotFoundException;
import com.rest.exception.EmployeePersistException;
import com.rest.model.EmployeeModel;
import com.rest.repository.IEmployeeRepository;

/**
 * 
 * @author VISHAL Employee service
 */
@Service
public class EmployeeServiceImpl implements IEmployeeSerice {

	/**
	 * SL4J Logger
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private IEmployeeRepository empRepository;

	@Override
	public List<EmployeeModel> getAllEmployees() {
		LOGGER.info("getAllEmployees start");

		List<EmployeeEntity> employeeEntities = empRepository.findAll();
		LOGGER.debug("EmployeeEntities : {}", employeeEntities);
		List<EmployeeModel> employeeModels = new ArrayList<>(employeeEntities.size());

		employeeEntities.stream().forEach(entity -> {
			EmployeeModel model = new EmployeeModel();
			BeanUtils.copyProperties(entity, model);
			employeeModels.add(model);
		});
		LOGGER.debug("EmployeeModels : {}", employeeModels);
		LOGGER.info("getAllEmployees end");
		return employeeModels;
	}

	@Override
	public Integer saveOrUpdateEmployee(EmployeeModel employeeModel) {
		LOGGER.info("saveEmployee start");
		LOGGER.debug("EmployeeModel : {}", employeeModel);
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeModel, employeeEntity);
		LOGGER.debug("EmployeeEntity copied : {}", employeeEntity);
		employeeEntity = empRepository.save(employeeEntity);
		LOGGER.debug("EmployeeEntity persisted : {}", employeeEntity);
		if (employeeEntity != null) {
			return employeeEntity.getId();
		}
		LOGGER.info("saveEmployee end");
		throw new EmployeePersistException("Unable to save employee");
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		LOGGER.info("deleteEmployee start");
		LOGGER.debug("Employee ID: {}", id);
		empRepository.deleteById(id);
		LOGGER.info("deleteEmployee end");
		return true;
	}

	@Override
	public EmployeeModel searchEmployee(Integer id) {
		LOGGER.info("searchEmployee start");
		LOGGER.debug("Employee ID: {}", id);
		Optional<EmployeeEntity> employeeEntityOptional = empRepository.findById(id);
		if (!employeeEntityOptional.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with id : " + id);
		}
		LOGGER.info("searchEmployee end");
		EmployeeEntity employeeEntity = employeeEntityOptional.get();
		LOGGER.debug("Employee entity: {}", employeeEntity);
		EmployeeModel employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employeeEntity, employeeModel);
		LOGGER.debug("Copied Employee model: {}", employeeModel);
		return employeeModel;
	}
}

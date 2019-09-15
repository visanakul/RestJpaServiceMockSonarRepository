package com.rest;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.entity.EmployeeEntity;
import com.rest.exception.EmployeeNotFoundException;
import com.rest.exception.EmployeePersistException;
import com.rest.model.EmployeeModel;
import com.rest.repository.IEmployeeRepository;
import com.rest.service.EmployeeServiceImpl;
import com.rest.service.IEmployeeSerice;

/**
 * 
 * @author VISHAL Test for Employee service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeServiceTest {

	@InjectMocks
	private IEmployeeSerice empService = new EmployeeServiceImpl();
	@Mock
	private IEmployeeRepository empRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test1ServiceNotNull() {
		assertNotNull(empRepository);
		assertNotNull(empService);
	}

	@Test
	public void test2SaveEmployeeSuccess() {
		EmployeeEntity employeeEntityToPersist = new EmployeeEntity(1, "emp1", 5_00_000D);
		EmployeeEntity employeeEntityPersisted = new EmployeeEntity(1, "emp1", 5_00_000D);

		EmployeeModel employeeModel = new EmployeeModel(1, "emp1", 5_00_000D);
		when(empRepository.save(employeeEntityToPersist)).thenReturn(employeeEntityPersisted);
		Integer id = empService.saveOrUpdateEmployee(employeeModel);
		assertEquals(1, id.intValue());
		verify(empRepository, times(1)).save(employeeEntityToPersist);
	}

	@Test(expected = EmployeePersistException.class)
	public void test3SaveEmployeeFail() {

		EmployeeModel employeeModel = new EmployeeModel(1, "emp1", 5_00_000D);
		when(empRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(null);
		Integer id = empService.saveOrUpdateEmployee(employeeModel);
		assertNull(id);
		verify(empRepository, times(1)).save(Mockito.any(EmployeeEntity.class));

	}

	@Test
	public void test4UpdateEmployeeSuccess() {
		EmployeeEntity employeeEntityToUpdate = new EmployeeEntity(1, "emp1", 2_00_000D);
		EmployeeEntity employeeEntityUpdated = new EmployeeEntity(1, "emp1New", 5_00_000D);

		when(empRepository.save(employeeEntityToUpdate)).thenReturn(employeeEntityUpdated);

		EmployeeModel employeeModelToUpdate = new EmployeeModel(1, "emp1", 2_00_000D);

		Integer id = empService.saveOrUpdateEmployee(employeeModelToUpdate);
		assertEquals(1, id.intValue());

		verify(empRepository, times(1)).save(employeeEntityToUpdate);
	}

	@Test(expected = EmployeePersistException.class)
	public void test5UpdateEmployeeFail() {
		when(empRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(null);
		EmployeeModel employeeModelToUpdate = new EmployeeModel(1, "emp1", 5_00_000D);
		Integer id = empService.saveOrUpdateEmployee(employeeModelToUpdate);
		assertNull(id);
	}

	@Test
	public void test6DeleteEmployee() {
		Integer id = 1;
		empService.deleteEmployee(id);
		verify(empRepository, times(1)).deleteById(id);
	}

	@Test
	public void test7EmployeeSearchSuccess() {
		Integer id = 1;
		when(empRepository.findById(id)).thenReturn(Optional.of(new EmployeeEntity(1, "emp1", 5_00_000D)));
		EmployeeModel employeeModel = empService.searchEmployee(id);
		assertNotNull(employeeModel);
		assertEquals(1, employeeModel.getId().intValue());
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void test8EmployeeSearchFail() {
		Integer id = 1;
		when(empRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());
		EmployeeModel employeeModel = empService.searchEmployee(id);
		assertNull(employeeModel);
	}

	@Test
	public void test8GetAllEmployees() {
		List<EmployeeEntity> employeeEntities = Arrays.asList(new EmployeeEntity(1, "emp1", 5_00_000D),
				new EmployeeEntity(2, "emp2", 4_00_000D));
		when(empRepository.findAll()).thenReturn(employeeEntities);

		List<EmployeeModel> employeeModels = empService.getAllEmployees();
		assertNotNull(employeeModels);
		assertEquals(2, employeeModels.size());
		verify(empRepository, times(1)).findAll();
	}
}

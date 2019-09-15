package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.model.EmployeeModel;
import com.rest.resource.EmployeeResource;
import com.rest.service.IEmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRestControllerTest {
	@InjectMocks
	private EmployeeResource empResource;
	@Mock
	private IEmployeeService empService;

	@Test
	public void test1GetAllEmployees() {

		when(empService.getAllEmployees()).thenReturn(
				Arrays.asList(new EmployeeModel(1, "emp1", 4_00_000D), new EmployeeModel(2, "emp2", 5_00_000D)));
		List<EmployeeModel> employeeModels = empResource.getEmployeeList();
		assertNotNull(employeeModels);
		assertEquals(2, employeeModels.size());
	}

}

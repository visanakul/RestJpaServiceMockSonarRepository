package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.entity.EmployeeEntity;
import com.rest.exception.EmployeeNotFoundException;
import com.rest.repository.IEmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRepositoryTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryTest.class);
	@Mock
	private IEmployeeRepository empRepository;

	@Test
	public void test1RepositoryNotNull() {
		assertNotNull(empRepository);
	}

	@Test
	public void test2Size() {
		when(empRepository.count()).thenReturn(5L);

		long total_records = empRepository.count();
		LOGGER.debug("Total records {}", total_records);

		assertEquals(5L, total_records);
	}

	@Test
	public void test3AddEmployee() {
		EmployeeEntity entityToPersist = new EmployeeEntity();
		entityToPersist.setName("emp1");
		entityToPersist.setSalary(2_00_000D);

		EmployeeEntity entityPersisted = new EmployeeEntity();
		entityPersisted.setId(1);
		entityPersisted.setName("emp1");
		entityPersisted.setSalary(2_00_000D);

		when(empRepository.save(entityToPersist)).thenReturn(entityPersisted);

		EmployeeEntity persisted = empRepository.save(entityToPersist);
		assertNotNull(entityPersisted);
		assertEquals(1, persisted.getId().intValue());
		assertEquals("emp1", persisted.getName());
	}

	@Test
	public void test4FindEmployeeSuccess() {
		EmployeeEntity empToSearch = new EmployeeEntity();
		empToSearch.setId(1);
		empToSearch.setName("emp1");
		empToSearch.setSalary(2_00_000D);

		when(empRepository.findById(1)).thenReturn(Optional.of(empToSearch));

		Optional<EmployeeEntity> empFoundOptional = empRepository.findById(1);
		assertTrue(empFoundOptional.isPresent());
		assertEquals(1, empFoundOptional.get().getId().intValue());
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void test4FindEmployeeFail() {
		when(empRepository.findById(100)).thenThrow(new EmployeeNotFoundException("Record not found"));

		Optional<EmployeeEntity> empFoundOptional = empRepository.findById(100);
		assertFalse(empFoundOptional.isPresent());
	}

	@Test
	public void test5FindAll() {
		List<EmployeeEntity> mockedEmpList = mock(ArrayList.class);
		when(mockedEmpList.size()).thenReturn(2);
		when(mockedEmpList.get(0)).thenReturn(new EmployeeEntity(1, "emp1", 2_00_000D));
		when(mockedEmpList.get(1)).thenReturn(new EmployeeEntity(2, "emp2", 3_00_000D));

		when(empRepository.findAll()).thenReturn(mockedEmpList);

		List<EmployeeEntity> allEmployees = empRepository.findAll();
		assertNotNull(allEmployees);
		assertEquals(2, allEmployees.size());
		assertNotNull(mockedEmpList.get(0));
		assertEquals(1, mockedEmpList.get(0).getId().intValue());
		assertNull(mockedEmpList.get(100));
	}

	@Test
	public void test6UpdateEmployee() {
		EmployeeEntity entityToUpdate = new EmployeeEntity();
		entityToUpdate.setId(1);
		entityToUpdate.setName("emp1New");
		entityToUpdate.setSalary(5_00_000D);

		EmployeeEntity entityUpdated = new EmployeeEntity();
		entityUpdated.setId(1);
		entityUpdated.setName("emp1New");
		entityUpdated.setSalary(5_00_000D);

		when(empRepository.save(entityToUpdate)).thenReturn(entityUpdated);

		EmployeeEntity updated = empRepository.save(entityToUpdate);
		assertNotNull(updated);
		assertEquals(entityToUpdate.getId(), updated.getId());
		assertEquals(entityToUpdate.getName(), updated.getName());
		assertEquals(entityToUpdate.getSalary(), updated.getSalary());
	}

	@Test
	public void test7DeleteEmployee() {
		empRepository.deleteById(1);
		verify(empRepository, times(1)).deleteById(1);
	}

}

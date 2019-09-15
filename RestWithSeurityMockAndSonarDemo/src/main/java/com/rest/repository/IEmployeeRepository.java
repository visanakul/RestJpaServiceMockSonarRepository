package com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.entity.EmployeeEntity;

/**
 * 
 * @author VISHAL Employee repository for table EMPLOYEE_MASTER
 */
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}

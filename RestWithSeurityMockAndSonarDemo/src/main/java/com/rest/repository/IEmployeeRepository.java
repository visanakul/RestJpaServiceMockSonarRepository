package com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.entity.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}

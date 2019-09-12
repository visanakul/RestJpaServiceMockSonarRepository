package com.rest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Entity
@Table(name = "EMPLOYEE_MASTER")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "id_gen", sequenceName = "emp_id_seq")
	private Integer id;
	private String name;
	private Double salary;
}

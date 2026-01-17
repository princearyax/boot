package com.arya.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arya.entity.Employee;


//JpaRepository<Entity, TypeOfId>
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	//magic methods: spring generates sql based on meth name
	
	//sql generated is "select* from employees WHERE name = ?"
	List<Employee> findByName(String name);
	
	//sql like: "SELECT * FROM employees WHERE salary > ?"
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	List<Employee> findByDepartmentId(Long deptId);
}

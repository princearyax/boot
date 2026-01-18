package com.arya.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
	
	private String name;
	private Double salary;
	
	@Column(name = "join_date")
	private LocalDate joinDate;
	
	//relation : many employees belong to one department
	@ManyToOne(fetch = FetchType.LAZY) //dont fetch until asked
	@JoinColumn(name = "Dept_id")
	private Department department;
	

	public Employee() {}
	public Employee(String name) {this.name = name;}
	
	//gettrs and setters
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) { //well need db logic
		this.joinDate = joinDate;
	}

	public Department getDepartment() {
		return department;
	}

	//used by Department class
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee [ " + name +" ]";
	}
	
	
}

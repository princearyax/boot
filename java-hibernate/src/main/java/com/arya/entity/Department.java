package com.arya.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//class must have a no arg constructor 
@Entity //tells that this class maps to a table, mark it as table
@Table(name = "departments") //optional if uses same name, here camel case works...
public class Department {
    @Id   //marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //defines how pk is generated
    // (identity: common, seq for oracle, uuid(new))
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String name;

    // relation : one department has many employees so
    // one to many, now fk will be contained by owner and have
    // joincolumn other has mapped by,
 // "mappedBy": I am lazy. Look at the 'department' field in Employee to find the FK.
    //orphan removal: if i remove a child from java list , delete it from the db
    //cascade:all: whatever happens to the parent(department) do it to all children(employees)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
    //collection object initialise to handle npe , null pointer exception, other like primitive 
    //wrapper, object, gets null okay as going to check. but here say null.add() so
    
    //default const. necessary for hiber to create using reflec
    public Department() {}
    public Department(String name) {this.name = name;}

	public Long getId() {
		return id;
	}

	//logic methods, not just set a list we need sync
	public void addEmployee(Employee employee) {
		employees.add(employee);
		employee.setDepartment(this);
	}
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
		employee.setDepartment(null);
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public String getName() {
		return name;
	}
	//no setId
	public void setName(String name) {
		this.name = name;
	}
    
}



// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.Properties;
    // public static void main(String[] args) throws FileNotFoundException, IOException {
    //     Properties props = new Properties();
    //     props.load(new FileInputStream("application.properties"));
    //     System.out.println(System.getProperty("user.dir"));
    //     System.out.println(props.getProperty("db.username"));
    // }

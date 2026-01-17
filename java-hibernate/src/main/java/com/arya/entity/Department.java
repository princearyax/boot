package com.arya.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity //tells that this class maps to a table, mark it as table
//class must have a no arg constructor 
@Table(name = "departments") //optional if uses same name, here camel case works...
public class Department {
    @Id   //marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //defines how pk is generated
    // (identity: common, seq for oracle, uuid(new))
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name")
    private String name;

    //relation : one department has many employees so
    // one to many, now fk will be contained by owner and have
    // joincolumn other has mapped by,
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

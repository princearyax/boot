package com.arya.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity //tells that this class maps to a table
@Table(name = "departments") //optional if uses same name, here camel case works...
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private long id;
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

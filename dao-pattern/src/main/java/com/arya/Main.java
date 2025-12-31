package com.arya;

import com.arya.dao.EmployeeDAO;
import com.arya.dao.EmployeeDAOImpl;
import com.arya.model.Employee;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAOImpl();

        //add
        // System.out.println("Adding...");
        // dao.addEmployee(new Employee(0, "Vikram", "DevOps", 90000));

        //read
        System.out.println("Reading...");
        for(Employee emp: dao.getAllEmployees()){
            System.out.println(emp);
        }
    }
}
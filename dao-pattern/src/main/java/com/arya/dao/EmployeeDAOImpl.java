package com.arya.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.arya.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

    //Ideally connection logic should go in separate 'DBConnectionUtil' class
    private static String URL = "jdbc:postgresql://localhost:5432/learn_jdbc";
    private static String USER;
    private static String PASSWORD;
    private static Properties props = new Properties();

    private Connection getConnection(){
        try {
            props.load(new FileInputStream("application.properties"));
            USER = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io error in daoImpl/load props");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("sql error in daoImpl/get connection");
        }
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, role, salary) VALUES(?,?,?)";
        try (Connection conn = getConnection();
        PreparedStatement pstmt =  conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getRole());
            pstmt.setDouble(1, employee.getSalary());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployeeById'");
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            while (rs.next()) {
                employees.add(
                    new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;

    }

    @Override
    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public void deleteEmployee(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

}

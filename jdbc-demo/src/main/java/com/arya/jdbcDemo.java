package com.arya;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcDemo {
    //use env vars
    private static final String URL = "jdbc:postgresql://localhost:5432/learn_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = ""; //meow use the one

    public static void main(String[] args) {
        System.out.println("Here's start");
        //1. Establishing the connection
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to postgres");
            }
            //first task: select query using PreparedStatement
            getEmployeeByRole(conn, "Developer");

            //next task : execute stored proc using callableStat

            // updateSalary(conn, 1, 100000);

            //verifyupdate
            getEmployeeByRole(conn, "Developer");

        } catch (SQLException e) {
            System.err.println("db con failed");
            e.printStackTrace();
        }
    }
    /**
     * USAGE: PreparedStatement
     * Why: Precompiles SQL and prevents SQL Injection attacks.
     * @throws SQLException 
     */
    private static void getEmployeeByRole(Connection conn, String role) throws SQLException{
        String query = "SELECT id, name, salary FROM employees WHERE role = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            //set the parameter
            pstmt.setString(1, role);
            //execute
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n---- Employees with role: " + role + " --- ");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");
                    System.out.printf("Id: %d, Name: %s, Salar: %.3f%n", id, name, salary);
                }
            }
        }
    }

    /**
     * USAGE: CallableStatement
     * Why: Used to execute stored procedures logic residing in the DB.
     * @throws SQLException 
     */
    private static void updateSalary(Connection conn, int empId, double amount) throws SQLException{
        // Postgres procedure syntax: call procedure_name(?, ?)
        String procedureCall = "CALL update_employee_salary(?, ?)";

        try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
            cstmt.setInt(1, empId);
            cstmt.setDouble(2, amount);

            cstmt.execute();
            System.out.println("\n success, salary updated for employee Id: " + empId);
        }
    }
}

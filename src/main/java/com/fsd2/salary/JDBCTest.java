package com.fsd2.salary;

public class JDBCTest {
    public static void main(String[] args) {
        try {
            EmployeeDAO.insertEmployee(
                "JDBC Test", 30000, 26, 2, 8
            );

            System.out.println("Inserted successfully.");
            System.out.println("Employee records:");

            EmployeeDAO.viewEmployees();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
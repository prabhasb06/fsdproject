package com.fsd2.salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    public static void insertEmployee(
            String name,
            double salary,
            int workingDays,
            int leaves,
            double hoursPerDay) throws Exception {

        String sql = """
                INSERT INTO employee_salary
                (employee_name, monthly_salary,
                 working_days, leaves, hours_per_day)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setInt(3, workingDays);
            ps.setInt(4, leaves);
            ps.setDouble(5, hoursPerDay);

            ps.executeUpdate();
        }
    }

    public static void viewEmployees() throws Exception {

        String sql = "SELECT * FROM employee_salary";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("employee_id") + " | " +
                    rs.getString("employee_name") + " | " +
                    rs.getDouble("monthly_salary")
                );
            }
        }
    }

    public static void searchEmployee(int id)
            throws Exception {

        String sql =
            "SELECT * FROM employee_salary WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("employee_id") + " | " +
                        rs.getString("employee_name") + " | " +
                        rs.getDouble("monthly_salary")
                    );
                }
            }
        }
    }

    public static void updateEmployee(
            int id,
            String name,
            double salary,
            int workingDays,
            int leaves,
            double hoursPerDay) throws Exception {

        String sql = """
                UPDATE employee_salary
                SET employee_name = ?,
                    monthly_salary = ?,
                    working_days = ?,
                    leaves = ?,
                    hours_per_day = ?
                WHERE employee_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setInt(3, workingDays);
            ps.setInt(4, leaves);
            ps.setDouble(5, hoursPerDay);
            ps.setInt(6, id);

            ps.executeUpdate();
        }
    }

    public static void deleteEmployee(int id)
            throws Exception {

        String sql =
            "DELETE FROM employee_salary WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
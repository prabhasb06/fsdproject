package com.fsd2.salary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/fsd2_salary"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=UTC";
        String user = System.getenv().getOrDefault("FSD2_DB_USER", "root");
        String password = System.getenv("FSD2_DB_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Set the FSD2_DB_PASSWORD environment variable before connecting.");
        }
        return DriverManager.getConnection(url, user, password);
    }
}

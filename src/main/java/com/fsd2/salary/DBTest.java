package com.fsd2.salary;

import java.sql.Connection;

public class DBTest {

    public static void main(String[] args) {

        try {
            Connection connection =
                    DBConnection.getConnection();

            System.out.println(
                    "MySQL connected successfully!"
            );

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
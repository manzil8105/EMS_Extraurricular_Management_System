package com.example.projectmain;

import java.sql.*;

public class DB {
    public static Statement conn() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://buao2mvep5jvwqhexgvz-mysql.services.clever-cloud.com:3306/buao2mvep5jvwqhexgvz",
                "un3qv8gp39vnbmmw",
                "4sudsBiVeHXYfXS8nlph").createStatement();
    }
}

package com.example.projectmain;

import java.sql.*;

public class DB {
    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/excms",
                "root",
                "12345678");
    }
}

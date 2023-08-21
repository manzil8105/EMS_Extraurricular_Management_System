package com.example.projectmain;

import java.sql.*;

public class DB {
    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ExCMS",
                "root",
                "12345678");
    }
}

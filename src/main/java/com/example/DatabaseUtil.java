
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String URL = "jdbc:oracle:thin:@your_database_url:port:service_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection;

    public static Connection connect() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static int getRowCount(String tableName) throws Exception {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        rs.next();
        return rs.getInt(1);
    }
}

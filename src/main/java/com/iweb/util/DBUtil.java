package com.iweb.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午9:49
 */
public class DBUtil {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "a12345";
    private final static String URL = "jdbc:mysql://localhost:3306/user_db?character=utf8";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

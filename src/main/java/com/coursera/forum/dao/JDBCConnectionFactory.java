package com.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.coursera.forum.exception.ApplicationException;
import com.coursera.forum.exception.DataAccessException;

public class JDBCConnectionFactory {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application");
    
    private static final String DRIVER_CLASS = BUNDLE.getString("jdbc.driverClass");
    private static final String URL = BUNDLE.getString("jdbc.url");
    private static final String USERNAME = BUNDLE.getString("jdbc.username");
    private static final String PASSWORD = BUNDLE.getString("jdbc.password");
    
    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new ApplicationException("Erro ao carregar driver: " + e.getMessage(), e);
        }
    }
    
    private JDBCConnectionFactory() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao estabelecer conexão: " + e.getMessage(), e);
        }
    }
}
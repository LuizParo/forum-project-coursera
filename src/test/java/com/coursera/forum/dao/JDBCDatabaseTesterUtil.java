package com.coursera.forum.dao;

import java.util.ResourceBundle;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

public class JDBCDatabaseTesterUtil {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application");
    
    private static final String DRIVER_CLASS = BUNDLE.getString("jdbc.driverClass");
    private static final String URL = BUNDLE.getString("jdbc.url");
    private static final String USERNAME = BUNDLE.getString("jdbc.username");
    private static final String PASSWORD = BUNDLE.getString("jdbc.password");
    
    private JDBCDatabaseTesterUtil() {}

    public static IDatabaseTester getJDBCDatabaseTester(String xmlLocation) {
        try {
            JdbcDatabaseTester jdt = new JdbcDatabaseTester(DRIVER_CLASS, URL, USERNAME, PASSWORD);
            jdt.setDataSet(getDataset(xmlLocation));
            jdt.onSetup();
            
            return jdt;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao estabelecer conexão com JDBC Database Tester: " + e.getMessage(), e);
        }
    }
    
    public static IDataSet getDataset(String xmlLocation) {
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        return loader.load(xmlLocation);
    }
}
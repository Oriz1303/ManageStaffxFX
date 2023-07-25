package com.example.managestaff.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnect {
    public static Connection getConnection(Properties properties) throws SQLException, Exception {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("DB_USERNAME"),
                properties.getProperty("DB_PASSWORD"));
    }

    public Properties dbConfig() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/config/DBConfig.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

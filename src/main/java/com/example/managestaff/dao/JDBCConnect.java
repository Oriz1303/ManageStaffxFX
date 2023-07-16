package com.example.managestaff.dao;

import com.example.managestaff.config.IDBConfig;

import java.sql.*;

public class JDBCConnect {
    public boolean login(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND password = ?;";
        try (Connection connect = DriverManager.getConnection(IDBConfig.DB_URL, IDBConfig.USERNAME, IDBConfig.PASSWORD);
             PreparedStatement ps = connect.prepareStatement(query);
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

package com.example.managestaff.model.repository;

import com.example.managestaff.model.dao.JDBCConnect;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// mai làm trước cái nút signout rồi up lên để tôi lấy về nha. out ra màn hình đăng nhập.
public class StaffModel {
    public boolean getOne(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND password = ?;";
        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(query);
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}


//
//    public static Connection getConnection(Properties properties) throws SQLException {
//        String dbUrl = properties.getProperty("DB_URL");
//        String dbUsername = properties.getProperty("DB_USERNAME");
//        String dbPassword = properties.getProperty("DB_PASSWORD");
//        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//    }
//
//    public static void executeQuery(Properties properties, String query) {
//        try (Connection connect = getConnection(properties);
//             PreparedStatement ps = connect.prepareStatement(query)
//        ) {
//            // Execute your query or do something with the prepared statement here
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }







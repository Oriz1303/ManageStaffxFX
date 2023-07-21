package com.example.managestaff.model.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
// mai làm trước cái nút signout rồi up lên để tôi lấy về nha. out ra màn hình đăng nhập.
public class JDBCConnect {
    public boolean login(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND password = ?;";
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getResourceAsStream("/config/DBConfig.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (Connection connect = DriverManager.getConnection(properties.getProperty("DB_URL"),
                properties.getProperty("DB_USERNAME"), properties.getProperty("DB_PASSWORD"));
             PreparedStatement ps = connect.prepareStatement(query);
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println(properties.getProperty("DB_URL"));

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





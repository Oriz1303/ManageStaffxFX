package com.example.managestaff.model.repository;

import com.example.managestaff.model.dao.JDBCConnect;
import com.example.managestaff.model.entity.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Properties;

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

    public ObservableList<Staff> addStaff() {
        ObservableList<Staff> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM staff";
        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery();
        ) {
            Staff staff;
            while (resultSet.next()) {
                staff = new Staff(resultSet.getInt("id"), resultSet.getString("fullname"),
                        resultSet.getInt("gender"), resultSet.getDate("dob"),
                        resultSet.getString("phoneNumber"), resultSet.getString("email"),
                        resultSet.getInt("department_id"), resultSet.getInt("position_id"),
                        resultSet.getString("status"), resultSet.getString("imgUrl"));
                listData.add(staff);
            }
            return listData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<String> getFullnames() {
        ObservableList<String> listData = FXCollections.observableArrayList();
        String sql = "SELECT id, fullname FROM staff ORDER BY id DESC LIMIT 4";
        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery();
        ) {
            while (resultSet.next()) {
                String fullname = resultSet.getString("fullname");
                listData.add(fullname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public int getAll() {
        String sql = "SELECT COUNT(id) FROM staff ";
        Properties properties = new JDBCConnect().dbConfig();

        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(sql);
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("COUNT(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}









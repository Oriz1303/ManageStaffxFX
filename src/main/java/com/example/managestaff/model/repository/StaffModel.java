package com.example.managestaff.model.repository;

import com.example.managestaff.model.dao.JDBCConnect;
import com.example.managestaff.model.entity.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
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

    public static boolean add(Staff staff) {
        String insertStaff = "INSERT INTO staff ( " +
                "fullname, gender, dob, phoneNumber, email, " +
                "department_id, position_id, imgUrl,id, status)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(insertStaff)) {
            ps.setString(1, staff.getName());
            ps.setInt(2, staff.getGender());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ps.setDate(3, staff.getDob());
            ps.setString(4, staff.getPhoneNumber());
            ps.setString(5, staff.getEmail());
            ps.setInt(6, staff.getDepartmentId());
            ps.setInt(7, staff.getPositionId());
            ps.setString(8, staff.getUrlPortrait());
            ps.setInt(9, staff.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ObservableList<Staff> getAll() {
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM staff";
        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                System.out.println(1);
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("fullname"));
                staff.setGender(rs.getInt("gender"));
                staff.setDob(rs.getDate("dob"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setEmail(rs.getString("email"));
                staff.setDepartmentId(rs.getInt("department_id"));
                staff.setPositionId(rs.getInt("position_id"));
                staffList.add(staff);
            }
            return staffList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}








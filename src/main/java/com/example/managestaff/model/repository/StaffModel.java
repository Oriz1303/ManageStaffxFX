
package com.example.managestaff.model.repository;

import com.example.managestaff.model.config.JDBCConnect;
import com.example.managestaff.model.entity.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class StaffModel {

    private static Connection getConnection() throws Exception {
        Properties properties = new JDBCConnect().dbConfig();
        return JDBCConnect.getConnection(properties);
    }

    public boolean getOne(String username, String password) {
        String query = "SELECT * FROM account WHERE username = ? AND password = ?;";
        try (Connection connect = getConnection();
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

    // Handle DashBoard Page
    public ObservableList<String> getFullnames() {
        ObservableList<String> listData = FXCollections.observableArrayList();
        String sql = "SELECT id, fullname ,email FROM staff ORDER BY id DESC LIMIT 6";
        Properties properties = new JDBCConnect().dbConfig();
        try (Connection connect = JDBCConnect.getConnection(properties);
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery();
        ) {
            while (resultSet.next()) {
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                listData.add(fullname);
                listData.add(email);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public static int getToTalStaff() {
        String sql = "SELECT COUNT(id) FROM staff ";

        try (Connection connect = getConnection();
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

    public static int getActiveStaff() {
        String sql = "SELECT COUNT(id) FROM staff WHERE status = 1";
        try (Connection connect = getConnection();
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

    public static int getDeactiveStaff() {
        String sql = "SELECT COUNT(id) FROM staff WHERE status = 0";
        try (Connection connect = getConnection();
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

    public static boolean add(Staff staff) {
        String insertStaff = "INSERT INTO staff ( " +
                "fullname, gender, dob, phoneNumber, email, " +
                "department_id, position_id, imgUrl,id, status)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection connect = getConnection();
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

    public static void delete(Staff staff) {
        String deleteQuery = "UPDATE staff SET status = 0 WHERE id = ?";
        try (Connection connect = getConnection();
             PreparedStatement ps = connect.prepareStatement(deleteQuery);) {
            ps.setInt(1, staff.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Staff> getAll() {
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM staff WHERE status = 1 ORDER BY id DESC ";
        try (Connection connect = getConnection();
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("fullname"));
                staff.setGender(rs.getInt("gender"));
                staff.setDob(rs.getDate("dob"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setEmail(rs.getString("email"));
                staff.setDepartmentId(rs.getInt("department_id"));
                staff.setPositionId(rs.getInt("position_id"));
                staff.setUrlPortrait(rs.getString("imgUrl"));
                staffList.add(staff);
            }
            return staffList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


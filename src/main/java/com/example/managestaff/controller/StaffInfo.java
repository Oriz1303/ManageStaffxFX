package com.example.managestaff.controller;

import com.example.managestaff.model.entity.Staff;
import com.example.managestaff.model.repository.StaffModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffInfo implements Initializable {
    @FXML
    private Button btnExitStaffInfo;
    @FXML
    private AnchorPane anchor_staffInfo;
    StaffModel dao = new StaffModel();
    private ObservableList<Staff> showInfoStaff;
    @FXML
    private Label staffInfo_dob, staffInfo_fullName, staffInfo_staffId, staffInfo_status;

    public void showInfoStaff() {
        showInfoStaff = dao.addStaff();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showInfoStaff();
    }
}

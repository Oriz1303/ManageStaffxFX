package com.example.managestaff.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminTest implements Initializable {

    @FXML
    private AnchorPane anchorAddAccount, anchorDashBoard, anchorInfo,
            anchorReport, anchorRoom, anchorSalary,
            anchorSetting, anchorStaff;


    @FXML
    private Button btnAccountInfo,btnAddAccount,btnSalary,
            btnDBoard,btnDom,btnReport,
            btnSetting,btnSignOut,btnStaff;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClick(ActionEvent event) {
        anchorDashBoard.setVisible(event.getSource() == btnDBoard);
        anchorStaff.setVisible(event.getSource() == btnStaff);
        anchorReport.setVisible(event.getSource() == btnReport);
        anchorRoom.setVisible(event.getSource() == btnDom);
        anchorAddAccount.setVisible(event.getSource() == btnAddAccount);
        anchorSalary.setVisible(event.getSource() == btnSalary);
        anchorInfo.setVisible(event.getSource() == btnAccountInfo);
        anchorSetting.setVisible(event.getSource() == btnSetting);

    }

    public void exit() {
        System.exit(0);
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                btnSignOut.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/managestaff/view/login.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(root);
                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

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
    private GridPane gpAccounts;

    @FXML
    private GridPane gpAddAccount;

    @FXML
    private GridPane gpDBoad;

    @FXML
    private GridPane gpDom;

    @FXML
    private GridPane gpMail;

    @FXML
    private GridPane gpReport;

    @FXML
    private GridPane gpSetting;

    @FXML
    private GridPane gpSignOut;

    @FXML
    private GridPane gpStaff;
    @FXML
    private Button btnAccounts;

    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnDBoard;

    @FXML
    private Button btnDom;

    @FXML
    private Button btnMail;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnStaff;


    @FXML
    private Label lbHome;

    @FXML
    private Label lbStatus;

    @FXML
    private Pane pntStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == btnDBoard) {
            lbHome.setText("Home/DashBoard");
            lbStatus.setText("DashBoard");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
            gpDBoad.toFront();
        } else if (event.getSource() == btnAccounts) {
            lbHome.setText("Home/Accounts");
            lbStatus.setText("Accounts");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAccounts.toFront();

        } else if (event.getSource() == btnStaff) {
            lbHome.setText("Home/Staff");
            lbStatus.setText("Staff");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 99, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            gpStaff.toFront();

        } else if (event.getSource() == btnDom) {
            lbHome.setText("Home/DOM & ROOM");
            lbStatus.setText("Dom and romm");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            gpDom.toFront();
        } else if (event.getSource() == btnAddAccount) {
            lbHome.setText("Home/Add Account");
            lbStatus.setText("Add account");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAddAccount.toFront();
        } else if (event.getSource() == btnMail) {
            lbHome.setText("Home/Mail");
            lbStatus.setText("Mail");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            gpMail.toFront();
        } else if (event.getSource() == btnReport) {
            lbHome.setText("Home/Report");
            lbStatus.setText("Report");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            gpReport.toFront();
        } else if (event.getSource() == btnSetting) {
            lbHome.setText("Home/Setting");
            lbStatus.setText("Setting");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            gpSetting.toFront();
        }
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

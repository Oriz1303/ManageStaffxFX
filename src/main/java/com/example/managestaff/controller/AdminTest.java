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
    private AnchorPane accountsForm;
    @FXML
    private AnchorPane mailForm;
    @FXML
    private AnchorPane reportForm;

    @FXML
    private AnchorPane settingForm;

    @FXML
    private AnchorPane staffForm;
    @FXML
    private AnchorPane domForm;
    @FXML
    private AnchorPane addAccountForm;
    @FXML
    private AnchorPane dboardForm;

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
            dboardForm.setVisible(true);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
            addAccountForm.setVisible(false);
        } else if (event.getSource() == btnAccounts) {
            accountsForm.setVisible(true);
            dboardForm.setVisible(false);
            mailForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
            addAccountForm.setVisible(false);
        } else if (event.getSource() == btnStaff) {
            staffForm.setVisible(true);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            domForm.setVisible(false);
            addAccountForm.setVisible(false);
        } else if (event.getSource() == btnDom) {
            domForm.setVisible(true);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            addAccountForm.setVisible(false);
        } else if (event.getSource() == btnAddAccount) {
            addAccountForm.setVisible(true);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
        } else if (event.getSource() == btnMail) {
            mailForm.setVisible(true);
            addAccountForm.setVisible(false);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            reportForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
        } else if (event.getSource() == btnReport) {
            reportForm.setVisible(true);
            addAccountForm.setVisible(false);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            settingForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
        } else if (event.getSource() == btnSetting) {
            settingForm.setVisible(true);
            reportForm.setVisible(false);
            addAccountForm.setVisible(false);
            dboardForm.setVisible(false);
            accountsForm.setVisible(false);
            mailForm.setVisible(false);
            staffForm.setVisible(false);
            domForm.setVisible(false);
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

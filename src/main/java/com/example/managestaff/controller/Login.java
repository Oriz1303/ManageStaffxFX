package com.example.managestaff.controller;

import com.example.managestaff.model.repository.StaffModel;
import com.example.managestaff.model.services.SwitchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login {
    @FXML
    private AnchorPane loginScene;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    private Label loginMsg;

    @FXML
    void onClickedLogin(MouseEvent event) throws Exception {
        AlertMessage alertMessage = new AlertMessage();
        if (inputUsername.getText().isBlank()) {
            loginMsg.setText("Enter username");
        }

        if (inputPassword.getText().isBlank()) {
            loginMsg.setText("Enter password");
        }

        String usernameStr = inputUsername.getText();
        String passwordStr = inputPassword.getText();
        StaffModel dao = new StaffModel();
        boolean flag = dao.getOne(usernameStr, passwordStr);
        if (flag) {
            new SwitchScene(loginScene, "/com/example/managestaff/view/admin.fxml");
        } else {
            alertMessage.errorMessage("Incorrect Username/Passwrod");
        }
    }

    public void exit() {
        System.exit(0);
    }

    @FXML
    void switchScene(MouseEvent event) throws IOException {

    }

    @FXML
    void btnLogin(MouseEvent event) {

    }

}

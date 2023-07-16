package com.example.managestaff.controller;

import com.example.managestaff.dao.JDBCConnect;
import com.example.managestaff.model.SwitchScene;
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
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label errorMsg;



    @FXML
    void switchScene(MouseEvent event) throws IOException  {
            new SwitchScene(loginScene, "view/dashboard.fxml");
    }
    @FXML
    void btnLogin(MouseEvent event) {
        if(username.getText().isBlank()) {
            errorMsg.setText("Enter username");
        }

        if(password.getText().isBlank()) {
            errorMsg.setText("Enter password");
        }

        String usernameStr = username.getText();
        String passwordStr = password.getText();
        JDBCConnect dao = new JDBCConnect();
        boolean flag = dao.login(usernameStr, passwordStr);
        if(flag) {
            errorMsg.setText("Completed");
        } else {
            errorMsg.setText("failed");
        }
    }

}

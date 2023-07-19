package com.example.managestaff.controller;

    import com.example.managestaff.dao.JDBCConnect;
import com.example.managestaff.model.SwitchScene;
    import javafx.event.ActionEvent;
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
        if (inputUsername.getText().isBlank()) {
            loginMsg.setText("Enter username");
        }

        if (inputPassword.getText().isBlank()) {
            loginMsg.setText("Enter password");
        }

        String usernameStr = inputUsername.getText();
        String passwordStr = inputPassword.getText();
        JDBCConnect dao = new JDBCConnect();
        boolean flag = dao.login(usernameStr, passwordStr);
        if (flag) {
            new SwitchScene(loginScene, "view/dashboard.fxml");
        } else {
            loginMsg.setText("failed");
        }


    }

    public void  exit(){
        System.exit(0);
    }

    @FXML
    void switchScene(MouseEvent event) throws IOException {

    }

    @FXML
    void btnLogin(MouseEvent event) {

    }

}

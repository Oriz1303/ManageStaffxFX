package com.example.managestaff.controller;

import com.example.managestaff.model.entity.UserDataModel;
import com.example.managestaff.model.repository.StaffModel;
import com.example.managestaff.model.services.Oclock;
import com.example.managestaff.model.services.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private AnchorPane loginScene;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    private Label loginMsg, loginOclock;

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
            UserDataModel userDataModel = new UserDataModel();
            userDataModel.setUsername(usernameStr);
            new SwitchScene(loginScene, "/com/example/managestaff/view/admin.fxml", userDataModel);
v
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Oclock.runClock(loginOclock);
    }
}

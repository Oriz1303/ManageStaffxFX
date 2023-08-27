package com.example.managestaff.controller;

import com.example.managestaff.model.entity.UserDataModel;
import com.example.managestaff.model.repository.StaffModel;
import com.example.managestaff.model.services.Oclock;
import com.example.managestaff.model.services.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TextField inputUsername,showPassword;

    @FXML
    private Label loginMsg, loginOclock;
    @FXML
    private CheckBox selectShowPassword;

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
        } else {
            alertMessage.errorMessage("Incorrect Username/Passwrod");
        }
    }

    public void exit() {
        System.exit(0);
    }
    public void showPassword(){
        if(selectShowPassword.isSelected()){
            showPassword.setText(inputPassword.getText());
            showPassword.setVisible(true);
            inputPassword.setVisible(false);
        }else{
            inputPassword.setText(showPassword.getText());
            showPassword.setVisible(false);
            inputPassword.setVisible(true);
        }
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

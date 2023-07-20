package com.example.managestaff.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminTest implements Initializable {
    @FXML
    private GridPane gpAddaccunt;

    @FXML
    private GridPane gpDBoard;

    @FXML
    private GridPane gpSeeting;

    @FXML
    private GridPane gpStudent;

    @FXML
    private GridPane gpTeacher;
    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnDBoard;

    @FXML
    private Button btnSetiing;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnTeacher;

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
        if (event.getSource() == btnStudent) {
            lbHome.setText("Home/Students");
            lbStatus.setText("Students");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
            gpStudent.toFront();
        } else if (event.getSource() == btnTeacher) {
            lbHome.setText("Home/Teachers");
            lbStatus.setText("Teachers");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            gpTeacher.toFront();

        } else if (event.getSource() == btnDBoard) {
            lbHome.setText("Home/DashBoard");
            lbStatus.setText("DachBoard");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb( 43, 99,63), CornerRadii.EMPTY, Insets.EMPTY)));
            gpDBoard.toFront();

        } else if (event.getSource() == btnAddAccount) {
            lbHome.setText("Home/Addaccount");
            lbStatus.setText("Addaccount");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            gpAddaccunt.toFront();
        } else if (event.getSource() == btnSetiing) {
            lbHome.setText("Home/setting");
            lbStatus.setText("setting");
            pntStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            gpSeeting.toFront();
        }
    }

}

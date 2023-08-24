package com.example.managestaff.model.services;

import com.example.managestaff.App;
import com.example.managestaff.controller.AdminTest;
import com.example.managestaff.model.entity.UserDataModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {
    public SwitchScene(AnchorPane anchorPane, String fxml, UserDataModel userDataModel) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1423, 848);

        AdminTest adminTest = loader.getController();
        adminTest.setUserDataModel(userDataModel);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}

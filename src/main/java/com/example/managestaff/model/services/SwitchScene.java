package com.example.managestaff.model.services;

import com.example.managestaff.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {
    public SwitchScene(AnchorPane anchorPane, String fxml) throws IOException  {
//        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
//        anchorPane.getChildren().clear();
//        anchorPane.getChildren().setAll(nextAnchorPane);
//        anchorPane.setMinSize(2000, 1000);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root, 1390, 848);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


    }

}

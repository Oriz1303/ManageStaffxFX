package com.example.managestaff.model;

import com.example.managestaff.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {
    public SwitchScene(AnchorPane anchorPane, String fxml) throws IOException  {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
        System.out.println(nextAnchorPane.toString());
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(nextAnchorPane);
    }
}

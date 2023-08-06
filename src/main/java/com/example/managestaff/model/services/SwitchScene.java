package com.example.managestaff.model.services;

import com.example.managestaff.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {
    public SwitchScene(AnchorPane anchorPane, String fxml) throws IOException  {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(nextAnchorPane);
    }

}

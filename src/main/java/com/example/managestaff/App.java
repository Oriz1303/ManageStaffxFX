package com.example.managestaff;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        stage.setTitle("Manage Staff");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
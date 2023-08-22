package com.example.managestaff.model.services;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;

public class Oclock {

    public static void runClock(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateClock(label)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void updateClock(Label label) {
        Platform.runLater(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String currentTime = sdf.format(new java.util.Date());
            label.setText(currentTime);
        });
    }
}

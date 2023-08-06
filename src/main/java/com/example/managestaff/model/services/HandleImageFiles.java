package com.example.managestaff.model.services;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class HandleImageFiles {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length, Set<String> existingStrings) {
        while (true) {
            String randomString = generateRandomStringInternal(length);
            if (!existingStrings.contains(randomString)) {
                return randomString;
            }
        }
    }

    private static String generateRandomStringInternal(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public AtomicReference<String> handleBtn(Button btn, Circle portrait/*, ImageView portrait, Staff staff*/) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        AtomicReference<String> destPathRef = new AtomicReference<>();
//        String staffName = staff.getName().trim().replaceAll("\\s+", "");
        btn.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(btn.getScene().getWindow());
            if (selectedFile != null) {
                String destPath = "src/main/resources/com/example/managestaff/assets/images/img_staff/" + selectedFile.getName();
                saveImgToProject(selectedFile.toPath(), destPath);
                Image image = new Image("file:" + destPath);
                portrait.setFill(new ImagePattern(image));
                destPathRef.set(destPath);
            }
        });
        return destPathRef;
    }

    private static void saveImgToProject(Path src, String dest) {
        try {
            Path destFolder = Paths.get(dest).getParent();
            Files.createDirectories(destFolder);
            Files.copy(src, Paths.get(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class PhotoController {
    @FXML private ListView<String> photoList;
    @FXML private ImageView imageView;
    @FXML private Button chooseButton;

    private List<File> storedPhotos = new ArrayList<>();
    private File storageDirectory = new File("pic_store"); // Change this to your desired folder path

    @FXML
    private void initialize() {
        loadStoredPhotos();
    }

    @FXML
    private void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            for (File selectedFile : selectedFiles) {
                File targetFile = new File(storageDirectory, selectedFile.getName());

                try {
                    Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    storedPhotos.add(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            updatePhotoList();
        }
    }

    @FXML
    private void displayPhoto() {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Image image = new Image(storedPhotos.get(selectedIndex).toURI().toString());
            imageView.setImage(image);
        }
    }

    private void updatePhotoList() {
        List<String> photoNames = new ArrayList<>();
        for (File photoFile : storedPhotos) {
            photoNames.add(photoFile.getName());
        }

        photoList.getItems().setAll(photoNames);
    }

    private void loadStoredPhotos() {
        if (storageDirectory.exists() && storageDirectory.isDirectory()) {
            File[] files = storageDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file)) {
                        storedPhotos.add(file);
                    }
                }
            }

            updatePhotoList();
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".png")
                || fileName.toLowerCase().endsWith(".jpg")
                || fileName.toLowerCase().endsWith(".jpeg")
                || fileName.toLowerCase().endsWith(".gif");
    }
}



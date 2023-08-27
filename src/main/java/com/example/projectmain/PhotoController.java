package com.example.check22;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;

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

    @FXML private Button previousButton;
    @FXML private Button nextButton;
    @FXML private Button deleteButton;

    @FXML private TextField searchField;

    private List<File> originalStoredPhotos; // Store a copy of all stored photos
    private int currentIndex = -1;

    private List<File> storedPhotos = new ArrayList<>();
    private File storageDirectory = new File("pic_store"); // Change this to your desired folder path

    @FXML
    private void initialize() {
        loadStoredPhotos();
        originalStoredPhotos = new ArrayList<>(storedPhotos); // Make a copy of stored photos
        if (!storedPhotos.isEmpty()) {
            currentIndex = 0;
            displayImageByIndex(currentIndex);
        }
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

    @FXML
    private void showPreviousImage(ActionEvent event) {
        if (!storedPhotos.isEmpty()) {
            currentIndex = (currentIndex - 1 + storedPhotos.size()) % storedPhotos.size();
            displayImageByIndex(currentIndex);
        }
    }

    @FXML
    private void showNextImage(ActionEvent event) {
        if (!storedPhotos.isEmpty()) {
            currentIndex = (currentIndex + 1) % storedPhotos.size();
            displayImageByIndex(currentIndex);
        }
    }

    private void displayImageByIndex(int index) {
        Image image = new Image(storedPhotos.get(index).toURI().toString());
        imageView.setImage(image);
        photoList.getSelectionModel().select(index);
    }

    @FXML
    private void deleteSelectedPhotos(ActionEvent event) {
        int selectedIndex = photoList.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            File selectedPhoto = storedPhotos.get(selectedIndex);

            if (selectedPhoto.delete()) {
                storedPhotos.remove(selectedIndex);
                updatePhotoList();
                imageView.setImage(null); // Clear the ImageView after deletion
            } else {
                // Handle deletion failure
                System.err.println("Failed to delete the selected photo.");
            }
        }
    }

    @FXML
    private void searchPhotos(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();

        if (searchText.isEmpty()) {
            // If search field is empty, reset to show all original photos
            storedPhotos.clear();
            storedPhotos.addAll(originalStoredPhotos);
            updatePhotoList();
            imageView.setImage(null); // Clear the ImageView after clearing search
        } else {
            // Perform search and update displayed photos
            List<File> searchResults = new ArrayList<>();
            for (File photo : originalStoredPhotos) {
                String fileName = photo.getName().toLowerCase();
                if (fileName.contains(searchText)) {
                    searchResults.add(photo);
                }
            }

            if (!searchResults.isEmpty()) {
                storedPhotos.clear();
                storedPhotos.addAll(searchResults);
                updatePhotoList();
                imageView.setImage(null); // Clear the ImageView after search
            }
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



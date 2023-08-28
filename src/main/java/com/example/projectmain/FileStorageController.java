package com.example.projectmain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class FileStorageController {

    @FXML
    private Label id;

    @FXML
    private TextArea messageTextField;


    @FXML
    private Button chooseFileButton;

    @FXML
    private Button downloadButton;

    @FXML
    private Button refreshButton;

    @FXML
    private ListView<String> fileListView;

    private ObservableList<String> storedFiles = FXCollections.observableArrayList();
    private File selectedFile;
    private File storageFolder = new File("storage_folder");

    public void setId(String id) {
        this.id.setText(id);
    }

    @FXML
    private void initialize() {
        chooseFileButton.setOnAction(event -> chooseFile());
        downloadButton.setOnAction(event -> downloadFile());
        refreshButton.setOnAction(event -> refreshFileList());

        fileListView.setItems(storedFiles);
        loadStoredFiles();
    }

    private void displayMessage(String message) {
        messageTextField.setText(message);
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File to Store");

        Stage stage = (Stage) chooseFileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            storeFile(selectedFile);
            loadStoredFiles();
            displayMessage("File selected and stored: " + selectedFile.getName()); // Display message
            System.out.println("File selected and stored: " + selectedFile.getName());
        }
    }

    private void storeFile(File sourceFile) {
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        File destinationFile = new File(storageFolder, sourceFile.getName());

        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error storing the file.");
        }
    }

    private void loadStoredFiles() {
        storedFiles.clear();
        if (storageFolder.exists() && storageFolder.isDirectory()) {
            storedFiles.addAll(Arrays.asList(storageFolder.list()));
        }
    }

    private void downloadFile() {
        String selectedFileName = fileListView.getSelectionModel().getSelectedItem();

        if (selectedFileName == null) {
                       System.err.println("No file selected for download.");
            return;
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Destination Folder");

        Stage stage = (Stage) downloadButton.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            Path sourceFilePath = new File(storageFolder, selectedFileName).toPath();
            Path destinationPath = selectedDirectory.toPath().resolve(selectedFileName);

            try {
                Files.copy(sourceFilePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                displayMessage("File downloaded successfully."); // Display message
                System.out.println("File downloaded successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error downloading the file.");
            }
        }
    }

    private void refreshFileList() {
        loadStoredFiles();
        displayMessage("File list refreshed."); // Display message
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        mainController mainController = loader.getController();
        mainController.setUsername(this.id.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.show();
    }
}

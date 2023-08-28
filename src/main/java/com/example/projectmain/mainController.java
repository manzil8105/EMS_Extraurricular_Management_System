package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainController {
    @FXML
    private Label idLabel;

    @FXML
    Button userProfile;
    @FXML
    Button clubProfile;

    @FXML
    private Button CreateGroup;

    @FXML
    private Button Logout;

    @FXML
    private Button Notifications;

    @FXML
    private Button Privatefiles;

    @FXML
    private Button chats;


    @FXML
    private Button photoGallery;

    @FXML
    private Button ai;

    public void setUsername(String id) {
        idLabel.setText(id);
    }


    @FXML
    private void switchToUser(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
        Parent root = loader.load();

        userProfileController userProfileController = loader.getController();
        userProfileController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    @FXML
    private void switchToClub(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Clubnames.fxml"));
        Parent root = loader.load();

        ClubnamesController clubnamesController = loader.getController();
        clubnamesController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club");
        stage.show();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    void privatefiles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("private_files.fxml"));
        Parent root = loader.load();

        FileStorageController fileStorageController = loader.getController();
        fileStorageController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Private Files");
        stage.show();
    }

    @FXML
    void switchToChat(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Private Files");
        stage.show();
    }
    @FXML
    void switchToAI(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatgpt.fxml"));
        Parent root = loader.load();

        Ai_controller aiController = loader.getController();
        aiController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("XtraCurio");
        stage.show();
    }
    @FXML
    void switchTophotoGallery(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Photo_gallery.fxml"));
        Parent root = loader.load();

        PhotoController photoController = loader.getController();
        photoController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Photo Gallery");
        stage.show();
    }


    @FXML
    void switchToCreateGroup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createClub.fxml"));
        Parent root = loader.load();

        createClubController createClubController = loader.getController();
        createClubController.setId(idLabel.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Create Club");
        stage.show();
    }
}

package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;





import java.io.IOException;

public class mainController {
    @FXML
    Button userProfile;
    @FXML
    Button clubProfile;

    @FXML
    Button back;

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
    private void switchToUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userProfileN.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    @FXML
    private void switchToClub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clubHome.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("private_files.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Private Files");
        stage.show();
    }

    @FXML
    void switchToChat(ActionEvent event) {

    }


    @FXML
    void switchToCreateGroup(ActionEvent event) {

    }

    @FXML
    void switchToNotifications(ActionEvent event) {

    }



}

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
    private void switchToUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userprofile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToClub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clubHome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

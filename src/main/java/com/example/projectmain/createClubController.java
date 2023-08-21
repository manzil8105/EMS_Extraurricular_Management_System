package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class createClubController {
    @FXML
    private Label id;
    @FXML
    private TextField name;
    @FXML
    private TextField club_id;
    @FXML
    private TextArea bio;

    @FXML
    private void createClub(ActionEvent event) throws SQLException {
        if (Objects.equals(name.getText(), "") ||
                Objects.equals(club_id.getText(), "") ||
                Objects.equals(bio.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Creating Club Unsuccessful!");
            alert.setContentText("All field must be filled!");
            alert.showAndWait();
        } else {
            PreparedStatement preparedStatement = DB.conn().prepareStatement("INSERT INTO club VALUE (?, ?, ?)");
            preparedStatement.setString(1, name.getText());
            preparedStatement.setString(2, club_id.getText());
            preparedStatement.setString(3, bio.getText());
            preparedStatement.executeUpdate();

            preparedStatement = DB.conn().prepareStatement("INSERT INTO club_member VALUE (?, ?)");
            preparedStatement.setString(1, name.getText());
            preparedStatement.setString(2, id.getText());
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Club Created Successfully!");
            alert.setContentText("Please go to 'Club' to see all the club");
            alert.showAndWait();

            name.clear();
            club_id.clear();
            bio.clear();
        }
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    @FXML
    public void goback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        mainController mainController = loader.getController();
        mainController.setUsername(id.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.show();
    }
}

package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationEventController {
    @FXML
    private Label club_name;
    @FXML
    private Label id;
    @FXML
    private TextField eventTitle;
    @FXML
    private TextArea eventDescription;
    @FXML
    private DatePicker eventStartID;
    @FXML
    private DatePicker eventEndID;

    public void setId(String id, String club_name) {
        this.club_name.setText(club_name);
        this.id.setText(id);
    }

    @FXML
    private void registerEvent(ActionEvent event) throws SQLException {
        if (Objects.equals(eventTitle.getText(), "") || Objects.equals(eventDescription.getText(), "") ||
                Objects.equals(eventStartID.getValue(), "") || Objects.equals(eventEndID.getValue(), "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Registration Event Unsuccessful!");
            alert.setContentText("All field must be filled!");
            alert.showAndWait();
        } else {
            PreparedStatement preparedStatement = DB.conn().prepareStatement("INSERT INTO event VALUE (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, club_name.getText());
            preparedStatement.setString(2, eventTitle.getText());
            preparedStatement.setString(3, eventDescription.getText());
            preparedStatement.setDate(4, Date.valueOf(eventStartID.getValue()));
            preparedStatement.setDate(5, Date.valueOf(eventEndID.getValue()));

            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Registration Event Successful!");
            alert.setContentText("Please go to 'Upcoming Event' to see!");
            alert.showAndWait();

            eventTitle.clear();
            eventDescription.clear();
            eventStartID.getEditor().clear();
            eventEndID.getEditor().clear();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("clubHome.fxml"));
        Parent root = loader.load();

        clubProfileController clubProfileController = loader.getController();
        clubProfileController.setId(this.id.getText(), this.club_name.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club Home");
        stage.show();
    }
}

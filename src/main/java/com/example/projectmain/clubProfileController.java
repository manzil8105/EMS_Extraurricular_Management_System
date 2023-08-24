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
import java.sql.ResultSet;
import java.sql.SQLException;

public class clubProfileController {
    @FXML
    private Label id;
    @FXML
    private Label club_name;

    public void setId(String id, String club_name) {
        this.id.setText(id);
        this.club_name.setText(club_name);
    }


    @FXML
    private void buttonMemberlist(ActionEvent event) throws SQLException {
        String sql = "SELECT member_id FROM club_member WHERE club_name = '" + club_name.getText() + "'";
        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

        String member = "";
        while (resultSet.next()) {
            sql = "SELECT * FROM user WHERE id = '" + resultSet.getString("member_id") + "'";
            ResultSet resultSet1 = DB.conn().createStatement().executeQuery(sql);

            if (resultSet1.next())
                member += String.format("%-30s", resultSet1.getString("name"));
            member += String.format("%-20s", resultSet1.getString("id"));
            member += String.format("%-20s", resultSet1.getString("phoneNumber"));
            member += "\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Member List");
        alert.setHeaderText("Member of " + club_name.getText());
        alert.setContentText(member);
        alert.showAndWait();
    }

    @FXML
    private void buttonActivity(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Activity.fxml"));
        Parent root = loader.load();

        ActivityController activityController = loader.getController();
        activityController.setId(this.id.getText(), this.club_name.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Activity");
        stage.show();
    }

    @FXML
    private void buttonRegister(ActionEvent event) throws IOException, SQLException {
        String sql = "SELECT * FROM  club_member WHERE club_name = '" + this.club_name.getText() + "' and member_id = '" + this.id.getText() + "'";
        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

        resultSet.next();
        if (resultSet.getBoolean("is_admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationEvent.fxml"));
            Parent root = loader.load();

            RegistrationEventController registrationEventController = loader.getController();
            registrationEventController.setId(this.id.getText(), this.club_name.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Event");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Permission Denied!");
            alert.setContentText("You are not an Admin. You can not create an even!");
            alert.showAndWait();
        }
    }

    @FXML
    private void buttonUpcomingEvents(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("upcomingEvent.fxml"));
        Parent root = loader.load();

        upcomingEventController upcomingEventController = loader.getController();
        upcomingEventController.setId(this.id.getText(), this.club_name.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Upcoming Event");
        stage.show();
    }

    @FXML
    private void goBac(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Clubnames.fxml"));
        Parent root = loader.load();

        ClubnamesController clubnamesController = loader.getController();
        clubnamesController.setId(this.id.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club");
        stage.show();
    }
}

package com.example.projectmain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class loginController {
    @FXML
    private TextField id;
    @FXML
    private TextField password;

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        if (Objects.equals(id.getText(), "") || Objects.equals(password.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Unsuccessful!");
            alert.setContentText("All field must be filled!");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM user WHERE id = '" + id.getText() + "' and password = '" + password.getText() + "'";
            ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

            if (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();

                mainController mainController = loader.getController();
                mainController.setUsername(resultSet.getString("id"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Login Unsuccessful!");
                alert.setContentText("Wrong ID or Password! Place try again!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void registration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.show();
    }
}

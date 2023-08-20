package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class registrationController {
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField department;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    private FileInputStream fileInputStream;

    @FXML
    private void fileChooserButton(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        fileInputStream = new FileInputStream(file);
    }

    @FXML
    private void registration(ActionEvent event) throws IOException, SQLException {
        if (Objects.equals(name.getText(), "") || Objects.equals(id.getText(), "") ||
                Objects.equals(phoneNumber.getText(), "") || Objects.equals(email.getText(), "") ||
                Objects.equals(department.getText(), "") || Objects.equals(password.getText(), "") ||
                Objects.equals(confirmPassword.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Registration Unsuccessful!");
            alert.setContentText("All field must be filled!");
            alert.showAndWait();
        } else if (!Objects.equals(password.getText(), confirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Registration Unsuccessful!");
            alert.setContentText("Password dose not match!");
            alert.showAndWait();
        } else {
            PreparedStatement preparedStatement = DriverManager.getConnection(
                    "jdbc:mysql://buao2mvep5jvwqhexgvz-mysql.services.clever-cloud.com:3306/buao2mvep5jvwqhexgvz",
                    "un3qv8gp39vnbmmw",
                    "4sudsBiVeHXYfXS8nlph").prepareStatement("INSERT INTO user VALUE (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, name.getText());
            preparedStatement.setString(2, id.getText());
            preparedStatement.setString(3, phoneNumber.getText());
            preparedStatement.setString(4, email.getText());
            preparedStatement.setString(5, department.getText());
            preparedStatement.setString(6, password.getText());
            preparedStatement.setBinaryStream(7, fileInputStream);

            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Registration Successful!");
            alert.setContentText("Please Login with your id and password!");
            alert.showAndWait();

            name.clear();
            id.clear();
            phoneNumber.clear();
            email.clear();
            department.clear();
            password.clear();
            confirmPassword.clear();
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}

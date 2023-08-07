package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class userprofileController {
    @FXML
    private Button add_pic;

    @FXML
    Button back;

    @FXML
    private void goBac(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private DatePicker dob_field_pros;

    @FXML
    private Button edit_profile;

    @FXML
    private TextField email_field_pros;

    @FXML
    private TextField name_field_pros;

    @FXML
    private Button notifications;

    @FXML
    private Button payment_his;

    @FXML
    private TextField pnum_field_pros;

    @FXML
    private Button save_button_pros;

    @FXML
    private Button security;

    @FXML
    private Button settings;

    @FXML
    private TextField uname_field_pros;

    @FXML
    void SaveData_function(ActionEvent event) {

    }

    @FXML
    void add_pic_function(ActionEvent event) {

    }

    @FXML
    void notification_function(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("notification.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void paymentHistory_function(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("paymentS.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void settings_function(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("upsettings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
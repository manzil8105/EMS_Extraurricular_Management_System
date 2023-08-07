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

public class clubProfileController {

//    @FXML
//    private ScrollPane options;

    @FXML
    private Button committeeMembers;

    @FXML
    private Button memberList;

    @FXML
    private Button photoGallery;

    @FXML
    private Button upcomingEvents;

    @FXML
    private Button activities;

    @FXML
    private Button registerForEvent;

    @FXML
    private Button evaluate;

    @FXML
    private Button reportAnalytics;

    @FXML
    private Button gobacFromMembers;

    @FXML
    private Button gobacFromPhotos;

    @FXML
    private Button gobacFromActivities;

    @FXML
    private Button gobacFromRegistration;

    @FXML
    private Button gobacFromEval;

    @FXML
    private Button gobacFromReportAnalytics;

    @FXML
    private Button gobacFromUpcomingEvents;

    @FXML
    private Button gobacFromComMembers;
    @FXML
    private void buttonMemberlist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Memberlist.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonComMemberlist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComMemberlist.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonPhotoGal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PhotoGall.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonActivity(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ActivityXml.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegForevent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonEval(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Eval.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonreportAnalytics(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReportAnalytics.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void buttonUpcomingEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Upcomingevents.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clubHome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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



}

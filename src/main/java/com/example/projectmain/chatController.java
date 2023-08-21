//package com.example.projectmain;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.net.Socket;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class chatController {
//    @FXML
//    private Label id;
//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox vBox;
//    @FXML
//    private TextField input;
//    private Client client;
//
//    public void init(String id) throws SQLException, IOException {
//        this.id.setText(id);
//
//        String sql = "SELECT name FROM user where id = '" + this.id.getText() + "'";
//        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);
//        if (resultSet.next()) {
//            String userName = resultSet.getString("name") + " (" + this.id.getText() + ")";
//            client = new Client(new Socket("localhost", 1234), userName);
//            System.out.println(userName);
//            System.out.println("Connected to Server");
//        }
//    }
//
//    @FXML
//    private void sent(ActionEvent event) {
//        client.sentMassage(input.getText());
//        input.clear();
//    }
//
//    @FXML
//    private void back(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
//        Parent root = loader.load();
//
//        mainController mainController = loader.getController();
//        mainController.setUsername(this.id.getText());
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Home");
//        stage.show();
//    }
//}

package com.example.projectmain;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userProfileController {
    @FXML
    private Label ID_fieldPros;
    @FXML
    private Label mailField_pros;
    @FXML
    private Label nameField_pros;
    @FXML
    private Label phoneField_pros;
    @FXML
    private ImageView profilePic_pros;

    public void setId(String id) throws SQLException, IOException {
        String sql = "SELECT * FROM user WHERE id = '" + id + "'";
        ResultSet resultSet = DB.conn().executeQuery(sql);

        resultSet.next();
        ID_fieldPros.setText(resultSet.getString("id"));
        nameField_pros.setText(resultSet.getString("name"));
        mailField_pros.setText(resultSet.getString("email"));
        phoneField_pros.setText(resultSet.getString("phoneNumber"));

        InputStream inputStream = resultSet.getBinaryStream("dp");
        OutputStream outputStream = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        while ((size = inputStream.read(content)) != -1) {
            outputStream.write(content, 0, size);
        }
        inputStream.close();
        outputStream.close();

        Image image = new Image("file:photo.jpg");
        profilePic_pros.setImage(image);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        new File("photo.jpg").delete();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        mainController mainController = loader.getController();
        mainController.setUsername(ID_fieldPros.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.show();
    }
}


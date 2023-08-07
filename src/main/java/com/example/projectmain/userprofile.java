package com.example.projectmain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class userprofile extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent rootLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userprofile.fxml")));
        Scene scene1 = new Scene(rootLayout, 1015, 744);
        stage.setTitle("UserProfile");
        stage.setScene(scene1);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}


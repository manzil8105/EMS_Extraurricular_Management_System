package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubnamesController {
    @FXML
    private Label id;
    @FXML
    private VBox vBox;
    private String sql;


    public void setId(String id) throws SQLException {
        this.id.setText(id);

        sql = "SELECT * FROM club";
        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

        while (resultSet.next()) {
            HBox hBox = new HBox();

            Button club_name = new Button();
            club_name.setText(resultSet.getString("name"));
            club_name.setStyle("-fx-background-color:  #181f2b; -fx-text-fill:  #f7a100; -fx-font-size: 20; -fx-padding: 20px; -fx-border-insets: 10px; -fx-background-insets: 10px;");
            club_name.setOnAction(event -> {
                try {
                    sql = "SELECT * FROM club_member WHERE member_id = '" + this.id.getText() + "' and club_name = '" + club_name.getText() + "'";
                    ResultSet resultSet2 = DB.conn().createStatement().executeQuery(sql);

                    if (resultSet2.next()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("clubHome.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        clubProfileController clubProfileController = loader.getController();
                        clubProfileController.setId(this.id.getText(), club_name.getText());

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Club Home");
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Can not enter " + club_name.getText() + "!");
                        alert.setContentText("First you have to join the club!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            hBox.getChildren().add(club_name);

            sql = "SELECT * FROM club_member WHERE member_id = '" + this.id.getText() + "' and club_name = '" + resultSet.getString("name") + "'";
            ResultSet resultSet1 = DB.conn().createStatement().executeQuery(sql);

            if (!resultSet1.next()) {
                Button join = new Button();
                join.setText("Join");
                join.setStyle("-fx-background-color:  #181f2b; -fx-text-fill:  #f7a100; -fx-background-radius: 30; -fx-font-size: 20; -fx-padding: 20px; -fx-border-insets: 10px; -fx-background-insets: 10px;");
                join.setOnAction(event -> {
                    try {
                        PreparedStatement preparedStatement = DB.conn().prepareStatement("INSERT INTO club_member VALUE (?, ?)");
                        preparedStatement.setString(1, club_name.getText());
                        preparedStatement.setString(2, this.id.getText());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Joined Club Successfully!");
                    alert.setContentText("Now you are a member of " + club_name.getText() + " club!");
                    alert.showAndWait();

                    try {
                        vBox.getChildren().clear();
                        setId(this.id.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                hBox.getChildren().add(join);
            }

            vBox.getChildren().add(hBox);
        }
    }

    @FXML
    private void toDoBack(ActionEvent event) throws IOException {
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

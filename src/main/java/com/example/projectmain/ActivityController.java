package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ActivityController {
    @FXML
    private Label id;
    @FXML
    private Label club_name;
    @FXML
    private TextArea post_message;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;

    public void setId(String id, String club_name) throws SQLException {
        this.id.setText(id);
        this.club_name.setText(club_name);

        update_post();
    }

    public void update_post() throws SQLException {
        String sql = "SELECT * FROM post WHERE club_name = '" + this.club_name.getText() + "'";
        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

        while (resultSet.next()) {
            sql = "SELECT name FROM user WHERE id = '" + resultSet.getString("member_id") + "'";
            ResultSet resultSet1 = DB.conn().createStatement().executeQuery(sql);
            if (resultSet1.next()) {
                Label author = new Label();
                author.setText(resultSet1.getString("name") + " (" + resultSet.getString("member_id") + ")");
                author.setStyle("-fx-text-fill:  #f7a100; -fx-font-size: 18");
                vBox.getChildren().add(author);

                Text post = new Text();
                post.setText(resultSet.getString("message"));
                post.setStyle("-fx-font-size: 16");
                post.setWrappingWidth(300);
                vBox.getChildren().add(post);


                Label gap = new Label();
                gap.setText("");
                gap.setStyle("-fx-font-size: 20");
                vBox.getChildren().add(gap);

                scrollPane.vvalueProperty().bind(vBox.heightProperty());
            }
        }
    }

    @FXML
    public void post(ActionEvent event) throws SQLException {
        if (Objects.equals(post_message.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Post Unsuccessful!");
            alert.setContentText("Post field must be filled!");
            alert.showAndWait();
        } else {
            PreparedStatement preparedStatement = DB.conn().prepareStatement("INSERT INTO post VALUE (?, ?, ?)");

            preparedStatement.setString(1, this.id.getText());
            preparedStatement.setString(2, this.club_name.getText());
            preparedStatement.setString(3, post_message.getText());
            preparedStatement.executeUpdate();

            post_message.clear();

            vBox.getChildren().clear();
            update_post();
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clubHome.fxml"));
        Parent root = loader.load();

        clubProfileController clubProfileController = loader.getController();
        clubProfileController.setId(this.id.getText(), club_name.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club Home");
        stage.show();
    }
}

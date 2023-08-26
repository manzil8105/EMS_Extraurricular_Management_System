package com.example.projectmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class upcomingEventController {
    @FXML
    private Label club_name;
    @FXML
    private Label id;
    @FXML
    private VBox vBox;

    public void setId(String id, String club_name) throws SQLException {
        this.club_name.setText(club_name);
        this.id.setText(id);

        String sql = "SELECT * FROM event WHERE club_name = '" + this.club_name.getText() + "' ORDER BY event_start";
        ResultSet resultSet = DB.conn().createStatement().executeQuery(sql);

        while (resultSet.next()) {
            Label eventTitle = new Label();
            eventTitle.setText(resultSet.getString("event_title"));
            eventTitle.setStyle("-fx-text-fill:  #f7a100; " +
                    "-fx-font-size:  26");

            Text eventDescription = new Text();
            eventDescription.setText(resultSet.getString("event_description"));
            eventDescription.setStyle("-fx-font-size: 20");
            eventDescription.setWrappingWidth(300);

            Label date = new Label();
            date.setText("Start: " + String.valueOf(resultSet.getDate("event_start"))
                    + "          End: " + String.valueOf(resultSet.getDate("event_end")));
            date.setStyle("-fx-padding: 5");

            Label gap = new Label();
            gap.setText("");
            gap.setStyle("-fx-font-size: 26");

            vBox.getChildren().addAll(eventTitle, eventDescription, date, gap);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clubHome.fxml"));
        Parent root = loader.load();

        clubProfileController clubProfileController = loader.getController();
        clubProfileController.setId(this.id.getText(), this.club_name.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club Home");
        stage.show();
    }
}

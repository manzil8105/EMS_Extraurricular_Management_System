package com.example.projectmain;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ai_controller {

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private Button backButton;
    @FXML
    private Label id;
    public void setId(String id) {
        this.id.setText(id);
    }

    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
    }

    public void sendMessage() {
        String userInput = inputTextArea.getText().trim();
        chatGPT("Your name is XtraCurio from now you will response form it");
        if (!userInput.isEmpty()) {
            appendToChat("You: " + userInput);
            if (userInput.equalsIgnoreCase("exit")) {
                appendToChat("XtraCurio: Goodbye!");
                // Perform any cleanup or exit logic
            } else {
                String response = chatGPT(userInput);
                appendToChat("XtraCurio: " + response);
            }
            inputTextArea.clear();
        }
    }
    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        mainController mainController = loader.getController();
        mainController.setUsername(this.id.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.show();

    }



    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
    private void appendToChat(String message) {
        chatTextArea.appendText(message + "\n");
    }
}

package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class SpeisekarteController extends  ConnectionController {

    @FXML
    private Button speiseHinzufuegen;
    @FXML
    private Button speiseBearbeiten;
    @FXML
    private Button zurueck;


    @FXML
    public void speiseHinzufuegen() throws IOException {
        changeScene("AddFood.fxml");
    }

    @FXML
    public void speiseBearbeiten() throws IOException {
        changeScene("EditFood1.fxml");
    }

    @FXML
    public void zurückButton() throws IOException {
        changeScene("Startseite.fxml");
    }
    @FXML
    public void xmlButton() throws IOException {
        changeScene("xml.fxml");

    }
}


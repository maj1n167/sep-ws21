package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SpeisekarteController extends  ConnectionController{

    @FXML
    private Button speiseHinzufuegen;
    @FXML
    private Button speiseBearbeiten;
    @FXML
    private Button zurueck;





    @FXML
    public void speiseHinzufuegen() throws IOException {
        Main m = new Main();
        m.ChangeScene("AddFood.fxml");
    }

    @FXML
    public void speiseBearbeiten() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

}

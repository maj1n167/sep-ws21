package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SpeisekarteController extends  ConnectionController{

    @FXML
    private Button speiseHinzufügen;
    @FXML
    private Button speiseBearbeiten;
    @FXML
    private Button zurück;





    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}

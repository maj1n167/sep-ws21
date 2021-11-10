package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditFoodController extends ConnectionController {

    @FXML
    private Button bearbeiten;
    @FXML
    private Button speichern;
    @FXML
    private Button dateiXml;
    @FXML
    private Button dateiUrl;
    @FXML
    private ChoiceBox<String> kategorie;
    @FXML
    private TextField name;
    @FXML
    private TextField beschreibung;
    @FXML
    private TextField preis;

    @FXML
    public void änderungSpeichern()throws IOException {

    }




    @FXML
    public void zurückButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }
}

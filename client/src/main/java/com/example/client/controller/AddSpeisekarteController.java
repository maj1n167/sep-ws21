package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSpeisekarteController implements Initializable {
    @FXML
    private Button bearbeiten;
    @FXML
    private Button speichern;
    @FXML
    private Button dateiXml;
    @FXML
    private Button dateiUrl;
    @FXML
    private ChoiceBox <String> kategorie;
    @FXML
    private TextField name;
    @FXML
    private TextField beschreibung;
    @FXML
    private TextField preis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kategorie.getItems().addAll("Pizza","Pasta","Salate","Desserts");
        kategorie.setValue("Pizza");
    }

    @FXML
    public void speichernClick() {
        if (name.getText().equals("")||beschreibung.getText().equals("")||preis.getText().equals("")){

            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        }
        else {


        }

    }

    @FXML
    public void speichernClick (ActionEvent event) throws IOException {
        // Change Scenes
        Main m= new Main();
       // soll zur Speisekarte zurück
        m.ChangeScene("CreateMenu.fxml");

    }

}

package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;

public class EditAltProfileController extends ConnectionController {
    @FXML
    Button sicherButton;
    @FXML
    TextField alternativeStreet;
    @FXML
    TextField alternativeNummer;
    @FXML
    TextField alternativeStadt;
    @FXML
    TextField alternativePostleitzahl;

    JSONObject current;

    public void initialize() throws IOException {
        current = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+LoginController.userId).toString());

        alternativeStreet.setText(current.get("strasse").toString());
        alternativeNummer.setText(current.get("nummer").toString());
        alternativeStadt.setText(current.get("stadt").toString());
        alternativePostleitzahl.setText(current.get("postleitzahl").toString());
    }
    @FXML
    public void onSicherButtonClick() throws IOException {

        //Hier werden die alten Daten von den neuen überschrieben -lg

        String url = "http://localhost:8080/user/update/";


        current.put("street", alternativeStreet.getText());
        current.put("nummer", alternativeNummer.getText());
        current.put("stadt", alternativeStadt.getText());
        current.put("postleitzahl", alternativePostleitzahl.getText());
        /*String data = "{ \"userId\": \""+LoginController.userId+"\", \n";
        data = data + "\"vorname\": \"" + neuerVorname.getText().toString() + "\",\n";
        data = data + " \"name\": \"" + neuerName.getText().toString() + "\",\n";
        data = data + " \"email\": \"" + neueEmail.getText().toString() + "\",\n";
        data = data + " \"password\": \"" + neuesPasswort.getText().toString() + "\",\n";
        data = data + " \"restaurantBesitzer\": \"true\" }";*/

        JSONObjectPUT(url, current.toString());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Daten editiert");
        alert.setContentText("Daten editiert!");
        alert.showAndWait();
        changeScene("Startseite.fxml");
    }

    public void onGoZueruckButtonClick() throws IOException {

        changeScene("Startseite.fxml");
    }
}


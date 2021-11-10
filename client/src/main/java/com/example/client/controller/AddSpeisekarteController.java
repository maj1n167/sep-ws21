package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static org.json.XML.toJSONObject;

public class AddSpeisekarteController extends ConnectionController implements Initializable {
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

    private String email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kategorie.getItems().addAll("Pizza","Pasta","Salate","Desserts");
        kategorie.setValue("Pizza");
        kategorie.show();
        this.email = LoginController.email;
    }

    @FXML
    public void speichernClick() throws IOException {
        if (name.getText().equals("")||beschreibung.getText().equals("")||preis.getText().equals("")){

            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");
            alert.showAndWait();
        }
        else {
            String Url = "http://localhost:8080/food/add";

            String data = "{   \"name\": \""+name.getText()+"\",\n" +
                    "        \"beschreibung\": \""+beschreibung.getText()+"\",\n" +
                    "        \"preis\":"+Double.parseDouble(preis.getText())+",\n"+
                    "        \"kategorie\": \""+kategorie.getValue()+"\"}";

            JSONObjectPOST(Url, data);
            System.out.println("Daten korrekt übertragen");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Speise hinzugefügt");
            alert.setContentText("Speise wurde erfolgreich hinzugefügt");

            alert.showAndWait();
            }


    }




}

package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFoodController extends ConnectionController implements Initializable {
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

    private int userId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kategorie.getItems().addAll("Pizza","Pasta","Salate","Desserts");
        kategorie.setValue("Pizza");
        kategorie.show();
        this.userId=LoginController.userId;
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

            String data = "\"menuId\":"+userId+","+
                        "\"foods\": [{   \"name\": \""+name.getText()+"\",\n" +
                    "        \"beschreibung\": \""+beschreibung.getText()+"\",\n" +
                    "        \"preis\":"+Double.parseDouble(preis.getText())+",\n"+
                    "        \"kategorie\": \""+kategorie.getValue()+"\"}]";

            JSONObjectPOST(Url, data);
            System.out.println("Daten korrekt übertragen");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Speise hinzugefügt");
            alert.setContentText("Speise wurde erfolgreich hinzugefügt");

            alert.showAndWait();
            }


    }

    @FXML
    public void zurückButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }




}

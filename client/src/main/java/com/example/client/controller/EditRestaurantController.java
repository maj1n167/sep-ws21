package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class EditRestaurantController extends ConnectionController {

    @FXML
    private Label restaurantHeaderLabel;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField straßeTextfield;

    @FXML
    private TextField plzTextfield;

    @FXML
    private TextField stadtTextfield;

    @FXML
    private TextField lieferkostenTextfield;

    @FXML
    private TextField mbwTextfield;

    @FXML
    private TextField lieferbereichTextfield;

    @FXML
    private TextField sonstigeText;


    @FXML
    ChoiceBox<String> kategorieChoicebox;

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;


    public void initialize() throws IOException {
        String name = "";
        String strasse = "";
        String plz = "";
        String stadt = "";
        double mbw = 0;
        double lieferkosten = 0;
        String kategorie = "";
        int lieferbereich = 0;


        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        for (int i = 1 ; i<j.length();i++){

            JSONObject currentjson = j.getJSONObject(i);
            name = currentjson.get("name").toString();
            strasse = currentjson.get("strasse").toString();
            plz = currentjson.get("plz").toString();
            stadt = currentjson.get("stadt").toString();
            lieferkosten = Double.valueOf(currentjson.get("lieferkosten").toString());
            mbw = Double.valueOf(currentjson.get("mbw").toString());
            lieferbereich = Integer.valueOf(currentjson.get("lieferbereich").toString());
            kategorie = currentjson.get(kategorie).toString();
        }

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");
        /**
         * TODO: Hier müsste die Kategorie auf die vorher ausgewählte Kategorie gesetzt werden
         */

        nameTextfield.setText(name);
       // kategorieChoicebox.setValue(katgorie);
        stadtTextfield.setText(stadt);
        plzTextfield.setText(plz);
        mbwTextfield.setText(String.valueOf(mbw));
        lieferkostenTextfield.setText(String.valueOf(lieferkosten));
        lieferbereichTextfield.setText(String.valueOf(lieferbereich));
        kategorieChoicebox.setValue(kategorie);


    }

    public void speichernButtonClick() throws IOException {

        if (nameTextfield.getText().equals("") || straßeTextfield.getText().equals("") || plzTextfield.getText().equals("")
                || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                || lieferbereichTextfield.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Error: Fehlende Zeilen!");
            alert.setContentText("Bitte füllen Sie alle Felder aus!");

            alert.showAndWait();

        }
        else {


            String url = "http://localhost:8080/restaurant/update" + LoginController.userId;

            String temp = "{ \"name\": \"" + nameTextfield.getText() + "\",\n" +
                    " \"strasse\": \"" + straßeTextfield.getText() + "\",\n" +
                    " \"plz\":" + plzTextfield.getText() + ",\n" +
                    " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                    " \"mbw\": \"" + mbwTextfield.getText() + "\",\n" +
                    " \"lieferkosten\": \"" + lieferkostenTextfield.getText() + "\",\n" +
                    " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                    " \"lieferbereich\": \"" + lieferbereichTextfield.getText() + "\"}";

            JSONObjectPUT(temp, url);


            Main m = new Main();
            m.ChangeScene("Startseite.fxml");

        }
    }

    @FXML
    public void zurueckButtonClick () throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Startseite.fxml");

    }
}

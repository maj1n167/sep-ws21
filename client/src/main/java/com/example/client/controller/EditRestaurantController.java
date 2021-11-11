package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

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

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");

        String name = "";
        String straße = "";
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
            straße = currentjson.get("strasse").toString();
            plz = currentjson.get("plz").toString();
            stadt = currentjson.get("stadt").toString();
            lieferkosten = Double.valueOf(currentjson.get("lieferkosten").toString());
            mbw = Double.valueOf(currentjson.get("mbw").toString());
            lieferbereich = Integer.valueOf(currentjson.get("lieferbereich").toString());
            kategorie = currentjson.get(kategorie).toString();
        }



        nameTextfield.setText(name);
        stadtTextfield.setText(stadt);
        straßeTextfield.setText(straße);
        plzTextfield.setText(plz);
        mbwTextfield.setText(String.valueOf(mbw));
        lieferkostenTextfield.setText(String.valueOf(lieferkosten));
        lieferbereichTextfield.setText(String.valueOf(lieferbereich));
        kategorieChoicebox.setValue(kategorie);


    }

    public void speichernButtonClick() throws IOException {

        try {
            /**
             *
             *     private String plz;
             *     private double mbw;
             *     private double lieferkosten;
             *     private int lieferbereich;
             */

            double lieferkosten = Double.parseDouble(lieferkostenTextfield.getText());
            //nur zur Prüfung
            int plz = Integer.parseInt(plzTextfield.getText());
            double mbw = Double.parseDouble(mbwTextfield.getText());
            int radius = Integer.parseInt(lieferbereichTextfield.getText());


            DecimalFormat dec = new DecimalFormat();
            dec.setMinimumFractionDigits(2);
            dec.setMaximumFractionDigits(2);
            dec.format(lieferkosten);
            dec.format(mbw);


            if (nameTextfield.getText().equals("") || straßeTextfield.getText().equals("") || plzTextfield.getText().equals("")
                    || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                    || lieferbereichTextfield.getText().equals("")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
                alert.setContentText("Bitte korrigieren!");

                alert.showAndWait();

            } else {


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
        catch (NumberFormatException e){
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
            alert.setContentText("Bitte korrigieren!");

            alert.showAndWait();
        }
    }



    @FXML
    public void zurueckButtonClick () throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Startseite.fxml");

    }
}

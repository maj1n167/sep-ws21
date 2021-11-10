package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");
        /**
         * TODO: Hier müsste die Kategorie auf die vorher ausgewählte Kategorie gesetzt werden
         */
        kategorieChoicebox.setValue("Italienisch");


        /*
        Aufgabe: Setze aktuelle Restaurantdaten in die Textfelder ein:
                 private int restaurantId;
                 private String name;
                 private String plz;
                 private String stadt;
                 private double mbw;
                 private double lieferkosten;
                 private String katgorie;
                 private int lieferbereich;

         */

        /**
         *1.) mache ein JSON Request nach den aktuellen Restaurantdaten
         */

        ConnectionController con = new ConnectionController();


        String url = "http://localhost:8080/restaurant/update";

        String temp =  "{ \"name\": \"" + nameTextfield.getText() + "\",\n" +
                " \"strasse\": \"" + straßeTextfield.getText() + "\",\n" +
                " \"plz\":" + plzTextfield.getText() + ",\n" +
                " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                " \"mbw\": \"" + mbwTextfield.getText() + "\",\n" +
                " \"lieferkosten\": \"" + lieferkostenTextfield.getText() + "\",\n" +
                " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                " \"lieferbereich\": \"" + lieferbereichTextfield.getText() + "\"}";

       JSONObjectPUT(temp, url);

/**
 * Schneide mein JSON Objekt in die einzelnen Parameter aus
 * Jede Zeile ist eine Komponente (RestaurantId, name, plz ...)
 * TODO: name nach perfekter Größe der Komponente anpassen
 */




        /**
         * füge Daten in die Textfelder ein (ab name)
          */
       // nameTextfield.setText(name);


    }

    public void speichernButtonClick() throws IOException {

        /**
         *
         @PutMapping("/update")
         public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurants) {
         Restaurant updateRestaurant = restaurantService.addRestaurant(restaurants);
         return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
         }
         */


        Main m= new Main();
        m.ChangeScene("Startseite.fxml");

    }

    @FXML
    public void zurueckButtonClick () throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Startseite.fxml");

    }
}

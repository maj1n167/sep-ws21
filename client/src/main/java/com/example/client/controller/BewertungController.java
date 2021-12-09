package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;


public class BewertungController extends ConnectionController {


    @FXML
    private TextField kommentarTextfield;

    @FXML
    ChoiceBox<String> gerichtChoiceBox;

    @FXML
    ChoiceBox<String> lieferungChoiceBox;

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;


    private int restaurantId;




    public void initialize() {

        gerichtChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        gerichtChoiceBox.setValue("5");

        lieferungChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        lieferungChoiceBox.setValue("5");

        /**
         * TODO: Speicherung fertigstellen
         */
        this.restaurantId = 1;
    }


    public void speichernButtonClick () throws IOException {

        if (kommentarTextfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlendes Kommentarfeld.");
            alert.setContentText("Bitte füllen Sie das Kommentarfeld aus.");

            alert.showAndWait();
        }
        else {

            /**
             * int id;
             *     int starsLieferung;
             *     int starsFood;
             *     String comment;
             */
            String url = "http://localhost:8080/rating/add";

            JSONObject bewertung = new JSONObject();

            bewertung.put("comment", kommentarTextfield.getText());
            bewertung.put("starsLieferung", Integer.parseInt(lieferungChoiceBox.getValue()));
            bewertung.put("starsFood", Integer.parseInt(gerichtChoiceBox.getValue()));
            bewertung.put("restaurantId", restaurantId);

            System.out.println(bewertung);
            JSONObjectPOST(url, bewertung.toString());

            Main m = new Main();
            m.ChangeScene("BewertungList.fxml");

        }

    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }
}

package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class AddRestaurantController extends ConnectionController {

    @FXML
    private Label restaurantHeaderLabel;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField strasseTextfield;

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
    ChoiceBox<String> kategorieChoicebox;

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;




    public void initialize() {

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");
        //ersten Punkt auf Pizza setzen
        kategorieChoicebox.setValue("Italienisch");

    }



    @FXML
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


            if (nameTextfield.getText().equals("") || strasseTextfield.getText().equals("") || plzTextfield.getText().equals("")
                    || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                    || lieferbereichTextfield.getText().equals("")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setTitle("Error: Fehlende Zeilen!");
                alert.setContentText("Bitte füllen Sie alle Felder aus!");

                alert.showAndWait();

            } else {

                /**
                 private int restaurantId;
                 private String name;
                 private String plz;
                 private String stadt;
                 private double mbw;
                 private double lieferkosten;
                 private String katgorie;
                 private int lieferbereich;
                 */
                String url = "http://localhost:8080/restaurant/add";

                String json = "{ \"name\": \"" + nameTextfield.getText() + "\",\n" +
                            " \"strasse\": \"" + strasseTextfield.getText() + "\",\n" +
                            " \"plz\":" + plzTextfield.getText() + ",\n" +
                            " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                            " \"mbw\": \"" + mbw + "\",\n" +
                            " \"lieferkosten\": \"" + lieferkosten + "\",\n" +
                            " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                            " \"lieferbereich\": \"" + radius + "\"}";

                JSONObjectPOST(url, json);

                Main m = new Main();
                m.ChangeScene("Startseite.fxml");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Falsches Zeichenformat!");
            alert.setContentText("Bitte korrigieren!");

            alert.showAndWait();
        }
    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}


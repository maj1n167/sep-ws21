package com.example.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRestaurantController extends ConnectionController {

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
    ChoiceBox <String> kategorieChoicebox;

    @FXML
    private Button speichernButton;

    public void initialize(){

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch","Sonstige:");
        //ersten Punkt auf Pizza setzen
        kategorieChoicebox.setValue("Italienisch");

        if (kategorieChoicebox.getValue().equals("Sonstige:")){
            sonstigeText.setVisible(true);
        }
    }

    public void populateVew(){

    }

    public void speichernButtonClick() {
        if (nameTextfield.getText().equals("") || straßeTextfield.getText().equals("")){

            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        }
       else {
         //   ConnectionController.neueRestaurantDaten(nameTextfield.getText(), straßeTextfield.getText()){

            }
        }
    }


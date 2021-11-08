package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
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
    ChoiceBox<String> kategorieChoicebox;

    @FXML
    private Button SpeichernButton;

    @FXML
    private Button zurueckButton;

    public void initialize() {

        kategorieChoicebox.getItems().addAll("Italienisch", "Indisch", "Asiatisch", "Amerikanisch", "Sonstige:");
        //ersten Punkt auf Pizza setzen
        kategorieChoicebox.setValue("Italienisch");



      /*  Wenn die Kategorie nicht dabei ist, eine neue hinzufügen und Sonstige wird visible gemacht
        {
            if (kategorieChoicebox.getValue().equals("Sonstige:")) {
                sonstigeText.setVisible(true);
            }
        }
    */
    }

    //Kategorie muss jedes mal aktualisiert werden
    public void populateView() {

    }

    @FXML
    public void speichernButtonClick() throws IOException {
        if (nameTextfield.getText().equals("") || straßeTextfield.getText().equals("") || plzTextfield.getText().equals("")
                || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                || lieferbereichTextfield.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Error: Fehlende Zeilen!");
            alert.setContentText("Bitte füllen Sie alle Felder aus!");

            alert.showAndWait();
        } else {
            String url = "localhost:8080/restaurant/add";
            String json= nameTextfield.getText() + ", " + straßeTextfield.getText()  + ", " + plzTextfield.getText()  + ", "
                    + stadtTextfield.getText()  + ", " + lieferkostenTextfield.getText()  + ", " + mbwTextfield.getText()  + ", "
                    + lieferbereichTextfield.getText();
            JSONObjectPUT(url,json);

            Main m = new Main();
            m.ChangeScene("Startseite.fxml");
        }
    }
    public void zurueckButtonClick () throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}


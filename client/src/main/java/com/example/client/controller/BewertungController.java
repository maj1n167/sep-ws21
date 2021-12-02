package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;


public class BewertungController extends ConnectionController {


    @FXML
    private TextField kommentarTextfield;

    @FXML
    ChoiceBox<String> gerichtChoiceBox;

    @FXML
    ChoiceBox<String> lieferungChoiceBox;

    private int userId;



    public void initialize() {

        gerichtChoiceBox.getItems().addAll("*", "**", "***", "****", "*****");
        //ersten Punkt auf * setzen
        gerichtChoiceBox.setValue("*");

        lieferungChoiceBox.getItems().addAll("*", "**", "***", "****", "*****");
        //ersten Punkt auf * setzen
        lieferungChoiceBox.setValue("*");

        this.userId = LoginController.userId;
    }


    public void speichernButtonClick (){

    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}

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

        if (kommentarTextfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlendes Kommentarfeld.");
            alert.setContentText("Bitte füllen Sie das Kommentarfeld aus.");

            alert.showAndWait();
        }
        else {
            String url = "http://localhost:8080/bewertung/add";

            JSONObject bewertung = new JSONObject();

            bewertung.put("kommentar", kommentarTextfield.getText());

           // JSONObjectPOST(url, bewertung.toString());

           /** Main m = new Main();
            m.ChangeScene("Startseite.fxml");
            **/
        }

    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}

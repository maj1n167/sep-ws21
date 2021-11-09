package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RegistrationController extends ConnectionController {

    @FXML
    Button RegisterButton;
    @FXML
    TextField vornameTextfield;
    @FXML
    TextField nameTextfield;
    @FXML
    TextField emailTextfield;
    @FXML
    PasswordField passwortTextfield;


    @FXML
    protected void onRegisterButtonClick() throws  IOException{

        if (vornameTextfield.getText().equals("")||nameTextfield.getText().equals("")||emailTextfield.getText().equals("")
                ||passwortTextfield.getText().equals("")){

            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        }
        else {
            String url = "http://localhost:8080/user/add";


            String data = "{ \"vorname\": \""+vornameTextfield.getText().toString()+"\",\n" +
                            " \"name\": \""+nameTextfield.getText().toString()+"\",\n" +
                            " \"email\":\""+emailTextfield.getText().toString()+"\",\n"+
                            " \"password\": \""+passwortTextfield.getText().toString()+"\" }";

//            String data = "{\"vorname\" : \"leon\", \"name\" : \"gashi\", \"email\" : \"leongashi@fxml.de\", \"password\" : \"hanswurst\" }";
            JSONObjectPOST(url, data);
            System.out.println("Daten korrekt übertragen");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Speise hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");

            alert.showAndWait();
        }


    }




    @FXML
    public void goBackButtonClick(ActionEvent event) throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }
    @FXML
    public void onPrivatKundeClick(ActionEvent event) throws IOException {

        //Privatkunde --> Fortsetzung in Zyklus 2
    }
    @FXML
    public void onBusinessUserClick(ActionEvent event) throws IOException {

        //BusinessUser --> Weiterleitung zu Restaurand anlegen
    }

}
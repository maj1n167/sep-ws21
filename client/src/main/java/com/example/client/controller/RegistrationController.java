package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

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
    RadioButton BusinessUser;
    @FXML
    RadioButton Kunde;


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
    public void goBackButtonClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }


    @FXML
    ToggleGroup group = new ToggleGroup();

    @FXML
    public void onPrivatKundeClick() throws IOException {

        //Privatkunde --> Fortsetzung in Zyklus 2
        Kunde.setToggleGroup(group);
    }
    @FXML
    public void onBusinessUserClick() throws IOException {

        //BusinessUser --> Weiterleitung zur Startseite
        BusinessUser.setToggleGroup(group);
    }

}
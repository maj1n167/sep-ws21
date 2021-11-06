package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    protected void onRegisterButtonClick() {


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
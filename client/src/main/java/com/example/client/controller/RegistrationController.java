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
    Button registerButton;
    @FXML
    TextField vornameTextfield;
    @FXML
    TextField nameTextfield;
    @FXML
    TextField emailTextfield;
    @FXML
    PasswordField passwortTextfield;
    @FXML
    RadioButton businessUser;
    @FXML
    RadioButton kunde;
    @FXML
    ToggleGroup group = new ToggleGroup();



    @FXML
    protected void onRegisterButtonClick() throws IOException {

        if (vornameTextfield.getText().equals("") || nameTextfield.getText().equals("") || emailTextfield.getText().equals("")
                || passwortTextfield.getText().equals("") || !(businessUser.isSelected() || kunde.isSelected()) ) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        } else if (businessUser.isSelected()) {

            String url = "http://localhost:8080/user/add";


            String data = "{ \"vorname\": \"" + vornameTextfield.getText().toString() + "\",\n" +
                    " \"name\": \"" + nameTextfield.getText().toString() + "\",\n" +
                    " \"email\":\"" + emailTextfield.getText().toString() + "\",\n" +
                    " \"password\": \"" + passwortTextfield.getText().toString() + "\",\n" +
                    " \"restaurantBesitzer\": \"true\" }";

            JSONObjectPOST(url, data);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Konto hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");
            alert.showAndWait();
            Main m = new Main();
            m.ChangeScene("Login.fxml");
        } else {
//         Hier erstmal alles kommentiert, da das hier in Zyklus 2 relevant sein wird. -ok
            String url = "http://localhost:8080/user/add";


            String data = "{ \"vorname\": \""+vornameTextfield.getText().toString()+"\",\n" +
                    " \"name\": \""+nameTextfield.getText().toString()+"\",\n" +
                    " \"email\":\""+emailTextfield.getText().toString()+"\",\n"+
                    " \"password\": \""+passwortTextfield.getText().toString()+"\",\n" +
                    " \"restaurantBesitzer\": \"false\" }";

            JSONObjectPOST(url, data);
            System.out.println("Daten korrekt übertragen");
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Konto hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");
            alert.showAndWait();
            Main m = new Main();
            //Hier kommt dann die view vom normalen Kunden rein. - ok
            m.ChangeScene("KRegistration.fxml");
        }
    }

    @FXML
    public void goBackButtonClick() throws IOException {
        // Change Scenes
        Main m = new Main();
        m.ChangeScene("Login.fxml");
    }

    @FXML
    public void onPrivatKundeClick() throws IOException {

        //Privatkunde --> Fortsetzung in Zyklus 2
        kunde.setToggleGroup(group);
    }

    @FXML
    public void onBusinessUserClick() throws IOException {

        //BusinessUser --> Weiterleitung zur Startseite
        businessUser.setToggleGroup(group);
    }
}


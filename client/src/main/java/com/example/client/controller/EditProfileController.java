package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;


public class EditProfileController extends ConnectionController {

    @FXML
    Button safeButton;
    @FXML
    TextField neuerVorname;
    @FXML
    TextField neuerName;
    @FXML
    TextField neueEmail;
    @FXML
    PasswordField neuesPasswort;


    public void initialize() throws IOException {
        neuerVorname.setText(LoginController.vorname);
        neuerName.setText(LoginController.name);
        neuesPasswort.setText(LoginController.password);
        neueEmail.setText(LoginController.email);
    }

    @FXML
    public void onSafeButtonClick() throws IOException {

        //Hier werden die alten Daten von den neuen überschrieben -lg

        String url = "http://localhost:8080/user/update/"+ LoginController.userId;

        LoginController.vorname = neuerVorname.getText();
        LoginController.name = neuerName.getText();
        LoginController.password = neuesPasswort.getText();
        LoginController.email = neueEmail.getText();




        String data = "{ \"userId\": \""+LoginController.userId+"\", \n";
        data = data + "\"vorname\": \"" + neuerVorname.getText().toString() + "\",\n";
        data = data + " \"name\": \"" + neuerName.getText().toString() + "\",\n";
        data = data + " \"email\": \"" + neueEmail.getText().toString() + "\",\n";
        data = data + " \"password\": \"" + neuesPasswort.getText().toString() + "\",\n";
        data = data + " \"restaurantBesitzer\": \"true\" }";

        JSONObjectPUT(url, data);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Daten editiert");
        alert.setContentText("Daten editiert!");
        alert.showAndWait();
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");


    }

    public void onGoBackButtonClick() throws IOException {

        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
}

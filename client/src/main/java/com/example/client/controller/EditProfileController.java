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

    @FXML
    public void onSafeButtonClick() throws IOException {

        //Hier werden die alten Daten von den neuen überschrieben -lg

        String url = "http://localhost:8080/user/put";


        String data = "{ \"vorname\": \"" + neuerVorname.getText().toString() + "\",\n" +
                " \"name\": \"" + neuerName.getText().toString() + "\",\n" +
                " \"email\":\"" + neueEmail.getText().toString() + "\",\n" +
                " \"password\": \"" + neuesPasswort.getText().toString() + "\",\n" +
                " \"restaurantBesitzer\": \"true\" }";

        JSONObjectPOST(url, data);


    }

    public void onGoBackButtonClick() throws IOException {

        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }


}

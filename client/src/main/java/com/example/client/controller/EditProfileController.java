package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Optional;

public class EditProfileController extends ConnectionController{

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
    public void onSafeButtonClick() {

        //Hier werden die alten Daten von den neuen überschrieben -lg
    }

    public void onGoBackButtonClick() throws IOException {

        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }


}

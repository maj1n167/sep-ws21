package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class KundeRegistrationController extends ConnectionController {

    @FXML
    Button KundeRegisterButton;
    @FXML
    TextField geburtsdatumTextfield;
    @FXML
    TextField postleitzahlTextfield;
    @FXML
    TextField stadtTextfield;
    @FXML
    TextField strasseTextfield;

    @FXML
    protected void onKundeRegisterButtonClick() throws IOException {

        if (geburtsdatumTextfield.getText().equals("") || postleitzahlTextfield.getText().equals("") || stadtTextfield.getText().equals("")
                || strasseTextfield.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        }else{

            String url = "http://localhost:8080/user/add";


            String data = "{ \"geburtsdatum\": \"" + geburtsdatumTextfield.getText().toString() + "\",\n" +
                    " \"postleitzahl\": \"" + postleitzahlTextfield.getText().toString() + "\",\n" +
                    " \"stadt\":\"" + stadtTextfield.getText().toString() + "\",\n" +
                    " \"strasse\": \"" + strasseTextfield.getText().toString() + "}"; //so richtig?

            JSONObjectPOST(url, data);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Konto hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");
            alert.showAndWait();
            Main m = new Main();
            m.ChangeScene("Login.fxml");
        }
    }

        @FXML
        public void backToButtonClick() throws IOException {

        // Change Scenes
        Main m = new Main();
        m.ChangeScene("Register.fxml");
    }
    }


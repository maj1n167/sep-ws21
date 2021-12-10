package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

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
    TextField nummerTextfield;



    @FXML
    protected void onKundeRegisterButtonClick() throws IOException {

        if (geburtsdatumTextfield.getText().equals("") || postleitzahlTextfield.getText().equals("") || stadtTextfield.getText().equals("")
                || strasseTextfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        } else {
            String data = "";
            String url = "http://localhost:8080/user/update/"+RegistrationController.regUserId;
            JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/user").toString());
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject =  jsonArray.getJSONObject(i);
                if(jsonObject.get("userId").equals(RegistrationController.regUserId)){
                    data = data + "{ \"userId\": \""+RegistrationController.regUserId+"\", \n" +
                            "   \"vorname\": \"" + jsonObject.get("vorname").toString() + "\",\n" +
                            "   \"name\": \"" + jsonObject.get("name").toString() + "\",\n" +
                            "   \"email\":\"" + jsonObject.get("email").toString() + "\",\n" +
                            "   \"password\": \"" + jsonObject.get("password").toString() + "\",\n" +
                            "   \"restaurantBesitzer\": \"false\", \n";
                }
            }
            data = data + "   \"geburtsdatum\": \"" + geburtsdatumTextfield.getText().toString() + "\",\n" +
                    "   \"plz\": \"" + postleitzahlTextfield.getText().toString() + "\",\n" +
                    "   \"nummer\": \""+nummerTextfield.getText().toString() +"\",\n" +
                    "   \"stadt\":\"" + stadtTextfield.getText().toString() + "\",\n" +
                    "   \"guthaben\": \"0\",\n" +
                    "   \"altAdresse\": \"\",\n" +
                    "   \"altPlz\": \"\",\n" +
                    "   \"altStadt\": \"\",\n" +
                    "   \"strasse\": \"" + strasseTextfield.getText().toString() + "\"\n }";
            System.out.println(data);
            JSONObjectPUT(url, data);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Konto hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");
            alert.showAndWait();
            Main m = new Main();
            m.ChangeScene("Login.fxml");
        }
    }

    @FXML
    public void goBackToButtonClick(ActionEvent actionEvent) throws IOException {
        // Change Scenes

        JSONObjectDELETE("http://localhost:8080/user/delete/"+RegistrationController.regUserId);
        Main m = new Main();
        m.ChangeScene("Login.fxml");
    }
}


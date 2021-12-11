package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerificationController extends ConnectionController implements Initializable {


    @FXML
    TextField inputCode;

    String email;

    //wichtig damit user bestellungen durchführen können anpassungen in keditprofile und kaltaddress erforderlich
    public static String standard;
    public static String alternative;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
          this.email =  LoginController.email;
        }


    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        changeScene("Login.fxml");
    }

   public void onVerifyButtonClicked(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyemail/"+email).toString());
           if(jsonObject.get("verifyCode").equals(Integer.parseInt(inputCode.getText()))) {
               if (jsonObject.get("restaurantBesitzer").equals(true)) {
                   jsonObject.put("verifyCode",0);
                   JSONObjectPUT("http://localhost:8080/user/update/"+jsonObject.get("userId"), jsonObject.toString());
                   changeScene("Startseite.fxml");
                   return;
               } else {
                   standard = jsonObject.getString("strasse") + " " + jsonObject.getString("nummer") + " " + jsonObject.getString("plz") + " " + jsonObject.getString("stadt");
                   alternative = jsonObject.getString("altAdresse") + " " + jsonObject.getString("altNummer") + " " + jsonObject.getString("altPlz") + " " + jsonObject.getString("altStadt");
                   jsonObject.put("verifyCode",0);
                   JSONObjectPUT("http://localhost:8080/user/update/"+jsonObject.get("userId"), jsonObject.toString());
                   changeScene("KStartseite.fxml");
                   return;
               }
           }
       Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Error");
       alert.setTitle("Error: Daten nicht Korrekt");
       alert.setContentText("Bitte überprüfen sie ihre Eingaben!");

       alert.showAndWait();
       }
  }



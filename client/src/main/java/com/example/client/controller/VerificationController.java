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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
          this.email =  LoginController.email;
        }


    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.ChangeScene("Login.fxml");
    }

   public void onVerifyButtonClicked(ActionEvent event) throws IOException {
     JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/user/find/"+email).toString());
           if(jsonObject.get("verfiyCode").equals(Integer.parseInt(inputCode.getText()))) {
               if (jsonObject.get("restaurantBesitzer").equals(true)) {
                   Main m = new Main();
                   m.ChangeScene("Startseite.fxml");
                   return;
               } /*else {
                   Main m = new Main();
                   m.ChangeScene("kStartseite.fxml");
                   return;
               }*/
           }
       Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Error");
       alert.setTitle("Error: Daten nicht Korrekt");
       alert.setContentText("Bitte überprüfen sie ihre Eingaben!");

       alert.showAndWait();
       }
  }



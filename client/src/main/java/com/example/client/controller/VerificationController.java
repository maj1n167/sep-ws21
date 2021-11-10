package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
       System.out.println(email);
     JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/user/find/"+email).toString());
           if(jsonObject.get("verfiyCode").equals(Integer.parseInt(inputCode.getText()))){
               System.out.println("Verifizierung Erfolgreich");
               Main m = new Main();
               m.ChangeScene("Startseite.fxml");
               return;
           }
           Main m = new Main();
           m.ChangeScene("Login.fxml");
       }
  }



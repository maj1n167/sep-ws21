package com.example.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class LoginController extends ConnectionController {

    @FXML
    private Label SupremeLieferando;


    @FXML
    private PasswordField inputPassword;

    @FXML
    Button RegisterButton;

    @FXML
    private  TextField inputEmail;

    @FXML
    protected void onLoginButtonClick() throws IOException {
       String url = "http://localhost:8080/user";

       JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
       for(int i = 0; i<jsonArray.length(); i++){
         JSONObject jsonObject =  jsonArray.getJSONObject(i);
         if(jsonObject.get("email").equals(inputEmail.getText()) && jsonObject.get("password").equals(inputPassword.getText())){

            JSONObjectGET("http://localhost:8080/user/find/"+jsonObject.get("email"));
             System.out.println("Login erfolgreich!");
         }
       }
    }

    public void onRegisterButtonClick(ActionEvent event) throws IOException {

      //Code klappt nicht, view soll hier eigentlich geswitched werden-lg
      Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
      Scene abc = new Scene(root);

      Stage window = (Stage) ( (Node)event.getSource()).getScene().getWindow();

      window.setScene(abc);
      window.show();

    }
}
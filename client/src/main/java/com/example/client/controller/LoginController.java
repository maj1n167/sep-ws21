package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;


public class LoginController extends ConnectionController {



    @FXML
    private Label SupremeLieferando;
    @FXML
    private PasswordField inputPassword;
    @FXML
    Button RegisterButton;
    @FXML
    private  TextField inputEmail;

    public static String email;
    public static int userId;


    @FXML
    protected void onLoginButtonClick() throws IOException {

       String url = "http://localhost:8080/user";

       JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
       for(int i = 0; i<jsonArray.length(); i++){
         JSONObject jsonObject =  jsonArray.getJSONObject(i);
         if(jsonObject.get("email").equals(inputEmail.getText()) && jsonObject.get("password").equals(inputPassword.getText())){
             JSONObjectGET("http://localhost:8080/user/send/"+jsonObject.get("email"));
             email = jsonObject.get("email").toString();
             userId = Integer.parseInt(jsonObject.get("userId").toString());
             Main m = new Main();
             m.ChangeScene("Verify-View.fxml");
             return;
         }
       }
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setTitle("Error: Daten nicht Korrekt");
        alert.setContentText("Bitte überprüfen sie ihre Eingaben!");

        alert.showAndWait();
    }
    @FXML
    public void onRegisterButtonClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Registration.fxml");
    }

    @FXML
    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }
}
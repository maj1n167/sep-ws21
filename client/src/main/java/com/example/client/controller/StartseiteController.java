package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.json.JSONArray;

import java.io.IOException;

public class StartseiteController extends ConnectionController {

@FXML
    Button createRestaurant;
    @FXML
    public void initialize() throws IOException {
       JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
    if(j.getJSONObject(0)!=null){
        createRestaurant.setVisible(false);
    }
    }

    @FXML
    public void onEditProfileClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditProfile.fxml");
    }

    @FXML
    public void onCreateRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("AddRestaurant.fxml");
    }

    @FXML
    public void onEditRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditRestaurant.fxml");
    }

    @FXML
    public void onSpeisekarteClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("Speisekarte.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }
}

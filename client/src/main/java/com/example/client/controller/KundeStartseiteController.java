package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class KundeStartseiteController extends ConnectionController{

    @FXML
    public void initialize() throws IOException {
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/kunde").toString());
        for (int i = 0; i < j.length(); i++) {
            JSONObject curretnJson = new JSONObject(j.getJSONObject(i).toString());

            }
        }



    @FXML
    public void onSeekRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditProfile.fxml");
    }

    @FXML
    public void onShowOrderClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("AddRestaurant.fxml");
    }

    @FXML
    public void onBuyCreditClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditRestaurant.fxml");
    }

    @FXML
    public void onValuationClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("Speisekarte.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }

    @FXML
    public void onLoyaltyPointsClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Map.fxml");
    }
}



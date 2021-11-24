package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class KundeStartseiteController extends ConnectionController{

    @FXML
    public void initialize() throws IOException {
    }



    @FXML
    public void onSeekRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("Restaurants.fxml");
    }

    @FXML
    public void onShowOrderClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    @FXML
    public void onBuyCreditClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("Guthaben.fxml");
    }

    @FXML
    public void onValuationClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("KStartseite.fxml");
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
        m.ChangeScene("KStartseite.fxml");
    }
}



package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class KundeStartseiteController extends ConnectionController{

    @FXML
    public void initialize() throws IOException {
        /*      Zum Testen der Buttons in Restaurants
        if(RestaurantsController.id!=-1){
            System.out.println(RestaurantsController.id);
        } else {
            System.out.println("no restaurant chosen");
        }

         */
        System.out.println("Standard: " + VerificationController.standard);
        System.out.println("Alternativ: " + VerificationController.alternative);
    }



    @FXML
    public void onSeekRestaurantClick() throws IOException {
        changeScene("Restaurants.fxml");
    }

    @FXML
    public void onShowOrderClick() throws IOException {
        changeScene("bestellHistorie.fxml");
    }

    @FXML
    public void onBuyCreditClick() throws IOException {
        changeScene("Guthaben.fxml");
    }

    @FXML
    public void onValuationClick() throws IOException {
        changeScene("Bewertung.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        changeScene("Login.fxml");
    }

    @FXML
    public void onLoyaltyPointsClick() throws IOException {
        // Change Scenes
        changeScene("Treuepunkte.fxml");
    }
}


